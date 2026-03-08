package com.testing_system.tester.creation_test_module.infrastructure.dto.response;

import com.testing_system.tester.creation_test_module.core.domain.Test;

import java.util.List;

/*
Главная страница сотрудника
 */
public class EmployeeMainPageDTO {

    private List<TestDTO> authorTests;

    private List<TestDTO> co_authorTests;

    public EmployeeMainPageDTO(List<TestDTO> currentAuthorTests, List<TestDTO> currentCoAuthorsTest){

        this.authorTests = currentAuthorTests;
        this.co_authorTests = currentCoAuthorsTest;
    }
}
