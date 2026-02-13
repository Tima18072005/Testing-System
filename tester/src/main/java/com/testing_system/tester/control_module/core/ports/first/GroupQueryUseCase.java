package com.testing_system.tester.control_module.core.ports.first;

import com.testing_system.tester.control_module.core.domain.Group;

import java.util.List;

/*
Первичный порт
Задачи:

    - Доступ к учетным записям групп
    - Проверка наличия группы в базе

 */
public interface GroupQueryUseCase {

    public boolean findGroup(String currentGroupNum);

    public List<Group> getAllGroups();

    public Group getGroupByName(String currentGroupName);

    public List<Group> filterGroupByName(String currentName);

    public List<Group> filterGroupByNumber(Integer currentNumber);
}
