package com.testing_system.tester.creation_test_module.infrastructure.dto.response;


import java.util.List;
import java.util.Map;

/*
Полное DTO (версия + контент) для подробного просмотра и прохождения теста
 */

public record TestVersionFullDTO(TestVersionDTO versionMetadata, Map<QuestionDTO, List<AnswerDTO>> versionContent) {

}
