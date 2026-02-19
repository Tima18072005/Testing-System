package com.testing_system.tester.control_module.infrastructure.dto.db;

import com.testing_system.tester.control_module.core.domain.EmployeeStatus;
import jakarta.persistence.*;


/*
Репозитарная сущность сотрудник
 */

@Entity
@Table(name = "employees")
public class EmployeeEntity {

    @Id
    @Column(name = "employee_id", nullable = false)
    private Integer empId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "access_level", nullable = false)
    @Enumerated(EnumType.STRING)
    private EmployeeStatus empStatus;

    @Column(name = "hash", nullable = false)
    private String hashPass;

    public EmployeeEntity(){}

    public EmployeeEntity(Integer empId, String firstName, String lastName, EmployeeStatus status, String hashPass) {
        this.empId = empId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = null;
        this.empStatus = status;
        this.hashPass = hashPass;
    }

    public EmployeeEntity(Integer empId, String firstName, String lastName,String patronymic, EmployeeStatus status, String hashPass) {
        this.empId = empId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.empStatus = status;
        this.hashPass = hashPass;
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
}
