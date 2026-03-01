package com.testing_system.tester.creation_test_module.core.domain;

import java.time.LocalDate;

// Доменная модель версия теста (на неё ссылаются вопросы, используется для проведения тестирований)
public class TestVersion {

    // Id версии, название теста, Id автора версии, дата создания

    private final Integer versionId;

    private final String testName;

    private final Integer versionAuthor;

    private VersionStatus versionStatus;

    private LocalDate creationDate;

    // Конструктор
    public TestVersion(
            Integer currentVersionId,
            String currentTestName,
            Integer currentVersionAuthor,
            VersionStatus currentStatus,
            LocalDate currentCreationDate
    )
    {
        this.versionId = currentVersionId;
        this.testName = currentTestName;
        this.versionAuthor = currentVersionAuthor;
        this.versionStatus = currentStatus;
        this.creationDate = currentCreationDate;

    }

    // Геттеры

    public Integer getVersionId() {
        return versionId;
    }

    public String getTestName() {
        return testName;
    }

    public Integer getVersionAuthor() {
        return versionAuthor;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public VersionStatus getVersionStatus() {
        return versionStatus;
    }

    // Установка статуса

    public void setDraft(){ this.versionStatus = VersionStatus.DRAFT;}

    public void setReady() {this.versionStatus = VersionStatus.READY;}

    // Установка даты

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}
