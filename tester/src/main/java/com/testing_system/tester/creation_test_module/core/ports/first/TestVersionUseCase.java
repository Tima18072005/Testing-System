package com.testing_system.tester.creation_test_module.core.ports.first;

import com.testing_system.tester.creation_test_module.core.domain.Answer;
import com.testing_system.tester.creation_test_module.core.domain.Question;
import com.testing_system.tester.creation_test_module.core.domain.TestVersion;

import java.util.List;
import java.util.Map;
import java.util.Optional;

// Первичный порт для работы с версиями теста
public interface TestVersionUseCase {

    // Создание/удаление новой версии

    public void makeNewVersion(
            TestVersion newVersion,
            Map<Question, List<Answer>> newVersionContent
    );

    public void deleteVersion(Integer currentVersionId);

    // Получение данных о версии

    public List<TestVersion> getAllTestVersion(String currentTestName); // Только медаданные

    // По отдельности получение метаданных и списка вопросов/ответов, в сервисе-оркестраторе будет объединённый метод

    public Optional<TestVersion> getVersionMetaDataById(Integer currentVersionId);

    public Map<Question, List<Answer>> getVersionContentById(Integer currentVersionId);

    // Для прохождения теста

    public Map<Question, List<Answer>> getLastUsableVersion(String currentTestName);

    // Сделать черновиком/чистовиком

    public void makeDraft(Integer currentVersionId);

    public void makeReady(Integer currentVersionId);

    // Расчет оценки (нужен репозиторий для доступа к ответам)
    public Integer calculateMark(List<Integer> currentAnswerIds);
}
