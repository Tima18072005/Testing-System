package com.testing_system.tester.control_module.infrastructure.second_adapters;

import com.testing_system.tester.control_module.core.domain.Group;
import com.testing_system.tester.control_module.core.ports.second.GroupDrivenUseCase;
import com.testing_system.tester.control_module.infrastructure.dto.db.GroupEntity;
import com.testing_system.tester.control_module.infrastructure.mappers.GroupMapper;
import com.testing_system.tester.control_module.infrastructure.repos.GroupRepoInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/*
Вторичный адаптер для группы
 */
@Service
public class GroupSecondAdapter implements GroupDrivenUseCase {

    private final GroupRepoInterface interfaceRepository;
    private final GroupMapper mapper;

    public GroupSecondAdapter(GroupRepoInterface interfaceRepository, GroupMapper mapper) {
        this.interfaceRepository = interfaceRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Group> getAllGroups() {
        List<GroupEntity> listOfEntity = interfaceRepository.findAll();

        if (listOfEntity.isEmpty()) return List.of();

        return listOfEntity.stream().map(mapper::entityToDomain).toList();
    }

    @Override
    public Optional<Group> getGroup(String currentGroupName) {

        Optional<GroupEntity> currentEntity = interfaceRepository.findById(currentGroupName.trim());

        return currentEntity.map(mapper::entityToDomain);
    }

    @Override
    public void saveGroup(Group currentGroup) {
        interfaceRepository.save(mapper.domainToEntity(currentGroup));
    }

    @Override
    public void deleteGroup(String currentGroupName) {
        interfaceRepository.deleteById(currentGroupName);
    }
}
