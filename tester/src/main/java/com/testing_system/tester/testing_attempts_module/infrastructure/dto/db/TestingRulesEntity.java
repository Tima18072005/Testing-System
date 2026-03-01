package com.testing_system.tester.testing_attempts_module.infrastructure.dto.db;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

/*
Репозитарная сущность правила тестирования
 */
@Entity
@Table(name = "testing_rules")
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class TestingRulesEntity {

    @Id
    @Column(name ="test_name", nullable = false)
    private String testName;

    @Column(name ="day_attempts", nullable = false)
    private Integer dayAttempts;

    @Column(name ="all_attempts", nullable = false)
    private Integer allAttempts;

    public TestingRulesEntity(Integer dayAttempts, Integer allAttempts) {
        this.dayAttempts = dayAttempts;
        this.allAttempts = allAttempts;
    }

    public String getTestName() {
        return testName;
    }

    public Integer getDayAttempts() {
        return dayAttempts;
    }

    public Integer getAllAttempts() {
        return allAttempts;
    }



}
