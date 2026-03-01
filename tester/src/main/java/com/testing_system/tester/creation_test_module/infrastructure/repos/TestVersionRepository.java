package com.testing_system.tester.creation_test_module.infrastructure.repos;

import com.testing_system.tester.creation_test_module.infrastructure.dto.db.TestVersionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

/*
Репозиторий для версий тестов
 */
public interface TestVersionRepository extends JpaRepository<TestVersionEntity, Integer> {

    @Query("SELECT t FROM TestVersionEntity t WHERE t.testName =:test_name")
    public List<TestVersionEntity> findAllVersions(@Param("test_name") String currentTestName);
}
