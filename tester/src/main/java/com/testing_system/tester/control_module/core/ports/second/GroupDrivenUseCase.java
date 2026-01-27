package com.testing_system.tester.control_module.core.ports.second;



import com.testing_system.tester.control_module.core.domain.Group;
import java.util.List;
import java.util.Optional;

// Вторичный порт для работы с группами

public interface GroupDrivenUseCase {

    public List<Group> getAllGroups();

    public Optional<Group> getGroup(String currentGroupName);

    public void saveGroup(Group currentGroup);

    public void deleteGroup(String currentGroupName);
}
