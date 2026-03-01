package com.testing_system.tester.creation_test_module.infrastructure.repos;

import com.testing_system.tester.creation_test_module.infrastructure.dto.db.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/*
Репозиторий для тестов
 */
public interface TestRepository extends JpaRepository<TestEntity, String> {
}
