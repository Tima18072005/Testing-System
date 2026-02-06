package com.testing_system.tester.control_module.core.ports.first.impl;

import com.testing_system.tester.control_module.core.domain.Group;
import com.testing_system.tester.control_module.core.domain.Student;
import com.testing_system.tester.control_module.core.ports.first.StudentRegistrationUseCase;
import com.testing_system.tester.control_module.core.ports.first.exceptions.NoGroupException;
import com.testing_system.tester.control_module.core.ports.first.exceptions.NoStudentException;
import com.testing_system.tester.control_module.core.ports.second.GroupDrivenUseCase;
import com.testing_system.tester.control_module.core.ports.second.StudentDrivenUseCase;

import java.util.List;

/*
 Сервис-оркестратор для доступа к учётным записям студентов
 */
public class StudentRegistrationService implements StudentRegistrationUseCase {

    /*
    Реализация использует два вторичных порта
     */
    private final StudentDrivenUseCase secondPort;
    private final GroupDrivenUseCase groupSecondPort;

    public StudentRegistrationService(StudentDrivenUseCase secondPort, GroupDrivenUseCase groupSecondPort) {
        this.secondPort = secondPort;
        this.groupSecondPort = groupSecondPort;
    }

    @Override
    public void regStudent(Student currentStudent) {

        // Поменять конкатенацию, поменять обработку ошибок и добавить методы проверки наличия
        if (secondPort.getStudent(currentStudent.getStudentId()).isPresent())
            throw new IllegalArgumentException("Error! You can't save students with the same ids! Student ID: "
                    + currentStudent.getStudentId());

        if (groupSecondPort.getGroup(currentStudent.getStudentGroup()).isEmpty())
            throw new IllegalArgumentException("Error! You can't save students with non-existent group! Student ID: "
                    + currentStudent.getStudentId() + ". Student group: " + currentStudent.getStudentGroup());


        secondPort.saveStudent(currentStudent);

    }


    /*
    Метод для проверки валидости названия группы
    Проверяет наличие трехзначного номера в названии группы
    Придумать, как проверять корректность курса группы (возможно добавить сущность направление)
     */
    @Override
    public boolean validGroup(String currentGroup){

        if(!currentGroup.contains("-")) return false;

        var number = currentGroup.substring(currentGroup.lastIndexOf("-"));

        return number.length() == 3;
    }

    @Override
    public void makeGroup(Group currentGroup) {

        if (groupSecondPort.getGroup(currentGroup.getGroupNumber()).isPresent())
            throw new IllegalArgumentException("Error! You can't save groups with the same names and numbers! Group name: "
                    + currentGroup.getGroupNumber());

        if (!validGroup(currentGroup.getGroupNumber()))
            throw new IllegalArgumentException("Error! Incorrect group name! Group name: %s".formatted(currentGroup.getGroupNumber()));

        groupSecondPort.saveGroup(currentGroup);
    }

    @Override
    public void deleteStudent(Integer currentStudentId) {

        if (secondPort.getStudent(currentStudentId).isEmpty())
            throw new IllegalArgumentException("Error! You can't delete non-existent student! Student ID: "
                    + currentStudentId);

        secondPort.deleteStudent(currentStudentId);

    }

    /*
    Удалять группу можно при отсутствии студентов, обучающихся в этой группе
     */
    @Override
    public void deleteGroup(String currentGroupNum) {

        if (groupSecondPort.getGroup(currentGroupNum).isEmpty())
            throw new IllegalArgumentException("Error! You can't delete non-existent group! Group name: "
                    + currentGroupNum);

        if (!getStudentsByGroup(currentGroupNum).isEmpty())
            throw new IllegalStateException("Error! You can't delete group with students! Group name: %s".formatted(currentGroupNum));

        groupSecondPort.deleteGroup(currentGroupNum);
    }

    @Override
    public Student getStudentById(Integer currentStudentId) {

        return secondPort.getStudent(currentStudentId).orElseThrow(
                () -> new NoStudentException("Student not found! Student ID: "
                        + currentStudentId));
    }

    /*
    При отстутствии студентов контроллер вернёт соответствующий ДТО
     */
    @Override
    public List<Student> getAllStudents() {
        return secondPort.getAllStudents();
    }

    /*
   При отстутствии студентов контроллер вернёт соответствующий ДТО
     */
    @Override
    public List<Student> getStudentsByGroup(String currentGroupNum) {

        return getAllStudents().stream()
                .filter(student -> student.getStudentGroup().equals(currentGroupNum)).toList();
    }

    /*
   При отстутствии студентов контроллер вернёт соответствующий ДТО
     */
    @Override
    public List<Student> getStudentByName(String currentFirstName, String currentSecondName) {
        return getAllStudents().stream().filter(student -> student.getFirstName().equals(currentFirstName))
                .filter(student -> student.getLastName().equals(currentSecondName))
                .toList();
    }

    /*
   При отстутствии студентов контроллер вернёт соответствующий ДТО
     */
    @Override
    public List<Student> getStudentByFirstName(String currentFirstName) {
        return getAllStudents().stream().filter(
                student -> student.getFirstName()
                        .equals(currentFirstName)).toList();
    }

    /*
   При отстутствии студентов контроллер вернёт соответствующий ДТО
     */
    @Override
    public List<Student> getStudentByLastName(String currentSecondName) {
        return getAllStudents().stream().filter(
                student -> student.getFirstName()
                        .equals(currentSecondName)).toList();
    }

    /*
    При отстутствии групп контроллер вернёт соответствующий ДТО
     */
    @Override
    public List<Group> getAllGroups() {
        return groupSecondPort.getAllGroups();
    }

    @Override
    public Group getGroupByName(String currentGroupName) {
        return groupSecondPort.getGroup(currentGroupName).orElseThrow(
                () -> new NoGroupException("Group not found! Group name: "
                + currentGroupName));
    }
}
