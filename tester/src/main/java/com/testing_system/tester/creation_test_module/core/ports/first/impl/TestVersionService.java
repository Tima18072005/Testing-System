package com.testing_system.tester.creation_test_module.core.ports.first.impl;

import com.testing_system.tester.creation_test_module.core.domain.*;
import com.testing_system.tester.creation_test_module.core.ports.first.TestCommandUseCase;
import com.testing_system.tester.creation_test_module.core.ports.first.TestQueryUseCase;
import com.testing_system.tester.creation_test_module.core.ports.first.TestVersionUseCase;
import com.testing_system.tester.creation_test_module.core.ports.first.exceptions.NoTestException;
import com.testing_system.tester.creation_test_module.core.ports.first.exceptions.NoVersionException;
import com.testing_system.tester.creation_test_module.core.ports.second.TestVersionDrivenUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


/*
Сервис-оркестратор для работы с версиями теста
 */
@Service
public class TestVersionService implements TestVersionUseCase {


    //Реализация использует первичные и вторичные порты
    private final TestQueryUseCase firstPort;
    private final TestVersionDrivenUseCase secondPort;
    private final Logger logger = LoggerFactory.getLogger(TestVersionService.class);


    public TestVersionService(TestQueryUseCase firstPort, TestVersionDrivenUseCase secondPort) {
        this.firstPort = firstPort;
        this.secondPort = secondPort;
    }


    @Override
    public List<TestVersion> getAllTestVersion(String currentTestName) {

        if (!firstPort.findTest(currentTestName))
            throw new NoTestException("Error! Test %t is not found!"
                    .formatted(currentTestName));

        var allVersions = secondPort.getAllTestVersions(currentTestName);

        if (allVersions.isEmpty()) throw new IllegalStateException("Error! Test can't exist without versions! Test name: "
                + currentTestName);

        return allVersions;
    }


    @Override
    public TestVersion getVersionMetaDataById(Integer currentVersionId) {
        return secondPort.getVersionMetaData(currentVersionId)
                .orElseThrow(() -> new NoVersionException(
                        "Current version isn't found! Version id: " + currentVersionId));
    }


    @Override
    public Map<Question, List<Answer>> getVersionContentById(Integer currentVersionId) {

        var content = secondPort.getVersionContent(currentVersionId);

        if (content.isEmpty()) throw new NoVersionException(
                "Current version isn't found! Version id: " + currentVersionId);

        return content;
    }


    /*
    кидает IllegalStateException()
    кидает NoTestException()
     */
    @Override
    public void makeNewVersion(TestVersion newVersion, Map<Question, List<Answer>> newVersionContent) {

        var currentTestMetadata = firstPort.getTestByName(newVersion.getTestName());

        var allVersion = getAllTestVersion(newVersion.getTestName());


        if (allVersion.stream().map(TestVersion::getVersionId).anyMatch(id ->id.equals(newVersion.getVersionId()))) {
            throw new IllegalArgumentException("Error! You can't save version with same ids! Test name: %t Version id: %v"
                    .formatted(newVersion.getTestName(),newVersion.getVersionId()));
        }

        if (newVersionContent.keySet().stream().toList().size() < currentTestMetadata.getQuestionBatch())
            throw new IllegalArgumentException("Error! Too few question in the version %v ! Minimal question size: %s"
                    .formatted(newVersion.getVersionId(), currentTestMetadata.getQuestionBatch()));


        secondPort.saveTestVersion(newVersion, newVersionContent);
    }

    /*
    кидает IllegalStateException()
    Если вопросы и ответы на них содержатся в других версиях, то они не удаляются
     */
    @Override
    public void deleteVersion(Integer currentVersionId) {

        var currentTestName = getVersionMetaDataById(currentVersionId).getTestName();

        var allVersions = getAllTestVersion(currentTestName);

        if (allVersions.size() == 1) throw new IllegalStateException(
                "Error! You cannot delete a single version of test! Test name: " + currentTestName);


        if (secondPort.getVersionMetaData(currentVersionId).isEmpty())
            throw new IllegalArgumentException("You can't delete non-existent version! Version id: %v"
                    .formatted(currentVersionId));

        secondPort.deleteTestVersion(currentVersionId);
    }


    // Рандомно выбираем вопросы, чтобы составить батч для тестирования
    private Map<Question, List<Answer>> randomFilterQuestion(String currentTestName, Map<Question, List<Answer>> currentContent){

        var currentBatch = firstPort.getTestByName(currentTestName).getQuestionBatch();
        var allQuestion = new ArrayList<>(currentContent.keySet());

        if (currentBatch.equals(allQuestion.size())) return currentContent;

        Collections.shuffle(allQuestion);

        var currentQuestions = allQuestion.subList(0, currentBatch);

        var currentQuestionsSet = Set.copyOf(currentQuestions);// Для ускорения

        return currentContent.entrySet().stream()
                .filter(entry -> currentQuestionsSet.contains(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));
    }

    /*
    для прохождения
    кидает IllegalStateException()
    кидает NoVersionException()
     */
    @Override
    public Map<Question, List<Answer>> getLastUsableVersion(String currentTestName) {

        var allVersions = getAllTestVersion(currentTestName);

        Integer currentVersionId = allVersions.stream()
                .filter(version -> version.getVersionStatus().equals(VersionStatus.READY))
                .sorted(Comparator.comparing(TestVersion::getCreationDate).reversed()).toList()
                .getFirst().getVersionId();

        return randomFilterQuestion(currentTestName, getVersionContentById(currentVersionId));
    }


    /*
    может менять только автор версии и админ
    кидает NoVersionException()
     */
    @Override
    public void makeDraft(Integer currentVersionId) {

        var currentVersion = getVersionMetaDataById(currentVersionId);

        currentVersion.setCreationDate(LocalDate.now());

        currentVersion.setDraft();

        secondPort.saveTestVersion(currentVersion);
        logger.info("Metadata for {} was successfully changed! Version {} is draft", currentVersion.getTestName(), currentVersionId);
    }

    /*
    может менять только автор версии и админ
    кидает NoVersionException()
     */
    @Override
    public void makeReady(Integer currentVersionId) {

        var currentVersion = getVersionMetaDataById(currentVersionId);

        currentVersion.setCreationDate(LocalDate.now());

        currentVersion.setReady();

        secondPort.saveTestVersion(currentVersion);
        logger.info("Metadata for {} was successfully changed! Version {} is ready", currentVersion.getTestName(), currentVersionId);

    }

}
