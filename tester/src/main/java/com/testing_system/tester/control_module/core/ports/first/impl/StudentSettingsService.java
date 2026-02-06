package com.testing_system.tester.control_module.core.ports.first.impl;

import com.testing_system.tester.control_module.core.domain.Student;
import com.testing_system.tester.control_module.core.domain.StudentStatus;
import com.testing_system.tester.control_module.core.ports.first.StudentRegistrationUseCase;
import com.testing_system.tester.control_module.core.ports.first.StudentSettingsUseCase;
import com.testing_system.tester.control_module.core.ports.second.StudentDrivenUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Сервис-оркестратор для изменения настроек учетной записис студента
public class StudentSettingsService implements StudentSettingsUseCase {

    private final StudentRegistrationUseCase firstPort;
    private final StudentDrivenUseCase secondPort;
    private final Logger logger = LoggerFactory.getLogger(StudentSettingsService.class);

    public StudentSettingsService(StudentRegistrationUseCase firstPort, StudentDrivenUseCase secondPort) {
        this.firstPort = firstPort;
        this.secondPort = secondPort;
    }



    /*
    Переиспользование функций из другого первичного порта
    Кидает NoStudentException
     */
    @Override
    public void changeGroup(Integer currentStudentId, String newGroup) {


        var student = firstPort.getStudentById(currentStudentId);

        // Добавить метод проверки наличия

        if (!firstPort.validGroup(newGroup))
            throw new IllegalArgumentException("Error! Incorrect group name! Group name: "
                    + newGroup);

        student.setStudentGroup(newGroup);
        secondPort.saveStudent(student);
        logger.info("Student with id {} changed group! New group: {}", currentStudentId, newGroup );
    }

    /*
    Реализовать метод изменения курса
    Использовать даты (более года с предыдущей даты регистрации/обновления студенческого)
    Логика изменения - номер группы + 100
    В группу добавить количество курсов для валидации
    Менять статус при окончании последнего курса
    */
    private Student changeCourse(Student currentStudent){
        return currentStudent;
    }

    @Override
    public void moveToTheNextCourse(String currentGroupName) {

        var allGroupStudents = firstPort.getStudentsByGroup(currentGroupName);

        if (allGroupStudents.isEmpty())
            throw new IllegalStateException("Error! You can't change empty group! Group name: "
                    + currentGroupName);

        var changedGroupStudents = allGroupStudents.stream().map(this::changeCourse).toList();

        for (Student student: changedGroupStudents) secondPort.saveStudent(student);
        // Перенести логирование в метод изменения группы
        logger.info("Students from the group{} moved to next course!" , currentGroupName);

    }

    /*
    Переиспользование функций из другого первичного порта
    Кидает NoStudentException
     */
    @Override
    public void activeStudent(Integer currentStudentId) {
        var student = firstPort.getStudentById(currentStudentId);
        student.setStudStatus(StudentStatus.ACTIVE);
        secondPort.saveStudent(student);
        logger.info("Student with id {} is active!", currentStudentId);
    }

    /*
    Переиспользование функций из другого первичного порта
    Кидает NoStudentException
     */
    @Override
    public void graduatedStudent(Integer currentStudentId) {
        var student = firstPort.getStudentById(currentStudentId);
        student.setStudStatus(StudentStatus.SUCCESSFULLY_EXPELLED);
        secondPort.saveStudent(student);
        logger.info("Student with id {} was successfully expelled!", currentStudentId);
    }

    /*
    Переиспользование функций из другого первичного порта
    Кидает NoStudentException
     */
    @Override
    public void expelledStudent(Integer currentStudentId) {

        var student = firstPort.getStudentById(currentStudentId);
        student.setStudStatus(StudentStatus.EXPELLED);
        secondPort.saveStudent(student);
        logger.info("Student with id {} was expelled!", currentStudentId);
    }

    /*
    Переиспользование функций из другого первичного порта
    Кидает NoStudentException
     */
    @Override
    public void debtorStudent(Integer currentStudentId) {

        var student = firstPort.getStudentById(currentStudentId);
        student.setStudStatus(StudentStatus.DEBTOR);
        secondPort.saveStudent(student);
        logger.info("Student with id {} is debtor!", currentStudentId);
    }
}
