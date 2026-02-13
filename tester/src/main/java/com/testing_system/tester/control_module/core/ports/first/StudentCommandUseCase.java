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

    public void changeGroup(Integer currentStudentId, String newGroup);

    public void promoteStudents(String currentGroupNumber, List<Student> groupStudents);

    public void activeStudent(Integer currentStudentId);

    public void graduatedStudent(Integer currentStudentId);

    public void expelledStudent(Integer currentStudentId);

    public void debtorStudent(Integer currentStudentId);
}
