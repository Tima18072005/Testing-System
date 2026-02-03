package com.testing_system.tester.testing_attempts_module.core.ports.first.impl;



import com.testing_system.tester.testing_attempts_module.core.domain.TestingRules;
import com.testing_system.tester.testing_attempts_module.core.ports.first.TestingRulesUseCase;
import com.testing_system.tester.testing_attempts_module.core.ports.first.exceptions.NoRulesException;
import com.testing_system.tester.testing_attempts_module.core.ports.second.TestAttemptDrivenUseCase;
import com.testing_system.tester.testing_attempts_module.core.ports.second.TestingRulesDrivenUseCase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDate;




// Класс оркестратор для правил проведения тестирования

public class TestingRulesService implements TestingRulesUseCase {


    private final TestingRulesDrivenUseCase secondPort;
    private final TestAttemptDrivenUseCase attemptSecondPort;
    private static final Logger logger = LoggerFactory.getLogger(TestingRulesService.class);

    public TestingRulesService(TestingRulesDrivenUseCase currentSecondPort, TestAttemptDrivenUseCase attemptSecondPort){
        this.secondPort = currentSecondPort;
        this.attemptSecondPort = attemptSecondPort;
    }


    @Override
    public void makeTestingRules(TestingRules currentTestRules) {

        if (secondPort.getTestRules(currentTestRules.getTestName()).isPresent())
            throw new IllegalStateException("Error! This rules is exist! Test name: "
                    + currentTestRules.getTestName());

        secondPort.saveRules(currentTestRules);
    }

    // Возможно не будет использоваться при использовании Spring Boot
    @Override
    public void deleteTestingRules(String currentTestName) {

        if (secondPort.getTestRules(currentTestName).isEmpty())
            throw new IllegalStateException("Error! You can't delete non-existent rules. Test");

        secondPort.deleteRules(currentTestName);
    }

    @Override
    public TestingRules getTestingRules(String currentTestName) {
        return secondPort.getTestRules(currentTestName).orElseThrow(
                () -> new NoRulesException("Testing rules not found! Test name: " + currentTestName));
    }

    /*
    Может кидать NoRulesException
    */

    @Override
    public void changeDayAttempts(String currentTestName, Integer newNumber)  {

        var currentRules = getTestingRules(currentTestName);

        currentRules.setDayAttempts(newNumber);
        secondPort.saveRules(currentRules);
        logger.info("Days attempts changed! Test name: {}", currentTestName);

    }

    /*
    Может кидать NoRulesException
    */
    @Override
    public void changeAllAttempts(String currentTestName, Integer newNumber) {

        var currentRules = getTestingRules(currentTestName);

        currentRules.setAllAttempts(newNumber);
        secondPort.saveRules(currentRules);
        logger.info("All attempts changed! Test name: {}", currentTestName);
    }

    /*
    Может кидать NoRulesException
    */
    @Override
    public boolean wasTestingToDay(String currentTestName, Integer currentStudentId) {

        var currentRules = getTestingRules(currentTestName);

        var allAttempts = attemptSecondPort.getAllAttemptsForStudent(currentTestName, currentStudentId);

        if (allAttempts.isEmpty()) return false;

        var toDayAttempts = allAttempts.stream().filter(
                attempt -> attempt.getDate().equals(LocalDate.now())).toList();

        return toDayAttempts.size() >= currentRules.getDayAttempts();
    }

    /*
    Может кидать NoRulesException
    */
    @Override
    public boolean isLastAttempt(String currentTestName, Integer currentStudentId) {

        var currentRules = getTestingRules(currentTestName);

        var allAttempts = attemptSecondPort.getAllAttemptsForStudent(currentTestName, currentStudentId);

        if (allAttempts.isEmpty()) return false;

        return currentRules.getAllAttempts().equals(allAttempts.size()) && allAttempts.stream().noneMatch(attempt -> attempt.getMark()>=3);
    }
}
