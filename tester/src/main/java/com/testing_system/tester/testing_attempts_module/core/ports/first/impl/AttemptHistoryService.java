package com.testing_system.tester.testing_attempts_module.core.ports.first.impl;

import com.testing_system.tester.testing_attempts_module.core.domain.TestAttempt;
import com.testing_system.tester.testing_attempts_module.core.ports.first.AttemptHistoryUseCase;
import com.testing_system.tester.testing_attempts_module.core.ports.first.exceptions.NoHistoryException;
import com.testing_system.tester.testing_attempts_module.core.ports.second.TestAttemptDrivenUseCase;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/*
 Сервис-оркестратор для доступа к истории прохождений
 */
@Service
public class AttemptHistoryService implements AttemptHistoryUseCase {

    // Реализация зависит от вторичного порта
    private final TestAttemptDrivenUseCase secondPort;

    public AttemptHistoryService(TestAttemptDrivenUseCase secondPort) {
        this.secondPort = secondPort;
    }


    @Override
    public TestAttempt getAttemptById(Integer currentTestId) {
        return secondPort.getTestAttemptById(currentTestId).orElseThrow(
                ()-> new NoHistoryException("Error! Attempt is not found! Id: %i"
                        .formatted(currentTestId)));
    }


    @Override
    public List<TestAttempt> getTestingHistory(String currentTestName) {

        var currentTestHistory = secondPort.getAllAttemptsForTest(currentTestName);

        if (currentTestHistory.isEmpty()) throw new NoHistoryException(
                "Testing History is not found! Test name: " + currentTestName);

        return currentTestHistory;
    }

    // Перегрузка метода для студента
    @Override
    public List<TestAttempt> getTestingHistory(String currentTestName, Integer currentStudentId) {

        var currentStudentHistory = secondPort.getAllAttemptsForStudent(currentTestName, currentStudentId);

        if (currentStudentHistory.isEmpty()) throw new NoHistoryException(
                "Testing History is not found! Test name: " + currentTestName
                        + ". Student ID: " + currentStudentId);

        return currentStudentHistory;
    }

    @Override
    public void saveAttemptToHistory(TestAttempt newAttempt) {

        if (secondPort.getTestAttemptById(newAttempt.getAttemptId()).isPresent())
            throw new IllegalArgumentException("Error! You cant save attempt with same ids! Id: %i"
                    .formatted(newAttempt.getAttemptId()));

        secondPort.saveAttempt(newAttempt);
    }

    @Override
    public void deleteAttemptById(Integer currentTestId) {
        if (secondPort.getTestAttemptById(currentTestId).isEmpty())
            throw new IllegalArgumentException("Error! You can't delete non-existent attempt! Id: %i"
                    .formatted(currentTestId));
    }

    @Override
    public void deleteAttemptHistory(String currentTestName) {

        var currentHistory = secondPort.getAllAttemptsForTest(currentTestName);

        if (currentHistory.isEmpty())
            throw new IllegalStateException("Error! You can't delete non-exist history! Test name: "
                    + currentTestName);

        List<Integer> currentIDS = currentHistory.stream().map(TestAttempt::getAttemptId).toList();

        for (Integer id: currentIDS) secondPort.deleteAttempt(id);
    }

    @Override
    public Map.Entry<List<String>, List<String>> testsFilter(Integer currentStudentId, List<String> currentTestsAssignations) {

        // Хеш-мапа (ключ - имя теста, значение - список с прохождениями)
        Map<String, List<TestAttempt>> allAttempts = currentTestsAssignations.stream()
                .collect(Collectors.toMap(
                        assign-> assign,
                        assign -> secondPort.getAllAttemptsForStudent(assign, currentStudentId)));


        List<String> passedTests = allAttempts.entrySet().stream()
                .filter(entry ->
                        entry.getValue().stream().anyMatch(
                                attempt -> attempt.getMark5()>=3))
                .map(Map.Entry::getKey).toList();

        List<String> failedTests = allAttempts.entrySet().stream()
                .filter(entry -> entry.getValue().stream().noneMatch(
                        attempt -> attempt.getMark5()>=3)).map(Map.Entry::getKey).toList();

        return Map.entry(passedTests, failedTests);
    }
}
