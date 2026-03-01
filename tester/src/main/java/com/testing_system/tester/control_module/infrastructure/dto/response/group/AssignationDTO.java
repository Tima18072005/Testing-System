package com.testing_system.tester.control_module.infrastructure.dto.response.group;

import java.util.List;

/*
DTO для назначаний
 */
public record AssignationDTO(String fieldName, String testName, List<String> testForDelete) {
}
