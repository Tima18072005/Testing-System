package com.testing_system.tester.testing_attempts_module.core.ports.first;

import com.testing_system.tester.testing_attempts_module.core.domain.TestAttempt;
import com.testing_system.tester.testing_attempts_module.core.ports.first.exeptions.NoHistoryException;

import java.util.List;
import java.util.Map;

// Первичный порт для взаимодейсвтия с историей прохождений
public interface AttemptHistoryUseCase {

    // Сохранить/удалить попытку

    public void saveAttemptToHistory(TestAttempt newAttempt);

    public void deleteAttempt(Integer currentAttemptId); // Удаление по id так как нет каскадирования

    // Доступ к данным (могут возвращать пустые списки)

    public List<TestAttempt> getTestingHistory(String currentTestName) throws NoHistoryException;

    public List<TestAttempt> getStudentTestingHistory(String currentTestName, Integer currentStudentId) throws NoHistoryException;

    // Фильтрация переданных ID тестов для отображения главной страницы программы

    public Map.Entry<List<String>, List<String>> testsFilter(Integer currentStudentId, List<String> currentTestsAssignations);

}
