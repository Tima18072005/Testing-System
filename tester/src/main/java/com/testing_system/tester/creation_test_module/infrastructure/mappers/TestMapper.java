package com.testing_system.tester.creation_test_module.infrastructure.mappers;

import com.testing_system.tester.creation_test_module.core.domain.Test;
import com.testing_system.tester.creation_test_module.infrastructure.dto.db.TestEntity;
import com.testing_system.tester.creation_test_module.infrastructure.dto.response.TestDTO;
import org.springframework.stereotype.Component;

/*
Маппер для сущности теста
 */
@Component
public class TestMapper {

    public Test entityToDomain(TestEntity currentEntity){

        return new Test(
                currentEntity.getTestName(),
                currentEntity.getField(),
                currentEntity.getAuthorId(),
                currentEntity.getQuestionBatch(),
                currentEntity.getCo_authorsIds()
                );
    }

    public TestEntity domainToEntity(Test currentTest){
        return new TestEntity(
                currentTest.getTestName(),
                currentTest.getField(),
                currentTest.getAuthorId(),
                currentTest.getQuestionBatch(),
                currentTest.getCo_authorsIds()
                );
    }

    public Test dtoToDomain(TestDTO currentDTO){

        return new Test(
                currentDTO.getTestName(),
                currentDTO.getField(),
                currentDTO.getAuthorId(),
                currentDTO.getQuestionBatch(),
                currentDTO.getCo_authorsIds());

    }

    public TestDTO domainToDTO(Test currentTest){
        return new TestDTO(
                currentTest.getTestName(),
                currentTest.getField(),
                currentTest.getAuthorId(),
                currentTest.getQuestionBatch(),
                currentTest.getCo_authorsIds());
    }


}
