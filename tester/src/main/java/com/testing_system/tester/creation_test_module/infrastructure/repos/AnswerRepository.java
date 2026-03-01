package com.testing_system.tester.creation_test_module.infrastructure.repos;

import com.testing_system.tester.creation_test_module.infrastructure.dto.db.AnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnswerRepository extends JpaRepository<AnswerEntity, Integer> {

    @Query("SELECT t FROM AnswerEntity t WHERE t.question =:question_id")
    public List<AnswerEntity> getAllAnswersByQuestionId(@Param("question_id") Integer QuestionId);
}
