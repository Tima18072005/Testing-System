package com.testing_system.tester.control_module.core.ports.first.impl;

import com.testing_system.tester.control_module.core.domain.Group;
import com.testing_system.tester.control_module.core.domain.Student;
import com.testing_system.tester.control_module.core.ports.first.StudentQueryUseCase;
import com.testing_system.tester.control_module.core.ports.first.exceptions.NoStudentException;
import com.testing_system.tester.control_module.core.ports.second.StudentDrivenUseCase;

import java.util.List;

/*
Сервис-оркестратор для доступа к данным учетных записей студентов и фильтрации
 */
public class StudentQueryService implements StudentQueryUseCase{

    // Реализация использует только вторичный порт
    private final StudentDrivenUseCase secondPort;

    public StudentQueryService(StudentDrivenUseCase secondPort) {
        this.secondPort = secondPort;
    }


    @Override
    public boolean findStudent(Integer currentStudentId) {
        return secondPort.getStudent(currentStudentId).isPresent();
    }

    @Override
    public Student getStudentById(Integer currentStudentId) {

        return secondPort.getStudent(currentStudentId).orElseThrow(
                () -> new NoStudentException("Student not found! Student ID: "
                        + currentStudentId));
    }

    /*
    При отстутствии студентов контроллер вернёт соответствующий ДТО
     */
    @Override
    public List<Student> getAllStudents() {
        return secondPort.getAllStudents();
    }


    // Следующие методы могут быть ускорены при помощи изменения реализации и расширения вторичных портов

    /*
   При отстутствии студентов контроллер вернёт соответствующий ДТО
     */
    @Override
    public List<Student> getStudentsByGroup(String currentGroupNum) {

        return getAllStudents().stream()
                .filter(student -> student.getStudentGroup().equals(currentGroupNum)).toList();
    }


    /*
   При отстутствии студентов контроллер вернёт соответствующий ДТО
     */
    @Override
    public List<Student> getStudentByName(String currentFirstName, String currentSecondName) {
        return getAllStudents().stream().filter(student -> student.getFirstName().equals(currentFirstName))
                .filter(student -> student.getLastName().equals(currentSecondName))
                .toList();
    }

    /*
   При отстутствии студентов контроллер вернёт соответствующий ДТО
     */
    @Override
    public List<Student> getStudentByFirstName(String currentFirstName) {
        return getAllStudents().stream().filter(
                student -> student.getFirstName()
                        .equals(currentFirstName)).toList();
    }

    /*
   При отстутствии студентов контроллер вернёт соответствующий ДТО
     */
    @Override
    public List<Student> getStudentByLastName(String currentSecondName) {
        return getAllStudents().stream().filter(
                student -> student.getFirstName()
                        .equals(currentSecondName)).toList();
    }

}
