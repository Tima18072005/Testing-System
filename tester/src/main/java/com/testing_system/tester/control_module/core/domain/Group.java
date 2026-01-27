package com.testing_system.tester.control_module.core.domain;
import java.util.ArrayList;
import java.util.List;

// Доменная модель группы
public class Group {

    // Номер группы
    private String groupNumber;

    // Назначенные тесты
    private List<String> tests;

    // Назначенные дисциплины
    private List<String> fields;


    // Конструктор
    public Group(String currentGroupNumber){

        this.groupNumber = currentGroupNumber;
        this.tests = new ArrayList<>();
        this.fields = new ArrayList<>();
    }


    // Геттеры

    public String getGroupNumber() {
        return groupNumber;
    }

    public List<String> getTests() {
        return tests;
    }

    public List<String> getFields() {
        return fields;
    }


    //Сеттер для номера группы
    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }


    // Функции назначения и удаления тестов и учебных дисциплин

    public void addTest(String currentTestName){
        this.tests.add(currentTestName);
    }

    public void addField(String currentFieldName){
        this.fields.add(currentFieldName);
    }

    public void removeTest(String currentTestName){
        this.tests.remove(currentTestName);
    }

    public void removeField(String currentFieldName){
        this.fields.remove(currentFieldName);
    }


}
