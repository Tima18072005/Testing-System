package com.testing_system.tester.creation_test_module.infrastructure.repos;

import com.testing_system.tester.creation_test_module.infrastructure.dto.db.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Integer> {

    @Query("SELECT t FROM QuestionEntity t WHERE t.version =:version_id")// Проверить работает ли при сравнении сущности Entity с его Integer id
    public List<QuestionEntity> getAllQuestionsByVersionId(@Param("version_id") Integer currentVersionId);
}
