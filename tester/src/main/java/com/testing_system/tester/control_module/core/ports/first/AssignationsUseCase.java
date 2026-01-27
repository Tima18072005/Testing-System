package com.testing_system.tester.control_module.core.ports.first;

import java.util.List;

// Первичный порт для назначения тестов и учебных дисциплин
public interface AssignationsUseCase {

    // Назанчачение/отмена назначений

    public void fieldAssign(String currentField);


    public void testAssign(String currentTest);


    public void fieldAssignDelete(String currentField);


    public void testAssignDelete(String currentTest);

    // Доступ к данным для группы и для кокретного студента

    public List<String> getFieldsAssign(String currentGroupNum);


    public List<String> getTestsAssign(String currentGroupNum);


    public List<String> getFieldsAssign(Integer currentStudentId);


    public List<String> getTestsAssign(Integer currentStudentId);
}
