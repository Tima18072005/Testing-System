package com.testing_system.tester.control_module.core.ports.first;

import com.testing_system.tester.control_module.core.domain.Group;

import java.util.List;

/*

    Первичный порт
    Выполняет следующие задачи:
     - Назначение/отмена назначений группам тестов и учебных дисциплин
     - Предоставление доступа к данным о назначениях

*/
public interface GroupCommandUseCase {


    public Group fieldAssign(String currentGroupNum, String currentField);

    public Group testAssign(String currentGroupNum, String currentTest, String currentTestField);

    public Group fieldAssignDelete(String currentGroupNum, String currentField);

    public Group testAssignDelete(String currentGroupNum, String currentTest);


}
