package com.testing_system.tester.control_module.infrastructure.dto.response.group;

import java.util.List;

/*
DTO группа для просмотра
 */
public class GroupFullDTO {

    private String groupNumber;

    private Integer numberOfCourses;

    private List<String> tests;

    private List<String> fields;

    public GroupFullDTO(String currentGroupNumber, Integer currentNumberOfCourses,  List<String> currentTests, List<String> currentFields){

        this.groupNumber = currentGroupNumber;
        this.numberOfCourses = currentNumberOfCourses;
        this.tests = currentTests;
        this.fields = currentFields;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public Integer getNumberOfCourses() {
        return numberOfCourses;
    }

    public List<String> getTests() {
        return tests;
    }

    public List<String> getFields() {
        return fields;
    }
}
