package com.testing_system.tester.creation_test_module.core.ports.first;

import com.testing_system.tester.creation_test_module.core.domain.Test;

/*
 Первичный порт для работы с метданными тестов
 Задача:

    - Создание и удаление метаданных тестов
    - Изменение батча вопросов для прохождения
    - Добавление/удаление соавторов теста

 */
public interface TestCommandUseCase {

    public void makeTest(Test currentTest);

    public void deleteTest(String currentTestName);

    public void makeCoAuthor(String currentTestName, Integer currentCoAuthorId) ;

    public void deleteCoAuthor(String currentTestName, Integer currentCoAuthorId);// Возможно добавить исключение

    public void changeQuestionBatch(String currentTestName, Integer newBatchSize);
}
