package com.testing_system.tester.creation_test_module.infrastructure.mappers;

import com.testing_system.tester.creation_test_module.core.domain.Answer;
import com.testing_system.tester.creation_test_module.infrastructure.dto.db.AnswerEntity;
import com.testing_system.tester.creation_test_module.infrastructure.dto.db.QuestionEntity;
import org.springframework.stereotype.Component;

/*
Маппер для сущности ответ
 */
@Component
public class AnswerMapper {

    public AnswerEntity domainToEntity(Answer currentAnswer, QuestionEntity currentEntity){

        return new AnswerEntity(currentAnswer.getAnswerId(), currentEntity, currentEntity.getDescription(), currentAnswer.getAnswerStatus());
    }

    public Answer entityToDomain(AnswerEntity currentEntity){
        return new Answer(currentEntity.getAnswerId(), currentEntity.getQuestionID(), currentEntity.getDescription(), currentEntity.getAnswerStatus());
    }
}
