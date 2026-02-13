package com.testing_system.tester.testing_attempts_module.core.ports.first.impl;



import com.testing_system.tester.testing_attempts_module.core.domain.TestingRules;
import com.testing_system.tester.testing_attempts_module.core.ports.first.TestingRulesUseCase;
import com.testing_system.tester.testing_attempts_module.core.ports.first.exceptions.NoRulesException;
import com.testing_system.tester.testing_attempts_module.core.ports.second.TestAttemptDrivenUseCase;
import com.testing_system.tester.testing_attempts_module.core.ports.second.TestingRulesDrivenUseCase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDate;




/*
Сервис-оркестратор для правил проведения тестирования
 */

public class TestingRulesService implements TestingRulesUseCase {

    // Реализация использует вторичные порты
    private final TestingRulesDrivenUseCase secondPort;
    private final TestAttemptDrivenUseCase attemptSecondPort;
    private static final Logger logger = LoggerFactory.getLogger(TestingRulesService.class);

    public TestingRulesService(TestingRulesDrivenUseCase currentSecondPort, TestAttemptDrivenUseCase attemptSecondPort){
        this.secondPort = currentSecondPort;
        this.attemptSecondPort = attemptSecondPort;
    }

    @Override
    public TestingRules getTestingRules(String currentTestName) {
        return secondPort.getTestRules(currentTestName).orElseThrow(
                () -> new NoRulesException("Testing rules not found! Test name: " + currentTestName));
    }



    @Override
    public void makeTestingRules(TestingRules currentTestRules) {

        if (secondPort.getTestRules(currentTestRules.getTestName()).isPresent())
            throw new IllegalStateException("Error! This rules is exist! Test name: "
                    + currentTestRules.getTestName());

        secondPort.saveRules(currentTestRules);
    }

    /*
    Удалить правила можно только при удалении теста
    Фасад будет вызывать соответсвующие методы
     */
    @Override
    public void deleteTestingRules(String currentTestName) {

        if (secondPort.getTestRules(currentTestName).isEmpty())
            throw new IllegalStateException("Error! You can't delete non-existent rules. Test");

        secondPort.deleteRules(currentTestName);
    }



    //Может кидать NoRulesException
    @Override
    public void changeDayAttempts(String currentTestName, Integer newNumber)  {

        var currentRules = getTestingRules(currentTestName);

        if (newNumber < 1 || newNumber> currentRules.getAllAttempts())
            throw new IllegalArgumentException("Error! Incorrect number %n for day attempts!"
                    .formatted(newNumber));

        currentRules.setDayAttempts(newNumber);
        secondPort.saveRules(currentRules);
        logger.info("Days attempts changed! Test name: {}", currentTestName);

    }

    //Может кидать NoRulesException
    @Override
    public void changeAllAttempts(String currentTestName, Integer newNumber) {

        var currentRules = getTestingRules(currentTestName);

        if (newNumber < 1 || newNumber> 10)
            throw new IllegalArgumentException("Error! Incorrect number %n for all attempts!"
                    .formatted(newNumber));

        currentRules.setAllAttempts(newNumber);
        secondPort.saveRules(currentRules);
        logger.info("All attempts changed! Test name: {}", currentTestName);
    }

    //Может кидать NoRulesException
    @Override
    public boolean canTestingToDay(String currentTestName, Integer currentStudentId) {

        var currentRules = getTestingRules(currentTestName);

        var allAttempts = attemptSecondPort.getAllAttemptsForStudent(currentTestName, currentStudentId);

        if (allAttempts.isEmpty()) return true;

        var toDayAttempts = allAttempts.stream().filter(
                attempt -> attempt.getDate().equals(LocalDate.now())).toList();

        return toDayAttempts.size() < currentRules.getDayAttempts();
    }


    //Может кидать NoRulesException
    @Override
    public boolean isLastAttempt(String currentTestName, Integer currentStudentId) {

        var currentRules = getTestingRules(currentTestName);

        var allAttempts = attemptSecondPort.getAllAttemptsForStudent(currentTestName, currentStudentId);

        if (allAttempts.isEmpty()) return false;

        // При true фасад ставит студенту статус должник
        return currentRules.getAllAttempts().equals(allAttempts.size()) && allAttempts.stream().noneMatch(attempt -> attempt.getMark()>=3);
    }
}
