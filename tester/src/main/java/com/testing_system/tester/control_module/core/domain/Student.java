package com.testing_system.tester.control_module.core.domain;


import java.time.LocalDate;
import java.time.Month;

/*
 Доменная модель студент
 Содержит в себе:

    - Id студента (номер студенческого)
    - Имя + фамилия + отчество (если есть)
    - Номер группы
    - Статус студента (обозначает успеваемость)

 */
public class Student {

    private final Integer studentId;

    private final String firstName;

    private final String lastName;

    private String patronymic;

    private String studentGroup;

    private StudentStatus studStatus;

    private LocalDate startLearningDate;


    //Первые два конструктора для первоначального создания


    // Конструктор для студентов, у которых есть отчество
    public Student(
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
        this.studStatus = StudentStatus.ACTIVE;
        this.startLearningDate = LocalDate.of(LocalDate.now().getYear(), Month.AUGUST,31);


    }

    // Конструктор для студентов, у которых нет отчества
    public Student(
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
        this.studStatus = StudentStatus.ACTIVE;
        this.startLearningDate = LocalDate.of(LocalDate.now().getYear(), Month.AUGUST,31);

    }

    // Эти конструкторы для создания сущности на основе сохраненной сущности в БД

    public Student(
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

    public Student(
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

    public void setStudentGroup(String studentGroup) {
        this.studentGroup = studentGroup;
    }

    public void setStartLearningDate() {
        this.startLearningDate = LocalDate.of(LocalDate.now().getYear(), Month.AUGUST,31);
    }

    public void setStudStatus(StudentStatus studStatus) {
        this.studStatus = studStatus;
    }

}
