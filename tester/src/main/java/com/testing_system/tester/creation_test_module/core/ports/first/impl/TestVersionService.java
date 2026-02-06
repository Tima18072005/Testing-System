package com.testing_system.tester.creation_test_module.core.ports.first.impl;

import com.testing_system.tester.creation_test_module.core.domain.*;
import com.testing_system.tester.creation_test_module.core.ports.first.TestVersionUseCase;
import com.testing_system.tester.creation_test_module.core.ports.first.exceptions.NoVersionException;
import com.testing_system.tester.creation_test_module.core.ports.second.TestVersionDrivenUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;


// Класс-оркестратор для работы с версиями теста
public class TestVersionService implements TestVersionUseCase {

    private final TestVersionDrivenUseCase secondPort;
    private final Logger logger = LoggerFactory.getLogger(TestVersionService.class);

    public TestVersionService(TestVersionDrivenUseCase secondPort) {
        this.secondPort = secondPort;
    }


    @Override
    public List<TestVersion> getAllTestVersion(String currentTestName) {

        var allVersions = secondPort.getAllTestVersions(currentTestName);

        if (allVersions.isEmpty()) throw new IllegalStateException("Error! Test cant exist without versions! Test name: "
                + currentTestName);

        return allVersions;
    }

    /*
    кидает IllegalStateException()
     */
    @Override
    public void makeNewVersion(TestVersion newVersion, Map<Question, List<Answer>> newVersionContent) {

        var allVersion = getAllTestVersion(newVersion.getTestName());

        if (allVersion.stream().map(TestVersion::getVersionId).anyMatch(id ->id.equals(newVersion.getVersionId()))) {
            throw new IllegalArgumentException("Error! You can't save version with same ids! Test name: "
                    + newVersion.getVersionId() +
                    ". Version id: " + newVersion.getVersionId());
        }

        secondPort.saveTestVersion(newVersion, newVersionContent);
    }

    /*
    кидает IllegalStateException()
    Если вопросы и ответы на них содержатся в других версиях, то они не удаляются
     */
    @Override
    public void deleteVersion(String currentTestName, Integer currentVersionId) {

        var allVersions = getAllTestVersion(currentTestName);

        if (allVersions.size() == 1) throw new IllegalStateException(
                "Error! You cannot delete a single version of test! Test name: " + currentTestName);

        secondPort.deleteTestVersion(currentVersionId);
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
    Алгоритм рандомизации id вопросов и ответов к ним
     */
    private Map<Question, List<Answer>> randomFilterQuestion(String currentTestName, Map<Question, List<Answer>> currentContent){
        return Map.of();
    }

    /*
    для прохождения
    кидает IllegalStateException()
    кидает NoVersionException()
     */
    @Override
    public Map<Question, List<Answer>> getLastUsableVersion(String currentTestName) {

        var allVersions = getAllTestVersion(currentTestName);

        // Разобрать подробнее stream api, проверка на null при статусе
        Integer currentVersionId = allVersions.stream()
                .filter(version -> version.getVersionStatus().equals(VersionStatus.READY))
                .sorted(Comparator.comparing(TestVersion::getCreationDate).reversed()).toList()
                .getFirst().getVersionId();

        return randomFilterQuestion( currentTestName,getVersionContentById(currentVersionId));
    }


    /*
    может менять только автор версии
    кидает NoVersionException()
     */
    @Override
    public void makeDraft(Integer currentVersionId) {

        var currentVersion = getVersionMetaDataById(currentVersionId);

        currentVersion.setCreationDate(LocalDate.now());

        currentVersion.setDraft();

        secondPort.changeMetadata(currentVersion);
        logger.info("Metadata for {} was successfully changed! Version {} is draft", currentVersion.getTestName(), currentVersionId);
    }

    /*
    может менять только автор версии
    кидает NoVersionException()
     */
    @Override
    public void makeReady(Integer currentVersionId) {

        var currentVersion = getVersionMetaDataById(currentVersionId);

        currentVersion.setCreationDate(LocalDate.now());

        currentVersion.setReady();

        secondPort.changeMetadata(currentVersion);
        logger.info("Metadata for {} was successfully changed! Version {} is ready", currentVersion.getTestName(), currentVersionId);

    }

}
