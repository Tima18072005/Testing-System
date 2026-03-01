package com.testing_system.tester.testing_attempts_module.infrastructure.repos;

import com.testing_system.tester.testing_attempts_module.infrastructure.dto.db.TestingRulesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/*
Репозиторий для правил тестирования
 */
public interface TestingRulesRepository extends JpaRepository<TestingRulesEntity, String> {
}
