package com.testing_system.tester.testing_attempts_module.core.ports.second;



import com.testing_system.tester.testing_attempts_module.core.domain.TestAttempt;

import java.util.List;

// Вторичный порт для работы с данными истории попыток

public interface TestAttemptDrivenUseCase {

    public List<TestAttempt> getAllAttemptsForTest(String currentTestName);

    // Метод добавлен сразу для, так-как студенты будут часто обращаться к своим оценкам. Это отображает мои конвенции в действии
    public List<TestAttempt> getAllAttemptsForStudent(String currentTestName, Integer currentStudentId);

    public void saveAttempt(TestAttempt currentAttempt);

    public void deleteAttempt(Integer currentAttemptId);
}
