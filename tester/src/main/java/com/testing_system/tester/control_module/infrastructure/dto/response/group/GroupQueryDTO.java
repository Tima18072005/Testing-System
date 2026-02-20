package com.testing_system.tester.control_module.infrastructure.dto.response.group;

/*
 DTO группы для просмота в ленте
 */
public class GroupQueryDTO {

    private String groupNumber;
    private Integer numberOfCourses;

    public GroupQueryDTO(String groupNumber, Integer numberOfCourses) {
        this.groupNumber = groupNumber;
        this.numberOfCourses = numberOfCourses;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public Integer getNumberOfCourses() {
        return numberOfCourses;
    }
}
