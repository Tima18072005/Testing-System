package com.testing_system.tester.control_module.core.ports.first;

/*
 Первичный порт.
 Выполняемые задачи:
  - Изменение статуса студента
  - Смена группы студента
  - Переход группы на следующий курс
 */
public interface StudentSettingsUseCase {

    public void changeGroup(Integer currentStudentId, String newGroup);

    public void moveToTheNextCourse(String currentGroupName);

    public void activeStudent(Integer currentStudentId);

    public void graduatedStudent(Integer currentStudentId);

    public void expelledStudent(Integer currentStudentId);

    public void debtorStudent(Integer currentStudentId);
}
