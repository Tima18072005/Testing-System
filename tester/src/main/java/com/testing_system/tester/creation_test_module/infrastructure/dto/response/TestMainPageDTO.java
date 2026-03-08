package com.testing_system.tester.creation_test_module.infrastructure.dto.response;


import com.testing_system.tester.testing_attempts_module.infrastructure.dto.response.TestingRulesDTO;


import java.util.List;

/*
DTO главная страница с данными теста
Отображение теста
 */

public record TestMainPageDTO(TestDTO testMetadata, TestingRulesDTO testingRules, List<TestVersionDTO> versionsList) {

}
