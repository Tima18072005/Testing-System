package com.testing_system.tester.control_module.core.ports.second;

import com.testing_system.tester.control_module.core.domain.Student;
import java.util.List;
import java.util.Optional;

/*
 Вторичный порт для работы с данными студента
 Задачи:

    - CRUD-операции
    - Сохранение/удаление батчами
 */

public interface StudentDrivenUseCase {

    public List<Student> getAllStudents();

    public Optional<Student> getStudent(Integer currentId);

    public void saveStudent(Student currentStudent);

    public void saveStudent(List<Student> currentStudents);

    public void deleteStudent(Integer currentId);

    public void deleteStudent(List<Integer> currentId);
}
