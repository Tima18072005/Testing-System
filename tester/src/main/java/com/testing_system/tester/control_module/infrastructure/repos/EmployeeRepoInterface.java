package com.testing_system.tester.control_module.infrastructure.repos;


import com.testing_system.tester.control_module.infrastructure.dto.db.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

// JPA-репозиторий для сотрудника
public interface EmployeeRepoInterface extends JpaRepository<EmployeeEntity, Integer> {

    @Query("SELECT e FROM EmployeeEntity e WHERE e.empStatus = :ADMIN")
    public Optional<EmployeeEntity> getAdmin();
}
