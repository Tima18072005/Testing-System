package com.testing_system.tester.creation_test_module.core.ports.first.impl;

import com.testing_system.tester.creation_test_module.core.domain.Test;
import com.testing_system.tester.creation_test_module.core.ports.first.TestQueryUseCase;
import com.testing_system.tester.creation_test_module.core.ports.first.exceptions.NoTestException;
import com.testing_system.tester.creation_test_module.core.ports.second.TestDrivenUseCase;
import org.springframework.stereotype.Service;

import java.util.List;

/*
Сервис-оркестратор для доступа к данным тестов
 */
@Service
public class TestQueryService implements TestQueryUseCase {

    private final TestDrivenUseCase secondPort;

    // Реализация использует только вторичный порт
    public TestQueryService(TestDrivenUseCase secondPort) {
        this.secondPort = secondPort;
    }


    @Override
    public boolean findTest(String currentTestName) {
        return secondPort.getTest(currentTestName).isPresent();
    }

    @Override
    public Test getTestByName(String currentTestName) {
        return secondPort.getTest(currentTestName).orElseThrow(
                () -> new NoTestException("Test not fond! Test name: " + currentTestName));
    }


    @Override
    public List<Test> getAllTests() {
        return secondPort.getAllTests();
    }


    // Следующие методы можно ускорить для при расширении вторичного порта

    @Override
    public List<Test> getFieldTests(String currentFieldName) {
        return getAllTests().stream().filter(test -> test.getField().equals(currentFieldName)).toList();
    }


    @Override
    public List<Test> getAuthorTests(Integer currentAuthorId) {

        return getAllTests().stream().filter(
                test -> test.getAuthorId().equals(currentAuthorId)).toList();
    }

    @Override
    public List<Test> getCoAuthorTests(Integer currentCoAuthorId) {

        return getAllTests().stream()
                .filter(test -> test.getCo_authorsIds().stream()
                        .anyMatch(id -> id.equals(currentCoAuthorId))).toList();
    }


}
