package com.testing_system.tester.testing_attempts_module.infrastructure.dto.response;

import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Setter
public class TestAttemptDTO{

    private Integer attemptId;

    private Integer testerId;

    private String testName;

    private Integer mark;

    private Integer mark5;

    private LocalDate date;

    private String IP;


    public TestAttemptDTO(
            Integer currentAttemptId,
            Integer currentTesterId,
            String currentTestName,
            Integer currentMark, Integer mark5,
            LocalDate currentDate,
            String currentIP
    )
    {
        this.attemptId = currentAttemptId;
        this.testerId = currentTesterId;
        this.testName = currentTestName;
        this.mark = currentMark;
        this.mark5 = mark5;
        this.date = currentDate;
        this.IP = currentIP;
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

    public LocalDate getDate() {
        return date;
    }

    public String getIP() {
        return IP;
    }

    public Integer getMark5() {
        return mark5;
    }

}

