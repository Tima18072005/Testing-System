package com.testing_system.tester.testing_attempts_module.core.ports.first;

import com.testing_system.tester.testing_attempts_module.core.domain.TestingRules;
import com.testing_system.tester.testing_attempts_module.core.ports.first.exceptions.NoRulesException;

/*
 Первичный порт
 Задачи:

    - CRUD правил тестирования
    - Изменение настроек в правилах
    - Методы для проверки выполнения правил
 */
public interface TestingRulesUseCase {

    public TestingRules getTestingRules(String currentTestName);

    public void makeTestingRules(TestingRules currentTestRules);

    public void deleteTestingRules(String currentTestName);

    public void changeDayAttempts(String currentTestName, Integer newNumber);

    public void changeAllAttempts(String currentTestName, Integer newNumber);

    // Валидация перед прохождением (не превышено ли количество попыток)

    public boolean canTestingToDay(String currentTestName, Integer currentStudentId);

    // Валидация после сохранения (нужно ли менять статус)

    public boolean isLastAttempt(String currentTestName, Integer currentStudentId);
}
