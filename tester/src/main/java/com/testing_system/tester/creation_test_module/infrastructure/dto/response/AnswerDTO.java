package com.testing_system.tester.creation_test_module.infrastructure.dto.response;

import com.testing_system.tester.creation_test_module.core.domain.AnswerStatus;

/*
DTO для создания и отображения
 */
public record AnswerDTO(Integer answerId, Integer questionId, String description, AnswerStatus answerStatus) {

    public AnswerDTO(Integer questionId, String description, AnswerStatus answerStatus)
    {
        this(null, questionId, description,answerStatus);
    }
}
