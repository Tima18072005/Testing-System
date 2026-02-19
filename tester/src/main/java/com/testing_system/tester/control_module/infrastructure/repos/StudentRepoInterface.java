package com.testing_system.tester.control_module.infrastructure.repos;


import com.testing_system.tester.control_module.infrastructure.dto.db.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

// JPA-репозитрий для студента
public interface StudentRepoInterface extends JpaRepository<StudentEntity, Integer> {
}
