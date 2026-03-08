package com.testing_system.tester.creation_test_module.infrastructure.dto.response;

import java.util.List;

/*
DTO главная страница студента
 */
public class StudentMainPageDTO {

    private List<String> passedTests;

    private List<String> failedTests;

    public StudentMainPageDTO(List<String> passedTests, List<String> failedTests){
        this.failedTests = failedTests;
        this.passedTests = passedTests;
    }

    public List<String> getPassedTests() {
        return passedTests;
    }

    public List<String> getFailedTests() {
        return failedTests;
    }
}
