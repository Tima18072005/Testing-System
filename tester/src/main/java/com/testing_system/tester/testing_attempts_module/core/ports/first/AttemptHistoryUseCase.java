package com.testing_system.tester.testing_attempts_module.core.ports.first;

import com.testing_system.tester.testing_attempts_module.core.domain.TestAttempt;
import com.testing_system.tester.testing_attempts_module.core.ports.first.exceptions.NoHistoryException;

import java.util.List;
import java.util.Map;

/*
 Первичный порт
 Задачи:

    - CRUD истории прохождений
    - Фильтрация тестов на основе истории прохождений для отображения студенту
 */
public interface AttemptHistoryUseCase {

    public TestAttempt getAttemptById(Integer currentTestId);

    public List<TestAttempt> getTestingHistory(String currentTestName);

    // Перегрузка метода для студента
    public List<TestAttempt> getTestingHistory(String currentTestName, Integer currentStudentId);

    public void saveAttemptToHistory(TestAttempt newAttempt);

    public void deleteAttemptById(Integer currentTestId);

    public void deleteAttemptHistory(String currentTestName);

    public Map.Entry<List<String>, List<String>> testsFilter(Integer currentStudentId, List<String> currentTestsAssignations);

}
