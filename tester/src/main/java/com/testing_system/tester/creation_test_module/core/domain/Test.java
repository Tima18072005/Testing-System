package com.testing_system.tester.creation_test_module.core.domain;

import java.util.ArrayList;
import java.util.List;

// Доменная модель тест (метаданные)
public class Test {

    // Название, название учебной дисциплины, Id автора, Id соавторов

    private final String testName;

    private final String field;

    private final Integer authorId;

    private Integer questionBatch;// Добавил батч для количества вопросов при нахождении теста

    private List<Integer> co_authorsIds;

    // Конструктор для первого создания
    public Test(String currentTestName, String currentField, Integer currentAuthorId){
        this.testName = currentTestName;
        this.field = currentField;
        this.authorId = currentAuthorId;
        this.questionBatch = 10;// Значение по умолчанию
        this.co_authorsIds = new ArrayList<>();
    }

    // Конструктор для доступа
    public Test(String currentTestName, String currentField, Integer currentAuthorId, Integer currentBatch, List<Integer> co_authorsIds){
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

    // Добавление и удаление соавторов

    public void addCoAuthor(Integer currentCoAuthorId){
        co_authorsIds.add(currentCoAuthorId);
    }

    public void removeCoAuthor(Integer currentCoAuthorId){
        co_authorsIds.remove(currentCoAuthorId);
    }

    // Установка количества вопросов, которые будут отображены в тесте при прохождении

    public void setQuestionBatch(Integer questionBatch) { this.questionBatch = questionBatch;}
}
