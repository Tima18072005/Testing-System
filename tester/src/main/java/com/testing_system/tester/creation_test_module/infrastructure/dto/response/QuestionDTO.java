package com.testing_system.tester.creation_test_module.infrastructure.dto.response;



/*
DTO для создания и отображения
 */

public record QuestionDTO(Integer questionId, Integer versionId, String description) {

    // Конструктор

    public QuestionDTO(Integer currentVersionId, String currentDescription)
    {
        this(null, currentVersionId, currentDescription);
    }



}
