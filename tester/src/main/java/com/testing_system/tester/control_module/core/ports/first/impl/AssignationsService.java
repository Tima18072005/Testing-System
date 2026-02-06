package com.testing_system.tester.control_module.core.ports.first.impl;

import com.testing_system.tester.control_module.core.ports.first.AssignationsUseCase;
import com.testing_system.tester.control_module.core.ports.first.StudentRegistrationUseCase;
import com.testing_system.tester.control_module.core.ports.second.GroupDrivenUseCase;
import com.testing_system.tester.control_module.core.ports.second.StudentDrivenUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/*
Сервис-оркестратор для назначения тестов и учебных дисциплин
 */
public class AssignationsService implements AssignationsUseCase {


    /*
     Реализация использует первичный и вторичный порты
     */
    private final StudentRegistrationUseCase firstPort;
    private final GroupDrivenUseCase secondPort;

    private final Logger logger = LoggerFactory.getLogger(AssignationsService.class);

    public AssignationsService(StudentRegistrationUseCase firstPort, GroupDrivenUseCase secondPort) {

        this.firstPort = firstPort;
        this.secondPort = secondPort;

    }

    /*
    Кидает NoGroupException()
     */
    @Override
    public void fieldAssign(String currentGroupNum, String currentField) {

        var currentGroup = firstPort.getGroupByName(currentGroupNum);

        if (currentGroup.getFields().stream().anyMatch(field -> field.equals(currentField)))

            throw new IllegalArgumentException("Error! You can't assign field twice! Field name: "
                    + currentField + ". Group name: " + currentGroupNum);

        currentGroup.addField(currentField);
        secondPort.saveGroup(currentGroup);
        logger.info("Field{} was assign for the group {}", currentField, currentGroupNum);
    }

    /*
    Кидает NoGroupException()
    Третий аргумент функции - дисциплина, для которой написан тест
     */
    @Override
    public void testAssign(String currentGroupNum, String currentTest, String currentTestField) {

        var currentGroup = firstPort.getGroupByName(currentGroupNum);

        if (currentGroup.getTests().stream().anyMatch(test -> test.equals(currentTest)))

            throw new IllegalArgumentException("Error! You can't assign test twice! Test name: "
                    + currentTest + ". Group name: " + currentGroupNum);

        if (currentGroup.getFields().stream().noneMatch(field -> field.equals(currentTestField)))
            throw new IllegalArgumentException("Error! This group haven't assignation for this field! Group name: "
                    + currentGroupNum + ". Field name: " + currentTestField);

        currentGroup.addTest(currentTest);
        secondPort.saveGroup(currentGroup);
        logger.info("Test{} was assign for the group {}", currentTest, currentGroupNum);

    }

    /*
    Кидает NoGroupException()
     */
    @Override
    public void fieldAssignDelete(String currentGroupNum, String currentField) {

        var currentGroup = firstPort.getGroupByName(currentGroupNum);

        if (currentGroup.getFields().stream().noneMatch(field -> field.equals(currentField)))

            throw new IllegalArgumentException("Error! You can't delete not assigned field! Field name: "
                    + currentField + ". Group name: " + currentGroupNum);

        currentGroup.removeField(currentField);
        secondPort.saveGroup(currentGroup);
        logger.info("Field{} was delete for the group {}", currentField, currentGroupNum);
    }

    /*
    Кидает NoGroupException()
     */
    @Override
    public void testAssignDelete(String currentGroupNum, String currentTest) {

        var currentGroup = firstPort.getGroupByName(currentGroupNum);

        if (currentGroup.getTests().stream().noneMatch(test -> test.equals(currentTest)))

            throw new IllegalArgumentException("Error! You can't delete not assigned test! Test name: "
                    + currentTest + ". Group name: " + currentGroupNum);


        currentGroup.removeTest(currentTest);
        secondPort.saveGroup(currentGroup);
        logger.info("Test{} was delete for the group {}", currentTest, currentGroupNum);
    }

    /*
    Кидает NoGroupException()
    При пустом списке контроллер возвращает соответствующий ДТО
     */
    @Override
    public List<String> getFieldsAssign(String currentGroupNum) {
        return firstPort.getGroupByName(currentGroupNum).getFields();
    }

    /*
    Кидает NoGroupException()
    При пустом списке контроллер возвращает соответствующий ДТО
     */
    @Override
    public List<String> getTestsAssign(String currentGroupNum) {
        return firstPort.getGroupByName(currentGroupNum).getTests();
    }

    /*
    Кидает NoStudentException()
    Кидает NoGroupException()
    При пустом списке контроллер возвращает соответствующий ДТО
     */
    @Override
    public List<String> getFieldsAssign(Integer currentStudentId) {

        var studentGroupNumber = firstPort.getStudentById(currentStudentId).getStudentGroup();
        return getFieldsAssign(studentGroupNumber);
    }

    /*
    Кидает NoStudentException()
    Кидает NoGroupException()
    При пустом списке контроллер возвращает соответствующий ДТО
     */
    @Override
    public List<String> getTestsAssign(Integer currentStudentId) {

        var studentGroupNumber = firstPort.getStudentById(currentStudentId).getStudentGroup();
        return getTestsAssign(studentGroupNumber);
    }
}
