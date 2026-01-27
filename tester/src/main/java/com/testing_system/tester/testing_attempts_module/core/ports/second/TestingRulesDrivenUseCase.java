package com.testing_system.tester.testing_attempts_module.core.ports.second;



import com.testing_system.tester.testing_attempts_module.core.domain.TestingRules;


import java.util.Optional;

// Вторичный порт для работы с правилами прохождения теста

public interface TestingRulesDrivenUseCase {

    public Optional<TestingRules> getTestRules(String currentTestName);

    public void saveRules(TestingRules currentRules);

    public void deleteRules(String currentTestName);
}
