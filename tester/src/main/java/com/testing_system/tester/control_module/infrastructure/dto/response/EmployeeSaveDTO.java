package com.testing_system.tester.control_module.infrastructure.dto.response;




import com.testing_system.tester.control_module.core.domain.EmployeeStatus;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 DTO для сохранения сотрудник
 */
@NoArgsConstructor
@Setter
public class EmployeeSaveDTO {

    private Integer empId;

    private String firstName;

    private String lastName;

    private String patronymic;

    private String hashPass;

    // Конструктор для сотрудников, у которых есть отчество
    public EmployeeSaveDTO (
            Integer currentEmpId,
            String currentFirstName,
            String currentLastName,
            String patronymic,
            EmployeeStatus empStatus,
            String currentHashPass
    )
    {
        this.empId = currentEmpId;
        this.firstName = currentFirstName;
        this.lastName = currentLastName;
        this.patronymic = patronymic;
        this.hashPass = currentHashPass;
    }


    // Конструктор для сотрудников, у которых нет отчества
    public EmployeeSaveDTO (
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

    public String getHashPass() {
        return hashPass;
    }
}
