package com.testing_system.tester.creation_test_module.infrastructure.mappers;

import com.testing_system.tester.creation_test_module.core.domain.TestVersion;
import com.testing_system.tester.creation_test_module.infrastructure.dto.db.TestVersionEntity;
import org.springframework.stereotype.Component;

/*
Маппер для сущности версии теста
 */
@Component
public class TestVersionMapper {

    public TestVersion entityToDomain(TestVersionEntity currentEntity){

        return new TestVersion(
                currentEntity.getVersionId(),
                currentEntity.getTestName(),
                currentEntity.getVersionAuthor(),
                currentEntity.getVersionStatus(),
                currentEntity.getCreationDate()
                );
    }

    public TestVersionEntity domainToEntity(TestVersion currentVersion){

        return new TestVersionEntity(
                currentVersion.getVersionId(),
                currentVersion.getTestName(),
                currentVersion.getVersionAuthor(),
                currentVersion.getVersionStatus(),
                currentVersion.getCreationDate()
        );
    }
}
