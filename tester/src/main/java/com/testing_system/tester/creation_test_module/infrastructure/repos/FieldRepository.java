package com.testing_system.tester.creation_test_module.infrastructure.repos;

import com.testing_system.tester.creation_test_module.infrastructure.dto.db.FieldEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/*
JPA-репозиторий для учебных дисциплин
 */
public interface FieldRepository extends JpaRepository<FieldEntity, String> {
}
