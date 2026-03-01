package com.testing_system.tester.creation_test_module.infrastructure.dto.db;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/*
Репозитарная сущность тест
 */
@Entity
@Table(name = "tests")
@NoArgsConstructor
@Setter
public class TestEntity {

    @Id
    @Column(name = "test_name", nullable = false)
    private String testName;

    // Добавить первичный ключ через SQL
    @Column(name = "field_name", nullable = false)
    private String field;

    @Column(name = "author_id", nullable = false)
    private Integer authorId;

    @Column(name = "questions_to_testing", nullable = false)
    private Integer questionBatch;

    @ElementCollection
    @CollectionTable(name = "test_and_so_authors", joinColumns = @JoinColumn(name = "test_name"))
    @Column(name = "co_author_id")
    private List<Integer> co_authorsIds;


    public TestEntity(String currentTestName, String currentField, Integer currentAuthorId, Integer currentBatch, List<Integer> co_authorsIds){
        this.testName = currentTestName;
        this.field = currentField;
        this.authorId = currentAuthorId;
        this.questionBatch = currentBatch;
        this.co_authorsIds = co_authorsIds;
    }

    // Геттеры

    public String getTestName() {
        return testName;
    }

    public String getField() {
        return field;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public Integer getQuestionBatch() { return questionBatch;}

    public List<Integer> getCo_authorsIds() {
        return co_authorsIds;
    }
}
