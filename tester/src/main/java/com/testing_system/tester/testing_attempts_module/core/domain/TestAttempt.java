package com.testing_system.tester.testing_attempts_module.core.domain;

// Доменная модель попытка прохождения

import java.time.LocalDate;

public class TestAttempt {

    // Id попытки, Id тестируемого, тест, оценка, дата, IP-адрес

    private final Integer attemptId;

    private final Integer testerId;

    private final String testName;

    private final Integer mark;

    private final Integer mark5;

    private final LocalDate date;

    private final String IP;


    public TestAttempt(
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

    public TestAttempt(

            Integer currentTesterId,
            String currentTestName,
            Integer currentMark, Integer mark5,
            LocalDate currentDate,
            String currentIP
    )
    {
        this.attemptId = null;
        this.testerId = currentTesterId;
        this.testName = currentTestName;
        this.mark = currentMark;
        this.mark5 = mark5;
        this.date = currentDate;
        this.IP = currentIP;
    }

    // Геттеры

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
