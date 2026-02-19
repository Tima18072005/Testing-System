package com.testing_system.tester.control_module.infrastructure.dto.response;

import com.testing_system.tester.control_module.core.domain.StudentStatus;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Month;

/*
DTO для создания студента
 */
@NoArgsConstructor
@Setter
public class StudentSaveDTO {

    private Integer studentId;

    private String firstName;

    private String lastName;

    private String patronymic;

    private String studentGroup;

    public StudentSaveDTO(
            Integer currentStudentId,
            String currentFirstName,
            String currentLastName,
            String currentPatronymic,
            String currentStudentGroup
    )
    {
        this.studentId = currentStudentId;
        this.firstName = currentFirstName;
        this.lastName = currentLastName;
        this.patronymic = currentPatronymic;
        this.studentGroup = currentStudentGroup;



    }

    // Конструктор для студентов, у которых нет отчества
    public StudentSaveDTO(
            Integer currentStudentId,
            String currentFirstName,
            String currentLastName,
            String currentStudentGroup

    )
    {
        this.studentId = currentStudentId;
        this.firstName = currentFirstName;
        this.lastName = currentLastName;
        this.studentGroup = currentStudentGroup;

    }

    public Integer getStudentId() {
        return studentId;
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

    public String getStudentGroup() {
        return studentGroup;
    }
}
