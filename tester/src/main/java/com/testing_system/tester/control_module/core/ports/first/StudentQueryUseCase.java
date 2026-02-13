package com.testing_system.tester.control_module.core.ports.first;

import com.testing_system.tester.control_module.core.domain.Student;

import java.util.List;
/*
Первичный порт
Задачи:

    - Доступ к данным учётных записей
    - Фильтрация данных при отображении
    - Проверка наличия студента в базе

 */
public interface StudentQueryUseCase {

    public boolean findStudent(Integer currentStudentId);

    public Student getStudentById(Integer currentStudentId);

    public List<Student> getAllStudents();

    public List<Student> getStudentsByGroup(String currentGroupNum);

    public List<Student> getStudentByName(String currentFirstName, String currentSecondName);

    public List<Student> getStudentByFirstName(String currentFirstName);

    public List<Student> getStudentByLastName(String currentSecondName);
}
