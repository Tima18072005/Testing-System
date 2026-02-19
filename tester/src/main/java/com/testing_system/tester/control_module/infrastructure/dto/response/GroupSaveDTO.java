package com.testing_system.tester.control_module.infrastructure.dto.response;

import lombok.NoArgsConstructor;
import lombok.Setter;

/*
Дто для сохранения группа
 */
@NoArgsConstructor
@Setter
public class GroupSaveDTO {

    private String groupNumber;
    private Integer numberOfCourses;

    public GroupSaveDTO(String groupNumber, Integer numberOfCourses) {
        this.groupNumber = groupNumber;
        this.numberOfCourses = numberOfCourses;
    }

    public GroupSaveDTO(String groupNumber) {
        this.groupNumber = groupNumber;
        this.numberOfCourses = null;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public Integer getNumberOfCourses() {
        return numberOfCourses;
    }
}
