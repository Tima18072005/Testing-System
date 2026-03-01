package com.testing_system.tester.testing_attempts_module.infrastructure.second_adapters;

import com.testing_system.tester.testing_attempts_module.core.domain.TestingRules;
import com.testing_system.tester.testing_attempts_module.core.ports.second.TestingRulesDrivenUseCase;
import com.testing_system.tester.testing_attempts_module.infrastructure.mappers.TestingRulesMapper;
import com.testing_system.tester.testing_attempts_module.infrastructure.repos.TestingRulesRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TestingRulesSecondAdapter implements TestingRulesDrivenUseCase {

    private final TestingRulesRepository JPARepository;
    private final TestingRulesMapper mapper;

    public TestingRulesSecondAdapter(TestingRulesRepository jpaRepository, TestingRulesMapper mapper) {
        this.JPARepository = jpaRepository;
        this.mapper = mapper;
    }


    @Override
    public Optional<TestingRules> getTestRules(String currentTestName) {

        var currentRules = JPARepository.findById(currentTestName);

        return currentRules.map(mapper::entityToDomain);

    }

    @Override
    public void saveRules(TestingRules currentRules) {
        JPARepository.save(mapper.domainToEntity(currentRules));
    }

    @Override
    public void deleteRules(String currentTestName) {

        JPARepository.deleteById(currentTestName);

    }
}
