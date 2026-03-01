package com.testing_system.tester.testing_attempts_module.infrastructure.second_adapters;

import com.testing_system.tester.testing_attempts_module.core.domain.TestAttempt;
import com.testing_system.tester.testing_attempts_module.core.ports.second.TestAttemptDrivenUseCase;
import com.testing_system.tester.testing_attempts_module.infrastructure.mappers.TestAttemptMapper;
import com.testing_system.tester.testing_attempts_module.infrastructure.repos.TestAttemptRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TestAttemptSecondAdapter implements TestAttemptDrivenUseCase {

    private final TestAttemptRepository JpaRepository;
    private final TestAttemptMapper mapper;

    public TestAttemptSecondAdapter(TestAttemptRepository jpaRepository, TestAttemptMapper mapper) {
        JpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<TestAttempt> getTestAttemptById(Integer currentId) {

        var testAttempt = JpaRepository.findById(currentId);

        return testAttempt.map(mapper::entityToDomain);
    }

    @Override
    public List<TestAttempt> getAllAttemptsForTest(String currentTestName) {

        var testHistory = JpaRepository.findAll();

        if (testHistory.isEmpty()) return List.of();

        return testHistory.stream().map(mapper::entityToDomain).toList();

    }

    @Override
    public List<TestAttempt> getAllAttemptsForStudent(String currentTestName, Integer currentStudentId) {

        var testHistory = JpaRepository.findAll();

        if (testHistory.isEmpty()) return List.of();

        return testHistory.stream().filter(attempt -> attempt.getTesterId().equals(currentStudentId)).map(mapper::entityToDomain).toList();
    }

    @Override
    public void saveAttempt(TestAttempt currentAttempt) {
        JpaRepository.save(mapper.domainToEntity(currentAttempt));
    }

    @Override
    public void deleteAttempt(Integer currentAttemptId) {
        JpaRepository.deleteById(currentAttemptId);
    }
}
