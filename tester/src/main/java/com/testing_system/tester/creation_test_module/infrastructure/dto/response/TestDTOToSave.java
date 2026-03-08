package com.testing_system.tester.creation_test_module.infrastructure.dto.response;

import com.testing_system.tester.testing_attempts_module.infrastructure.dto.response.TestingRulesDTO;

/*
DTO для создания теста
Создать полное DTO для сохранения
 */
public record TestDTOToSave(TestDTO testMetadata, TestingRulesDTO testingRules, TestVersionFullDTO firstVersion) {

}
