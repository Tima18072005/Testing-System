package com.testing_system.tester.control_module.infrastructure.dto.response;

import com.testing_system.tester.control_module.core.domain.EmployeeStatus;

// Полное DTO для чтения сотрудник
public class EmployeeFullDTO {

    private Integer empId;

    private String firstName;

    private String lastName;

    private String patronymic;

    private EmployeeStatus empStatus;


    // Конструктор для сотрудников, у которых есть отчество
    public EmployeeFullDTO (
            Integer currentEmpId,
            String currentFirstName,
            String currentLastName,
            String patronymic,
            EmployeeStatus empStatus
    )
    {
        this.empId = currentEmpId;
        this.firstName = currentFirstName;
        this.lastName = currentLastName;
        this.patronymic = patronymic;
        this.empStatus = empStatus;
    }


    // Конструктор для сотрудников, у которых нет отчества
    public EmployeeFullDTO (
            Integer currentEmpId,
            String currentFirstName,
            String currentLastName,
            EmployeeStatus empStatus

    )
    {
        this.empId = currentEmpId;
        this.firstName = currentFirstName;
        this.lastName = currentLastName;
        this.empStatus = empStatus;

    }


    public Integer getEmpId() {
        return empId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public EmployeeStatus getEmpStatus() {
        return empStatus;
    }


}
