package com.testing_system.tester.creation_test_module.infrastructure.dto.response;

import com.testing_system.tester.creation_test_module.core.domain.VersionStatus;

import java.time.LocalDate;

/*
DTO только с метаданными
Только для отображения на главной странице
 */
public record TestVersionDTO(Integer versionId, String testName, Integer versionAuthor, VersionStatus versionStatus,
                             LocalDate creationDate) {


}
