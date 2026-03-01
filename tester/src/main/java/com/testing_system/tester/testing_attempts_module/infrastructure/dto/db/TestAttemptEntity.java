package com.testing_system.tester.testing_attempts_module.infrastructure.dto.db;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/*
Репозитарная сущность попытка прохождения
 */
@Entity
@Table(name = "attempt_history")
@NoArgsConstructor
@Setter
public class TestAttemptEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer attemptId;

    @Column(name = "student_id", nullable = false)
    private Integer testerId;

    @Column(name = "test_name", nullable = false)
    private String testName;

    @Column(name = "100_mark", nullable = false)
    private Integer mark;

    @Column(name = "5_mark", nullable = false)
    private Integer mark5;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "ip_address", nullable = false)
    private String IP;

    public TestAttemptEntity(Integer testerId, String testName, Integer mark,
                             Integer mark5, LocalDate date, String IP) {
        this.testerId = testerId;
        this.testName = testName;
        this.mark = mark;
        this.mark5 = mark5;
        this.date = date;
        this.IP = IP;
    }

    public Integer getAttemptId() {
        return attemptId;
    }

    public Integer getTesterId() {
        return testerId;
    }

    public String getTestName() {
        return testName;
    }

    public Integer getMark() {
        return mark;
    }

    public Integer getMark5() {
        return mark5;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getIP() {
        return IP;
    }
}
