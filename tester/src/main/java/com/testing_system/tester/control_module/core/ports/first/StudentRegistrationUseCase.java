package com.testing_system.tester.control_module.core.ports.first;

import com.testing_system.tester.control_module.core.domain.Group;
import com.testing_system.tester.control_module.core.domain.Student;

import java.util.List;
import java.util.Optional;

// Первичный порт регистрирования студентов
public interface StudentRegistrationUseCase {

    // Функции для добавления/удаления учёнтых записей

    public void regStudent(Student currentStudent);

    public void makeGroup(Group currentGroup);

    public void deleteStudent(Integer currentStudentId);

    public void deleteGroup(String currentGroupNum);

    // Функции для получения данных

    public Student getStudentById(Integer currentStudentId);

    public List<Student> getAllStudents();

    public List<Student> getStudentsByGroup(String currentGroupNum);

    public List<Student> getStudentByName(String currentFirstName, String currentSecondName);

    public List<Group> getAllGroups();

    public Group getGroupByName(String currentGroupName);
}
