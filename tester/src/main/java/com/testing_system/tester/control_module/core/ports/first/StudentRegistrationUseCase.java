package com.testing_system.tester.control_module.core.ports.first;

import com.testing_system.tester.control_module.core.domain.Group;
import com.testing_system.tester.control_module.core.domain.Student;

import java.util.List;


/*
 Первичный порт
 Выполняемые задачи:

    - Создание/удаление учетных записей студентов и групп

 */
public interface StudentRegistrationUseCase {

    public void regStudent(Student currentStudent);

    public void deleteStudent(Integer currentStudentId);

    public boolean validGroup(String currentGroup);

    public void makeGroup(Group currentGroup);

    public void deleteGroup(String currentGroupNum);
}
