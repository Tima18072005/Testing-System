package com.testing_system.tester.creation_test_module.infrastructure.second_adapters;

import com.testing_system.tester.creation_test_module.core.domain.Test;
import com.testing_system.tester.creation_test_module.core.ports.second.TestDrivenUseCase;
import com.testing_system.tester.creation_test_module.infrastructure.mappers.TestMapper;
import com.testing_system.tester.creation_test_module.infrastructure.repos.TestRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/*
Вторичный адаптер для сущности тестов
 */
@Service
public class TestSecondAdapter implements TestDrivenUseCase {

    private final TestRepository JpaRepository;
    private final TestMapper mapper;

    public TestSecondAdapter(TestRepository jpaRepository, TestMapper mapper) {
        JpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Test> getAllTests() {

        var allTests = JpaRepository.findAll();
        if (allTests.isEmpty()) return List.of();

        return allTests.stream().map(mapper::entityToDomain).toList();
    }

    @Override
    public Optional<Test> getTest(String currentTestName) {

        var currentTest = JpaRepository.findById(currentTestName);

        return currentTest.map(mapper::entityToDomain);
    }

    @Override
    public void saveTest(Test currentTest) {

        JpaRepository.save(mapper.domainToEntity(currentTest));

    }

    @Override
    public void deleteTest(String currentTestName) {
        JpaRepository.deleteById(currentTestName);
    }
}
