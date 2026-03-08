package com.testing_system.tester.testing_attempts_module.infrastructure.dto.response;

import java.time.LocalDate;

public record TestAttemptDTOToSave(
        Integer testerId,
        String testName,
        LocalDate date,
        String IP) {

}
