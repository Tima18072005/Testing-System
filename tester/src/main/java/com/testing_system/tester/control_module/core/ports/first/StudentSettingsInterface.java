package com.testing_system.tester.control_module.core.ports.first;

// Первичный порт для изменения настроек учетной записи студента
public interface StudentSettingsInterface {

    // Методы, отвественные за смену группы

    public void changeGroup(Integer currentStudentId, String newGroup);

    public void moveToTheNextCourse(String currentGroupName);

    // Методы, ответственные за смену статуса

    public void activeStudent(Integer currentStudentId);

    public void graduatedStudent(Integer currentStudentId);

    public void expelledStudent(Integer currentStudentId);

    public void debtorStudent(Integer currentStudentId);
}
