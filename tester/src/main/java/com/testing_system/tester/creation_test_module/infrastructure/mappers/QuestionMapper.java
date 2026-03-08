package com.testing_system.tester.creation_test_module.infrastructure.mappers;

import com.testing_system.tester.creation_test_module.core.domain.Question;
import com.testing_system.tester.creation_test_module.infrastructure.dto.db.QuestionEntity;
import com.testing_system.tester.creation_test_module.infrastructure.dto.db.TestVersionEntity;
import com.testing_system.tester.creation_test_module.infrastructure.dto.response.QuestionDTO;
import org.springframework.stereotype.Component;

/*
Маппер для сущности вопроса
 */
@Component
public class QuestionMapper {

    public QuestionEntity domainToEntity(Question currentQuestion, TestVersionEntity currentEntity){

        return new QuestionEntity(currentQuestion.getQuestionId(), currentEntity, currentQuestion.getDescription());
    }

    public Question entityToDomain(QuestionEntity currentQuestionEntity){

        return new Question(currentQuestionEntity.getQuestionId(), currentQuestionEntity.getVersion(), currentQuestionEntity.getDescription());
    }

    public QuestionDTO domainToDTO(Question currentQuestion){
        return new QuestionDTO(currentQuestion.getQuestionId(), currentQuestion.getVersionId(), currentQuestion.getDescription());
    }
}
