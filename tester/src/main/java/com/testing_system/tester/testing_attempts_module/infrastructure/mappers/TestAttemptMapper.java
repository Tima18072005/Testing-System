package com.testing_system.tester.testing_attempts_module.infrastructure.mappers;

import com.testing_system.tester.testing_attempts_module.infrastructure.dto.response.TestAttemptDTO;
import com.testing_system.tester.testing_attempts_module.core.domain.TestAttempt;
import com.testing_system.tester.testing_attempts_module.infrastructure.dto.db.TestAttemptEntity;
import com.testing_system.tester.testing_attempts_module.infrastructure.dto.response.TestAttemptDTOToSave;
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

    public TestAttemptDTO domainToDTO(TestAttempt currentAttempt){
        return new TestAttemptDTO(
                currentAttempt.getAttemptId(),
                currentAttempt.getTesterId(),
                currentAttempt.getTestName(),
                currentAttempt.getMark(),
                currentAttempt.getMark5(), currentAttempt.getDate(), currentAttempt.getIP());
    }

    public TestAttempt dtoToDomain(TestAttemptDTOToSave currentDTO, Integer mark, Integer mark5){
        return new TestAttempt(
                currentDTO.testerId(), currentDTO.testName(), mark, mark5, currentDTO.date(), currentDTO.IP()
                );
    }

}
