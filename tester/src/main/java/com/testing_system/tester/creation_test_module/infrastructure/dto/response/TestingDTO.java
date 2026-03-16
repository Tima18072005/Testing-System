package com.testing_system.tester.creation_test_module.infrastructure.dto.response;

import java.util.List;
import java.util.Map;
/*
ДТО версия для прохождения
 */
public record TestingDTO(Map<QuestionDTO, List<AnswerToTestingDTO>> versionContent) {
}
