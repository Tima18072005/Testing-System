package com.testing_system.tester.control_module.core.domain;

/*
 Доменная модель сотрудник
 Содержит в себе:

    - Id сотрудника
    - Имя + фамилию + отчество (если есть)
    - Статус сотрудника (обозначает уровень доступа)
    - Хешированнный пароль

 */
public class Employee {


    private final Integer empId;

    private final String firstName;

    private final String lastName;

    private String patronymic;

    private EmployeeStatus empStatus;

    private String hashPass;



    // Конструктор для сотрудников, у которых есть отчество
    public Employee (
            Integer currentEmpId,
            String currentFirstName,
            String currentLastName,
            String currentPatronymic,
            String currentHashPass
            )
    {
        this.empId = currentEmpId;
        this.firstName = currentFirstName;
        this.lastName = currentLastName;
        this.patronymic = currentPatronymic;
        this.hashPass = currentHashPass;
    }


    // Конструктор для сотрудников, у которых нет отчества
    public Employee (
            Integer currentEmpId,
            String currentFirstName,
            String currentLastName,
            String currentHashPass
    )
    {
        this.empId = currentEmpId;
        this.firstName = currentFirstName;
        this.lastName = currentLastName;
        this.hashPass = currentHashPass;
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

    public String getHashPass() {
        return hashPass;
    }

    public void setEmpStatus(EmployeeStatus empStatus) {
        this.empStatus = empStatus;
    }

    public void setHashPass(String hashPass) {
        this.hashPass = hashPass;
    }
}
