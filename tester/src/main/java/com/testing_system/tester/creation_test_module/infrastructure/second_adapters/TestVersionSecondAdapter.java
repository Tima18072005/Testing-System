package com.testing_system.tester.creation_test_module.infrastructure.second_adapters;

import com.testing_system.tester.creation_test_module.core.domain.Answer;
import com.testing_system.tester.creation_test_module.core.domain.Question;
import com.testing_system.tester.creation_test_module.core.domain.TestVersion;
import com.testing_system.tester.creation_test_module.core.ports.second.TestVersionDrivenUseCase;
import com.testing_system.tester.creation_test_module.infrastructure.dto.db.AnswerEntity;
import com.testing_system.tester.creation_test_module.infrastructure.dto.db.QuestionEntity;
import com.testing_system.tester.creation_test_module.infrastructure.dto.db.TestVersionEntity;
import com.testing_system.tester.creation_test_module.infrastructure.mappers.AnswerMapper;
import com.testing_system.tester.creation_test_module.infrastructure.mappers.QuestionMapper;
import com.testing_system.tester.creation_test_module.infrastructure.mappers.TestVersionMapper;
import com.testing_system.tester.creation_test_module.infrastructure.repos.AnswerRepository;
import com.testing_system.tester.creation_test_module.infrastructure.repos.QuestionRepository;
import com.testing_system.tester.creation_test_module.infrastructure.repos.TestVersionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/*
Вторичный адаптер для сущности версии теста
 */
@Service
public class TestVersionSecondAdapter implements TestVersionDrivenUseCase {

    // Работает с тремя репозиториями
    private final TestVersionRepository JpaRepository;
    private final QuestionRepository JpaRepository1;
    private final AnswerRepository JpaRepository2;

    // Работает с тремя мапперами
    private final TestVersionMapper mapper;
    private final QuestionMapper mapper1;
    private final AnswerMapper mapper2;



    public TestVersionSecondAdapter(TestVersionRepository jpaRepository,
                                    QuestionRepository jpaRepository1,
                                    AnswerRepository jpaRepository2,
                                    TestVersionMapper mapper, QuestionMapper mapper1, AnswerMapper mapper2
    )
    {
        JpaRepository = jpaRepository;
        JpaRepository1 = jpaRepository1;
        JpaRepository2 = jpaRepository2;
        this.mapper = mapper;
        this.mapper1 = mapper1;
        this.mapper2 = mapper2;
    }


    @Override
    public List<TestVersion> getAllTestVersions(String currentTestName) {

        var allTestVersions = JpaRepository.findAllVersions(currentTestName);
        if (allTestVersions.isEmpty()) return List.of();

        return allTestVersions.stream().map(mapper::entityToDomain).toList();
    }

    @Override
    public Optional<TestVersion> getVersionMetaData(Integer currentVersionId) {

        var currentVersion = JpaRepository.findById(currentVersionId);
        return currentVersion.map(mapper::entityToDomain);
    }


    @Override
    public Map<Question, List<Answer>> getVersionContent(Integer currentVersionId) {

        var AllQuestions = JpaRepository1.getAllQuestionsByVersionId(currentVersionId);

        return  AllQuestions.stream().collect(Collectors.toMap(mapper1::entityToDomain,
                question -> JpaRepository2.getAllAnswersByQuestionId(question.getQuestionId()).stream().map(mapper2::entityToDomain).toList()));

    }

    @Override
    public void saveTestVersion(TestVersion currentTestVersion, Map<Question, List<Answer>> versionContent) {

        TestVersionEntity currentEntity = mapper.domainToEntity(currentTestVersion);

        var currentContent = versionContent.entrySet().stream().collect(Collectors
                .toMap(entry -> mapper1.domainToEntity(entry.getKey(), currentEntity), Map.Entry::getValue));

        Map<QuestionEntity, List<AnswerEntity>> currentContent1 = currentContent.entrySet().stream().collect(Collectors
                .toMap(Map.Entry::getKey, entry -> entry.getValue().stream().map(
                        answer -> mapper2.domainToEntity(answer, entry.getKey())).toList()));

        JpaRepository.save(currentEntity);
        JpaRepository1.saveAll(currentContent1.keySet().stream().toList());
        JpaRepository2.saveAll(currentContent1.values().stream().flatMap(List::stream).toList());

    }


    @Override
    public void saveTestVersion(TestVersion newMetaData) {

        JpaRepository.save(mapper.domainToEntity(newMetaData));

    }

    @Override
    public void deleteTestVersion(Integer currentTestVersionId) {

        JpaRepository.deleteById(currentTestVersionId);

    }

    @Override
    public List<Answer> getAnswers(List<Integer> currentAnswerIds) {

        var answers = JpaRepository2.findAllById(currentAnswerIds);
        if (answers.isEmpty()) return List.of();

        return answers.stream().map(mapper2::entityToDomain).toList();
    }
}
