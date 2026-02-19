package com.testing_system.tester.control_module.infrastructure.dto.db;

import com.testing_system.tester.control_module.core.domain.StudentStatus;
import jakarta.persistence.*;

import java.time.LocalDate;

// Репозитарная сущность студент
@Entity
@Table(name = "students")
public class StudentEntity {

    @Id
    @Column(name ="student_id", nullable = false)
    private Integer studentId;

    @Column(name ="first_name", nullable = false)
    private String firstName;

    @Column(name ="last_name", nullable = false)
    private String lastName;

    @Column(name = "patronymic")
    private String patronymic;

    // Первичный ключ в таблице groups, введен через SQL
    @Column(name = "group_name", nullable = false)
    private String studentGroup;

    @Column(name ="student_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private StudentStatus studStatus;

    @Column(name ="start_learning_date", nullable = false)
    private LocalDate startLearningDate;

    public StudentEntity() {}

    public StudentEntity(Integer currentStudentId, String currentFirstName, String currentLastName,
                         String currentPatronymic, String currentStudentGroup, StudentStatus status, LocalDate currentDate)
    {
        this.studentId = currentStudentId;
        this.firstName = currentFirstName;
        this.lastName = currentLastName;
        this.patronymic = currentPatronymic;
        this.studentGroup = currentStudentGroup;
        this.studStatus = status;
        this.startLearningDate = currentDate;

    }

    public StudentEntity(Integer currentStudentId, String currentFirstName, String currentLastName,
                         String currentStudentGroup, StudentStatus status, LocalDate currentDate)
    {
        this.studentId = currentStudentId;
        this.firstName = currentFirstName;
        this.lastName = currentLastName;
        this.patronymic = null;
        this.studentGroup = currentStudentGroup;
        this.studStatus = status;
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
