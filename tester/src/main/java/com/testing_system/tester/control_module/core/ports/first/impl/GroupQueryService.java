package com.testing_system.tester.control_module.core.ports.first.impl;

import com.testing_system.tester.control_module.core.domain.Group;
import com.testing_system.tester.control_module.core.ports.first.GroupQueryUseCase;
import com.testing_system.tester.control_module.core.ports.first.exceptions.NoGroupException;
import com.testing_system.tester.control_module.core.ports.second.GroupDrivenUseCase;

import java.util.List;

/*
Сервис-оркестратор для доступа к группе
 */
public class GroupQueryService implements GroupQueryUseCase {

    // Реализация использует только вторичный порт
    private final GroupDrivenUseCase secondPort;



    public GroupQueryService(GroupDrivenUseCase secondPort) {
        this.secondPort = secondPort;
    }



    @Override
    public boolean findGroup(String currentGroupNum) {

        return secondPort.getGroup(currentGroupNum).isPresent();
    }



    @Override
    public Group getGroupByName(String currentGroupName) {
        return secondPort.getGroup(currentGroupName).orElseThrow(
                () -> new NoGroupException("Group not found! Group name: "
                        + currentGroupName));
    }


    //При отстутствии групп контроллер вернёт соответствующий ДТО
    @Override
    public List<Group> getAllGroups() {
        return secondPort.getAllGroups();
    }


    // Следующие методы могут быть ускорены при расширении первичного порта


    @Override
    public List<Group> filterGroupByName(String currentName) {

        return getAllGroups().stream()
                .filter(group -> group.getGroupNumber().substring(0, group.getGroupNumber().indexOf("-")).equals(currentName))
                .toList();
    }



    @Override
    public List<Group> filterGroupByNumber(Integer currentNumber) {

        return getAllGroups().stream()
                .filter(group -> group.getGroupNumber().substring(group.getGroupNumber().lastIndexOf("-")).equals(currentNumber.toString()))
                .toList();
    }
}
