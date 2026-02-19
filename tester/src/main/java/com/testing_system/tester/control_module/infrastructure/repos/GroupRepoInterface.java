package com.testing_system.tester.control_module.infrastructure.repos;

import com.testing_system.tester.control_module.infrastructure.dto.db.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

// JPA-репозиторий для группы
public interface GroupRepoInterface extends JpaRepository<GroupEntity, String> {
}
