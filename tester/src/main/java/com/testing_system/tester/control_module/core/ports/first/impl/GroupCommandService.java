package com.testing_system.tester.control_module.core.ports.first.impl;

import com.testing_system.tester.control_module.core.domain.Group;
import com.testing_system.tester.control_module.core.ports.first.GroupCommandUseCase;
import com.testing_system.tester.control_module.core.ports.first.GroupQueryUseCase;
import com.testing_system.tester.control_module.core.ports.first.StudentQueryUseCase;
import com.testing_system.tester.control_module.core.ports.second.GroupDrivenUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/*
Сервис-оркестратор для назначения тестов и учебных дисциплин
 */
@Service
public class GroupCommandService implements GroupCommandUseCase {



    //Реализация использует первичные и вторичные порты
    private final GroupQueryUseCase firstPort;
    private final GroupDrivenUseCase secondPort;
    private final Logger logger = LoggerFactory.getLogger(GroupCommandService.class);


    public GroupCommandService( GroupQueryUseCase firstPort, GroupDrivenUseCase secondPort) {

        this.firstPort = firstPort;
        this.secondPort = secondPort;

    }


    // Использует метод первичного порта, который кидает NoGroupException()
    @Override
    public Group fieldAssign(String currentGroupNum, String currentField) {

        var currentGroup = firstPort.getGroupByName(currentGroupNum);

        if (currentGroup.getFields().stream().anyMatch(field -> field.equals(currentField)))

            throw new IllegalArgumentException("Error! You can't assign field twice! Field name: %s Group name: %s "
                    .formatted(currentField, currentGroupNum));

        currentGroup.addField(currentField);
        secondPort.saveGroup(currentGroup);
        logger.info("Field{} was assign for the group {}", currentField, currentGroupNum);
        return currentGroup;
    }


    // Использует метод первичного порта, который кидает NoGroupException()
    @Override
    public Group testAssign(String currentGroupNum, String currentTest, String currentTestField) {

        var currentGroup = firstPort.getGroupByName(currentGroupNum);

        if (currentGroup.getTests().stream().anyMatch(test -> test.equals(currentTest)))

            throw new IllegalArgumentException("Error! You can't assign test twice! Test name: %s Group name: %s"
                    .formatted(currentTest, currentGroupNum));

        if (currentGroup.getFields().stream().noneMatch(field -> field.equals(currentTestField)))
            throw new IllegalArgumentException("Error! This group haven't assignation for this field! Group name: %s Field name: %s"
                    .formatted( currentGroupNum, currentTestField));

        currentGroup.addTest(currentTest);
        secondPort.saveGroup(currentGroup);
        logger.info("Test{} was assign for the group {}", currentTest, currentGroupNum);
        return currentGroup;

    }


    // Использует метод первичного порта, который кидает NoGroupException()
    @Override
    public Group fieldAssignDelete(String currentGroupNum, String currentField) {

        var currentGroup = firstPort.getGroupByName(currentGroupNum);

        if (currentGroup.getFields().stream().noneMatch(field -> field.equals(currentField)))

            throw new IllegalArgumentException("Error! You can't delete not assigned field! Group name: %s Field name: %s"
                    .formatted(currentGroupNum, currentField));

        currentGroup.removeField(currentField);
        secondPort.saveGroup(currentGroup);
        logger.info("Field{} was delete for the group {}", currentField, currentGroupNum);
        return currentGroup;
    }



    // Использует метод первичного порта, который кидает NoGroupException()
    @Override
    public Group testAssignDelete(String currentGroupNum, String currentTest) {

        var currentGroup = firstPort.getGroupByName(currentGroupNum);

        if (currentGroup.getTests().stream().noneMatch(test -> test.equals(currentTest)))

            throw new IllegalArgumentException("Error! You can't delete not assigned test! Test name: %s Group name: %s"
                    .formatted(currentTest, currentGroupNum));


        currentGroup.removeTest(currentTest);
        secondPort.saveGroup(currentGroup);
        logger.info("Test{} was delete for the group {}", currentTest, currentGroupNum);
        return currentGroup;
    }



}
