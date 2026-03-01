package com.testing_system.tester.testing_attempts_module.infrastructure.mappers;

import com.testing_system.tester.testing_attempts_module.core.domain.TestingRules;
import com.testing_system.tester.testing_attempts_module.infrastructure.dto.db.TestingRulesEntity;
import org.springframework.stereotype.Component;

/*
Маппер для правил тестирования
 */
@Component
public class TestingRulesMapper {

    public TestingRules entityToDomain(TestingRulesEntity currentRules){

        return new TestingRules(currentRules.getTestName(), currentRules.getDayAttempts(), currentRules.getAllAttempts());
    }

    public TestingRulesEntity domainToEntity(TestingRules currentRules){
        return new TestingRulesEntity( currentRules.getDayAttempts(), currentRules.getAllAttempts());
    }
}
