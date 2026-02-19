package com.testing_system.tester.control_module.infrastructure.mappers;

import com.testing_system.tester.control_module.core.domain.Group;
import com.testing_system.tester.control_module.infrastructure.dto.db.GroupEntity;
import com.testing_system.tester.control_module.infrastructure.dto.response.GroupFullDTO;
import com.testing_system.tester.control_module.infrastructure.dto.response.GroupQueryDTO;
import com.testing_system.tester.control_module.infrastructure.dto.response.GroupSaveDTO;
import org.springframework.stereotype.Component;

/*
Маппер для доменной сущности группа
 */
@Component
public class GroupMapper {

    public GroupEntity domainToEntity(Group currentGroup) {

        return new GroupEntity(
                currentGroup.getGroupNumber(),
                currentGroup.getNumberOfCourses(),
                currentGroup.getTests(),
                currentGroup.getFields()
        );
    }

    public Group entityToDomain(GroupEntity currentGroupEntity) {
        return new Group(
                currentGroupEntity.getGroupNumber(),
                currentGroupEntity.getNumberOfCourses(),
                currentGroupEntity.getTests(),
                currentGroupEntity.getFields());
    }

    public Group saveDTOToDomain(GroupSaveDTO currentSaveDTO) {

        if (currentSaveDTO.getNumberOfCourses() == null) return new Group(currentSaveDTO.getGroupNumber());

        return new Group(currentSaveDTO.getGroupNumber(), currentSaveDTO.getNumberOfCourses());
    }

    public GroupFullDTO domainToFullDTO(Group currentGroup) {
        return new GroupFullDTO(
                currentGroup.getGroupNumber(),
                currentGroup.getNumberOfCourses(),
                currentGroup.getTests(),
                currentGroup.getFields()
                );
    }

    public GroupQueryDTO domainToQueryDTO(Group currentGroup){
        return new GroupQueryDTO(
                currentGroup.getGroupNumber(),
                currentGroup.getNumberOfCourses()
        );
    }
}
