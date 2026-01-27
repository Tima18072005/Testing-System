package com.testing_system.tester.control_module.core.domain;
import java.time.LocalDate;

// Доменная модель студент
public class Student {

    // Id, имя, фамилия, отчество, учебная группа, день рождения

    private final Integer studentId;

    private final String firstName;

    private final String lastName;

    private String patronymic;

    private String studentGroup;

    // Статус студента (обозначает его успеваемость)
    private StudentStatus studStatus;

    // Дата, до которой действует студенческий (обновляется каждый год)
    private LocalDate expirationDate;


    // Конструктор для студентов, у которых есть отчество
    public Student(
            Integer currentStudentId,
            String currentFirstName,
            String currentLastName,
            String currentPatronymic,
            String currentStudentGroup,
            LocalDate currentBirthDay
    )
    {
        this.studentId = currentStudentId;
        this.firstName = currentFirstName;
        this.lastName = currentLastName;
        this.patronymic = currentPatronymic;
        this.studentGroup = currentStudentGroup;
        this.expirationDate = currentBirthDay;

    }

    // Конструктор для студентов, у которых нет отчества
    public Student(
            Integer currentStudentId,
            String currentFirstName,
            String currentLastName,
            String currentStudentGroup,
            LocalDate currentBirthDay
    )
    {
        this.studentId = currentStudentId;
        this.firstName = currentFirstName;
        this.lastName = currentLastName;
        this.studentGroup = currentStudentGroup;
        this.expirationDate = currentBirthDay;

    }


    // Геттеры

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

    public LocalDate getBirthDay() {
        return expirationDate;
    }


    // Сеттер для смены группы
    public void setStudentGroup(String studentGroup) {
        this.studentGroup = studentGroup;
    }

    // Установка новой даты окончания действия студенческого
    private void setNewExpirationDate(LocalDate currentDate){this.expirationDate = currentDate;}

    // Сеттер для установки статуса
    public void setStudStatus(StudentStatus studStatus) {
        this.studStatus = studStatus;
    }
}
