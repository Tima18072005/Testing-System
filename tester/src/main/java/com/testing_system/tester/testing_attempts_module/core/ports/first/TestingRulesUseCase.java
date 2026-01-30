package com.testing_system.tester.testing_attempts_module.core.ports.first;

import com.testing_system.tester.testing_attempts_module.core.domain.TestingRules;
import com.testing_system.tester.testing_attempts_module.core.ports.first.exeptions.NoRulesException;

import java.util.Optional;

// Первичный порт для работы с правилами теста
public interface TestingRulesUseCase {

    // Создание/удаление правил

    public void makeTestingRules(TestingRules currentTestRules);

    public void deleteTestingRules(String currentTestName);

    // Доступ к данным

    public TestingRules getTestingRules(String currentTestName) throws NoRulesException;

    // Изменение правил

    public void changeDayAttempts(String currentTestName, Integer newNumber) throws NoRulesException;

    public void changeAllAttempts(String currentTestName, Integer newNumber) throws NoRulesException;

    // Валидация перед прохождением (не превышено ли количество попыток)

    public boolean wasTestingToDay(String currentTestName, Integer currentStudentId);

    // Валидация после сохранения (нужно ли менять статус)

    public boolean isLastAttempt(String currentTestName, Integer currentStudentId);
}
