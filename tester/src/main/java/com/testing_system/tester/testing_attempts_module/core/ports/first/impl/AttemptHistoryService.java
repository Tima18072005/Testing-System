package com.testing_system.tester.testing_attempts_module.core.ports.first.impl;

import com.testing_system.tester.testing_attempts_module.core.domain.TestAttempt;
import com.testing_system.tester.testing_attempts_module.core.ports.first.AttemptHistoryUseCase;
import com.testing_system.tester.testing_attempts_module.core.ports.first.exeptions.NoHistoryException;
import com.testing_system.tester.testing_attempts_module.core.ports.second.TestAttemptDrivenUseCase;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


// Класс-оркестратор для доступа к истории прохождений
public class AttemptHistoryService implements AttemptHistoryUseCase {

    private  final TestAttemptDrivenUseCase secondPort;

    public AttemptHistoryService(TestAttemptDrivenUseCase secondPort) {
        this.secondPort = secondPort;
    }

    @Override
    public void saveAttemptToHistory(TestAttempt newAttempt) {
        secondPort.saveAttempt(newAttempt);
    }

    @Override
    public void deleteAttempt(Integer currentAttemptId) {
        secondPort.deleteAttempt(currentAttemptId);
    }

    @Override
    public List<TestAttempt> getTestingHistory(String currentTestName) throws NoHistoryException {

        var currentTestHistory = secondPort.getAllAttemptsForTest(currentTestName);

        if (currentTestHistory.isEmpty()) throw new NoHistoryException(
                "Testing History is not found! Test name: " + currentTestName);

        return currentTestHistory;
    }

    @Override
    public List<TestAttempt> getStudentTestingHistory(String currentTestName, Integer currentStudentId) throws NoHistoryException {

        var currentStudentHistory = secondPort.getAllAttemptsForStudent(currentTestName, currentStudentId);

        if (currentStudentHistory.isEmpty()) throw new NoHistoryException(
                "Testing History is not found! Test name: " + currentTestName);

        return currentStudentHistory;
    }

    @Override
    public Map.Entry<List<String>, List<String>> testsFilter(Integer currentStudentId, List<String> currentTestsAssignations) {

        Map<String, List<TestAttempt>> allAttempts = currentTestsAssignations.stream().collect(Collectors.toMap(
                s -> s,
                s -> secondPort.getAllAttemptsForStudent(s, currentStudentId)));

        // Разобрать лучше Stream Api и понять правильность конвенции о вторичных портах
        List<String> passedTests = allAttempts.entrySet().stream()
                .filter(entry ->
                        entry.getValue().stream().anyMatch(
                                attempt -> attempt.getMark()>=3))
                .map(Map.Entry::getKey).toList();

        List<String> failedTests = allAttempts.entrySet().stream()
                .filter(entry -> entry.getValue().stream().noneMatch(
                        attempt -> attempt.getMark()>=3)).map(Map.Entry::getKey).toList();

        return Map.entry(passedTests, failedTests);
    }
}
