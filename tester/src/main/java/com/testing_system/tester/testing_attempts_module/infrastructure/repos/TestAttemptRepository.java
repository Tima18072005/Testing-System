package com.testing_system.tester.testing_attempts_module.infrastructure.repos;

import com.testing_system.tester.testing_attempts_module.infrastructure.dto.db.TestAttemptEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestAttemptRepository extends JpaRepository<TestAttemptEntity, Integer> {
}
