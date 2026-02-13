package com.testing_system.tester.control_module.core.domain;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/*
 Доменная модель группы
 Содержит в себе:

    - Имя + номер группы
    - Количество лет обучения
    - Дату начала обучения (всегда 31.08.xxxx, меняется только год)
    - Информацию о назначенных группе предметах и тестах (списки id)

 */
public class Group {


    private final String groupNumber;

    private final Integer numberOfCourses;

    private List<String> tests;

    private List<String> fields;


    public Group(String currentGroupNumber, Integer currentNumberOfCourses){

        this.groupNumber = currentGroupNumber;
        this.numberOfCourses = currentNumberOfCourses;
        this.tests = new ArrayList<>();
        this.fields = new ArrayList<>();
    }

    // Конструктор без указания курсов
    public Group(String currentGroupNumber){

        this.groupNumber = currentGroupNumber;
        this.numberOfCourses = 4;
        this.tests = new ArrayList<>();
        this.fields = new ArrayList<>();
    }


    public String getGroupNumber() {
        return groupNumber;
    }

    public List<String> getTests() {
        return tests;
    }

    public List<String> getFields() {
        return fields;
    }

    public Integer getNumberOfCourses() {
        return numberOfCourses;
    }

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
