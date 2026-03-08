package com.testing_system.tester.creation_test_module.infrastructure.dto.response;

import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Setter
public class TestDTO {

    private String testName;

    private String field;

    private Integer authorId;

    private Integer questionBatch;

    private List<Integer> co_authorsIds;

    // Конструктор при незаданных значениях
    public TestDTO(String currentTestName, String currentField, Integer currentAuthorId){
        this.testName = currentTestName;
        this.field = currentField;
        this.authorId = currentAuthorId;
        this.questionBatch = 10;
        this.co_authorsIds = new ArrayList<>();
        // Автоматически добавлять админа и ответственных преподов
    }


    public TestDTO(String currentTestName, String currentField, Integer currentAuthorId, Integer currentBatch, List<Integer> co_authorsIds){
        this.testName = currentTestName;
        this.field = currentField;
        this.authorId = currentAuthorId;
        this.questionBatch = currentBatch;
        this.co_authorsIds = co_authorsIds;
    }

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
