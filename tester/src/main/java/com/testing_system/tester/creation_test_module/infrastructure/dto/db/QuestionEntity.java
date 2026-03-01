package com.testing_system.tester.creation_test_module.infrastructure.dto.db;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/*
Репозитарная сущность вопрос
 */
@Entity
@Table(name = "Questions")
@NoArgsConstructor
@Setter
public class QuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id", nullable = false)
    private Integer questionId;


    // Решить N+1 проблему
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "version_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private TestVersionEntity version;

    @Column(name = "description", nullable = false)
    private String description;


    public QuestionEntity (
            Integer currentQuestionId,
            TestVersionEntity currentVersion,
            String currentDescription
    )
    {
        this.questionId = currentQuestionId;
        this.version = currentVersion;
        this.description = currentDescription;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public Integer getVersion() {
        return version.getVersionId();
    }

    public String getDescription() {
        return description;
    }
}
