package com.testing_system.tester.control_module.core.ports.first;

import java.util.List;

/*

    Первичный порт
    Выполняет следующие задачи:
     - Назначение/отмена назначений группам тестов и учебных дисциплин
     - Предоставление доступа к данным о назначениях

*/
public interface GroupCommandUseCase {


    public void fieldAssign(String currentGroupNum, String currentField);


    public void testAssign(String currentGroupNum, String currentTest, String currentTestField);


    public void fieldAssignDelete(String currentGroupNum, String currentField);


    public void testAssignDelete(String currentGroupNum, String currentTest);


    public List<String> getFieldsAssign(String currentGroupNum);


    public List<String> getTestsAssign(String currentGroupNum);

    // Для конкретного студента

    public List<String> getFieldsAssign(Integer currentStudentId);


    public List<String> getTestsAssign(Integer currentStudentId);
}
