package com.testing_system.tester.creation_test_module.core.ports.first;

import com.testing_system.tester.creation_test_module.core.domain.Test;

import java.util.List;
import java.util.Optional;

// Первичный порт для работы с метданными тестов
public interface TestUseCase {

    // Создание/удаление теста

    public void makeTest(Test currentTest);

    public void deleteTest(String currentTestName);

    // Поиск данных

    public Test getTestByName(String currentTestName);

    public List<Test> getAllTests();

    public List<Test> getAuthorTests(Integer currentAuthorId);

    public List<Test> getCoAuthorTests(Integer currentCoAuthorId);

    // Добавить/убрать соавтора

    public void makeCoAuthor(String currentTestName, Integer currentCoAuthorId);

    public void deleteCoAuthor(String currentTestName, Integer currentCoAuthorId);

    // Изменить размер батча вопросов при прохождении

    public void changeQuestionBatch(Integer newBatchSize);
}
