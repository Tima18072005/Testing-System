package com.testing_system.tester.control_module.core.ports.first;

import com.testing_system.tester.control_module.core.domain.Group;
import com.testing_system.tester.control_module.core.domain.Student;

import java.util.List;


/*
 Первичный порт.
 Выполняемые задачи:
  - CRUD с учетными записями студентов и групп (удаление студента предполагает каскадирование)
  - Фильтрация запрашиваемых данных
  - Валидация названия группы
 */
public interface StudentRegistrationUseCase {

    public void regStudent(Student currentStudent);

    public void makeGroup(Group currentGroup);

    public boolean validGroup(String currentGroup);

    public void deleteStudent(Integer currentStudentId);

    public void deleteGroup(String currentGroupNum);

    public Student getStudentById(Integer currentStudentId);

    public List<Student> getAllStudents();

    public List<Student> getStudentsByGroup(String currentGroupNum);

    public List<Student> getStudentByName(String currentFirstName, String currentSecondName);

    public List<Student> getStudentByFirstName(String currentFirstName);

    public List<Student> getStudentByLastName(String currentSecondName);

    public List<Group> getAllGroups();

    public Group getGroupByName(String currentGroupName);
}
