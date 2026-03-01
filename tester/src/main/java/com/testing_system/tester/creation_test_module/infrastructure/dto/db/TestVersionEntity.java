package com.testing_system.tester.creation_test_module.infrastructure.dto.db;

import com.testing_system.tester.creation_test_module.core.domain.VersionStatus;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/*
Репозитарная сущность версия теста
 */
@Entity
@Table(name = "test_versions")
@NoArgsConstructor
@Setter
public class TestVersionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "version_id", nullable = false)
    private Integer versionId;

    // Ссылается на таблицу с тестами, использовать SQL
    @Column(name = "name", nullable = false)
    private String testName;

    @Column(name = "author_id", nullable = false)
    private Integer versionAuthor;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private VersionStatus versionStatus;

    @Column(name = "date_of_creation")
    private LocalDate creationDate;

    public TestVersionEntity(
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
