package com.testing_system.tester.creation_test_module.core.domain;

import java.time.LocalDate;

// Доменная модель версия теста (на неё ссылаются вопросы, используется для проведения тестирований)
public class TestVersion {

    // Id версии, название теста, Id автора версии, дата создания

    private final Integer versionId;

    private final String testName;

    private final Integer versionAuthor;// В диаграмме прямая ссылка на объект Employee, исправить

    // Статус версии (означает готовность)
    // Если при редактировании версии меняется только статус, то черновая версия удаляется из списка
    // Это раелизуется в сервисе
    private final VersionStatus versionStatus;

    private final LocalDate creationDate;

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



}
