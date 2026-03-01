package com.testing_system.tester.control_module.core.ports.first.impl;

import com.testing_system.tester.control_module.core.domain.Group;
import com.testing_system.tester.control_module.core.domain.Student;
import com.testing_system.tester.control_module.core.ports.first.GroupQueryUseCase;
import com.testing_system.tester.control_module.core.ports.first.StudentQueryUseCase;
import com.testing_system.tester.control_module.core.ports.first.StudentRegistrationUseCase;
import com.testing_system.tester.control_module.core.ports.second.GroupDrivenUseCase;
import com.testing_system.tester.control_module.core.ports.second.StudentDrivenUseCase;
import org.springframework.stereotype.Service;


/*
 Сервис-оркестратор для регистрации/удаления учетных записей студентов и групп
 */
@Service
public class StudentRegistrationService implements StudentRegistrationUseCase {


    //Реализация использует первичные и вторичные порты
    private final StudentQueryUseCase firstPort;
    private final GroupQueryUseCase groupFirstPort;
    private final StudentDrivenUseCase secondPort;
    private final GroupDrivenUseCase groupSecondPort;



    public StudentRegistrationService(StudentQueryUseCase firstPort, GroupQueryUseCase groupFirstPort, StudentDrivenUseCase secondPort, GroupDrivenUseCase groupSecondPort) {
        this.firstPort = firstPort;
        this.groupFirstPort = groupFirstPort;
        this.secondPort = secondPort;
        this.groupSecondPort = groupSecondPort;
    }



    @Override
    public Student regStudent(Student currentStudent) {

       if (firstPort.findStudent(currentStudent.getStudentId()))
           throw new IllegalArgumentException("Error! You can't save students with the same ids! Student id: %s"
                   .formatted(currentStudent.getStudentId()));

       if (!groupFirstPort.findGroup(currentStudent.getStudentGroup()))
           throw new IllegalArgumentException("Error! You can't save students with non-existent group! Group: %s. Student ID: %d"
                   .formatted(currentStudent.getStudentGroup(), currentStudent.getStudentId()));

        secondPort.saveStudent(currentStudent);
        return currentStudent;
    }



    //Название группы считается верным при наличии символа "-" и трехзначном номере

    private boolean validGroup(String currentGroup){

        if(!currentGroup.contains("-")) return false;

        var number = currentGroup.substring(currentGroup.lastIndexOf("-")+1);

        return number.length() == 3;
    }



    @Override
    public Group makeGroup(Group currentGroup) {

        if (groupFirstPort.findGroup(currentGroup.getGroupNumber()))
            throw new IllegalArgumentException("Error! You can't save groups with the same names and numbers! Group name: %s"
                    .formatted(currentGroup.getGroupNumber()));

        if (!validGroup(currentGroup.getGroupNumber()))
            throw new IllegalArgumentException("Error! Incorrect group name! Group name: %s"
                    .formatted(currentGroup.getGroupNumber()));

        groupSecondPort.saveGroup(currentGroup);
        return currentGroup;
    }



    @Override
    public void deleteStudent(Integer currentStudentId) {

        if (!firstPort.findStudent(currentStudentId))
            throw new IllegalArgumentException("Error! You can't delete non-existent student! Student ID: %d "
                    .formatted(currentStudentId));

        secondPort.deleteStudent(currentStudentId);

    }



    //Удалять группу можно только при отсутствии студентов, обучающихся в этой группе
    @Override
    public void deleteGroup(String currentGroupNum) {

        if (!groupFirstPort.findGroup(currentGroupNum))
            throw new IllegalArgumentException("Error! You can't delete non-existent group! Group name: %s"
                    .formatted(currentGroupNum));

        if (!firstPort.getStudentsByGroup(currentGroupNum).isEmpty())
            throw new IllegalStateException("Error! You can't delete group with students! Group name: %s"
                    .formatted(currentGroupNum));

        groupSecondPort.deleteGroup(currentGroupNum);
    }


}
