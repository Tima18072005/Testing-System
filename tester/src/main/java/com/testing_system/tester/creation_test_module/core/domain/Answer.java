package com.testing_system.tester.creation_test_module.core.domain;


// Доменная модель ответ
public class Answer {

    // Id ответа, Id вопроса, описание

    private final Integer answerId;

    private final Integer questionId;

    private final String description;

    // Статус ответа (означает его правильность или неправильность)
    private final AnswerStatus answerStatus;


    // Конструктор
    public Answer(
            Integer currentAnswerId,
            Integer currentQuestionId,
            String currentDescription,
            AnswerStatus currentStatus
    )
    {
        this.answerId = currentAnswerId;
        this.questionId = currentQuestionId;
        this.description = currentDescription;
        this.answerStatus = currentStatus;
    }

    // Геттеры

    public Integer getAnswerId() {
        return answerId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public String getDescription() {
        return description;
    }

    public AnswerStatus getAnswerStatus() {
        return answerStatus;
    }
}
