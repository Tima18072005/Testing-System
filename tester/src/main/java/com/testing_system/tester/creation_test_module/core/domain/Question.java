package com.testing_system.tester.creation_test_module.core.domain;


// Доменная модель вопрос
public class Question {

    // Id вопроса, Id версии, описание вопроса

    private final Integer questionId;

    private final Integer versionId;

    private final String description;

    // Конструктор
    public Question (
            Integer currentQuestionId,
            Integer currentVersionId,
            String currentDescription
    )
    {
        this.questionId = currentQuestionId;
        this.versionId = currentVersionId;
        this.description = currentDescription;
    }


    // Геттеры

    public Integer getQuestionId() {
        return questionId;
    }

    public Integer getVersionId() {
        return versionId;
    }

    public String getDescription() {
        return description;
    }
}
