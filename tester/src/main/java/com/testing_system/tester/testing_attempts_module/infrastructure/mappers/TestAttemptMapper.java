package com.testing_system.tester.testing_attempts_module.infrastructure.mappers;

import com.testing_system.tester.testing_attempts_module.core.domain.TestAttempt;
import com.testing_system.tester.testing_attempts_module.infrastructure.dto.db.TestAttemptEntity;
import org.springframework.stereotype.Component;

/*
Маппер для сущности попытки
 */
@Component
public class TestAttemptMapper {

    public TestAttemptEntity domainToEntity(TestAttempt currentEntity){

        return new TestAttemptEntity(currentEntity.getTesterId(), currentEntity.getTestName(),
                currentEntity.getMark(), currentEntity.getMark5(),currentEntity.getDate(), currentEntity.getIP());

    }

    public TestAttempt entityToDomain(TestAttemptEntity currentEntity){

        return new TestAttempt(currentEntity.getAttemptId(), currentEntity.getTesterId(), currentEntity.getTestName(),
                currentEntity.getMark(), currentEntity.getMark5(),currentEntity.getDate(), currentEntity.getIP());
    }
}
