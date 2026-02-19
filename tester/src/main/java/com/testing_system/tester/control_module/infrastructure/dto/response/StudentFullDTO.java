package com.testing_system.tester.control_module.infrastructure.dto.response;

import com.testing_system.tester.control_module.core.domain.StudentStatus;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/*
DTO подробного просмотра студент
 */
@NoArgsConstructor
@Setter
public class StudentFullDTO {

    private Integer studentId;

    private String firstName;

    private String lastName;

    private String patronymic;

    private String studentGroup;

    private StudentStatus studStatus;

    private LocalDate startLearningDate;

    public StudentFullDTO(
            Integer currentStudentId,
            String currentFirstName,
            String currentLastName,
            String currentPatronymic,
            String currentStudentGroup,
            StudentStatus currentStatus,
            LocalDate currentDate
    )
    {
        this.studentId = currentStudentId;
        this.firstName = currentFirstName;
        this.lastName = currentLastName;
        this.patronymic = currentPatronymic;
        this.studentGroup = currentStudentGroup;
        this.studStatus = currentStatus;
        this.startLearningDate = currentDate;


    }

    public StudentFullDTO(
            Integer currentStudentId,
            String currentFirstName,
            String currentLastName,
            String currentStudentGroup,
            StudentStatus currentStatus,
            LocalDate currentDate
    )
    {
        this.studentId = currentStudentId;
        this.firstName = currentFirstName;
        this.lastName = currentLastName;
        this.studentGroup = currentStudentGroup;
        this.studStatus = currentStatus;
        this.startLearningDate = currentDate;


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

    public StudentStatus getStudStatus() {
        return studStatus;
    }

    public LocalDate getStartLearningDate() {
        return startLearningDate;
    }
}
