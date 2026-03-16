package com.testing_system.tester.testing_attempts_module.infrastructure.dto.response;



/*
DTO для отображения на главной странице и создания
 */

public record TestingRulesDTO(String testName, Integer dayAttempts, Integer allAttempts) {

}
