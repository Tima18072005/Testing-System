package com.testing_system.tester.control_module.core.ports.first;

import com.testing_system.tester.control_module.core.domain.Student;

import java.util.List;

/*
 Первичный порт
 Выполняемые задачи:

  - Изменение статуса студента
  - Смена группы студента
  - Перевод группы на следующий курс

 */
public interface StudentCommandUseCase {

    public Student changeGroup(Integer currentStudentId, String newGroup);

    public void promoteStudents( List<Student> groupStudents);

    public Student activeStudent(Integer currentStudentId);

    public Student graduatedStudent(Integer currentStudentId);

    public Student expelledStudent(Integer currentStudentId);

    public Student debtorStudent(Integer currentStudentId);
}
