package com.testing_system.tester.creation_test_module.core.ports.second;


import com.testing_system.tester.creation_test_module.core.domain.Field;
import com.testing_system.tester.creation_test_module.core.domain.Test;

import java.util.List;
import java.util.Optional;

// Вторичный порт для работы с метаданными тестов
public interface TestDrivenInterface {

    public List<Test> getAllTests();

    public Optional<Test> getTest(String currentTestName);

    public void saveTest(Test currentTest);

    public void deleteTest(String currentTestName);
}
