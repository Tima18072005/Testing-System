package com.testing_system.tester.creation_test_module.core.ports.second;

import com.testing_system.tester.creation_test_module.core.domain.Answer;
import com.testing_system.tester.creation_test_module.core.domain.Question;
import com.testing_system.tester.creation_test_module.core.domain.TestVersion;

import java.util.List;
import java.util.Map;
import java.util.Optional;

// Вторичный порт для работы с данными версий тестов

public interface TestVersionDrivenUseCase {

    // Получение информации

    public List<TestVersion> getAllTestVersions(String currentTestName);

    public Optional<TestVersion> getVersionMetaData(Integer currentVersionId);

    public Map<Question, List<Answer>> getVersionContent(Integer currentVersionId);

    // Сохранение и удаление

    public void saveTestVersion(TestVersion currentTestVersion, Map<Question, List<Answer>> versionContent);

    public void changeMetadata(TestVersion newMetaData);

    public void deleteTestVersion(Integer currentTestVersionId);

}
