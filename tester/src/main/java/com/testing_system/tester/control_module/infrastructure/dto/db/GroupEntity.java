package com.testing_system.tester.control_module.infrastructure.dto.db;

import jakarta.persistence.*;

import java.util.List;

// Репозитарная сущность группа
@Entity
@Table(name = "groups")
public class GroupEntity {

    @Id
    @Column(name = "name", nullable = false)
    private String groupNumber;

    @Column(name = "number_of_courses", nullable = false)
    private Integer numberOfCourses;

    @ElementCollection
    @CollectionTable(name = "groups_and_tests", joinColumns = @JoinColumn(name = "group_name"))
    @Column(name = "test_name")
    private List<String> tests;

    @ElementCollection
    @CollectionTable(name = "groups_and_fields", joinColumns = @JoinColumn(name = "group_name"))
    @Column(name = "field_name")
    private List<String> fields;

    public GroupEntity(){}


    // Конструктор с назачениями
    public GroupEntity(String groupNumber, Integer numberOfCourses, List<String> currentTests, List<String> currentFields){
        this.groupNumber = groupNumber;
        this.numberOfCourses = numberOfCourses;
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
