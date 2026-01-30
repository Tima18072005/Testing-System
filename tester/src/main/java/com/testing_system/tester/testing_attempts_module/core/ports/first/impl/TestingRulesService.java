package com.testing_system.tester.testing_attempts_module.core.ports.first.impl;



import com.testing_system.tester.testing_attempts_module.core.domain.TestingRules;
import com.testing_system.tester.testing_attempts_module.core.ports.first.TestingRulesUseCase;
import com.testing_system.tester.testing_attempts_module.core.ports.first.exeptions.NoRulesException;
import com.testing_system.tester.testing_attempts_module.core.ports.second.TestAttemptDrivenUseCase;
import com.testing_system.tester.testing_attempts_module.core.ports.second.TestingRulesDrivenUseCase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDate;
import java.util.NoSuchElementException;



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
        secondPort.saveRules(currentTestRules);
    }

    @Override
    public void deleteTestingRules(String currentTestName) {
        secondPort.deleteRules(currentTestName);
    }

    @Override
    public TestingRules getTestingRules(String currentTestName) throws NoRulesException{
        return secondPort.getTestRules(currentTestName).orElseThrow(
                () -> new NoRulesException("Testing rules not found! Test name: " + currentTestName));
    }

    // Можно использовать предыдущую функцию
    @Override
    public void changeDayAttempts(String currentTestName, Integer newNumber) throws NoRulesException {

        var currentRules = secondPort.getTestRules(currentTestName).orElseThrow(
                () -> new NoRulesException("Testing rules not found! Test name: " + currentTestName));

        currentRules.setDayAttempts(newNumber);
        secondPort.saveRules(currentRules);
        logger.info("Days attempts changed! Test name: {}", currentTestName);

    }

    @Override
    public void changeAllAttempts(String currentTestName, Integer newNumber) throws NoRulesException {

        var currentRules = secondPort.getTestRules(currentTestName).orElseThrow(
                () -> new NoRulesException("Testing rules not found! Test name: " + currentTestName));

        currentRules.setAllAttempts(newNumber);
        secondPort.saveRules(currentRules);
        logger.info("All attempts changed! Test name: {}", currentTestName);
    }

    @Override
    public boolean wasTestingToDay(String currentTestName, Integer currentStudentId) {

        var currentRules = secondPort.getTestRules(currentTestName).orElseThrow(
                () -> new NoSuchElementException("Testing rules not found! Test name: " + currentTestName));

        var allAttempts = attemptSecondPort.getAllAttemptsForStudent(currentTestName, currentStudentId);

        if (allAttempts.isEmpty()) return true;

        var dayAttempts = allAttempts.stream().filter(
                attempt -> attempt.getDate().equals(LocalDate.now())).toList();

        return dayAttempts.size() < currentRules.getDayAttempts();
    }

    @Override
    public boolean isLastAttempt(String currentTestName, Integer currentStudentId) {

        var currentRules = secondPort.getTestRules(currentTestName).orElseThrow(
                () -> new NoSuchElementException("Testing rules not found! Test name: " + currentTestName));

        var allAttempts = attemptSecondPort.getAllAttemptsForStudent(currentTestName, currentStudentId);

        if (allAttempts.isEmpty()) return false;

        return currentRules.getAllAttempts().equals(allAttempts.size()) && allAttempts.stream().noneMatch(attempt -> attempt.getMark()>=3);
    }
}
