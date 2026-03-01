package com.testing_system.tester.creation_test_module.infrastructure.dto.db;

import com.testing_system.tester.creation_test_module.core.domain.AnswerStatus;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/*
Репозитарная сущность вопрос
 */
@Entity
@Table(name = "answers")
@NoArgsConstructor
@Setter
public class AnswerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    private Integer answerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "questionId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private QuestionEntity question;

    @Column(name = "description")
    private String description;

    @Column(name = "answer_status")
    @Enumerated(EnumType.STRING)
    private AnswerStatus answerStatus;

    public AnswerEntity(Integer answerId, QuestionEntity question, String description, AnswerStatus currentAnswerStatus) {
        this.answerId = answerId;
        this.question = question;
        this.description = description;
        this.answerStatus = currentAnswerStatus;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public Integer getQuestionID() {
        return question.getQuestionId();
    }

    public String getDescription() {
        return description;
    }

    public AnswerStatus getAnswerStatus() {
        return answerStatus;
    }
}
