package com.testing_system.tester.creation_test_module.core.ports.first;

import com.testing_system.tester.creation_test_module.core.domain.Answer;
import com.testing_system.tester.creation_test_module.core.domain.Question;
import com.testing_system.tester.creation_test_module.core.domain.TestVersion;

import java.util.List;
import java.util.Map;


/*
Первичный порт.
Задачи:
    - CRUD-операции для версии
    - Прохождение теста
    - Сделать черновиком/чистовиком
 */
public interface TestVersionUseCase {



    public void makeNewVersion(
            TestVersion newVersion,
            Map<Question, List<Answer>> newVersionContent
    );

    public void deleteVersion(Integer currentVersionId);

    public List<TestVersion> getAllTestVersion(String currentTestName); // Только медаданные

    public TestVersion getVersionMetaDataById(Integer currentVersionId);

    public Map<Question, List<Answer>> getVersionContentById(Integer currentVersionId);

    public Map<Question, List<Answer>> getLastUsableVersion(String currentTestName);

    public void makeDraft(Integer currentVersionId);

    public void makeReady(Integer currentVersionId);


}
