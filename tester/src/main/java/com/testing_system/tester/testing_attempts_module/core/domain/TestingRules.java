package com.testing_system.tester.testing_attempts_module.core.domain;

import java.util.Objects;

/*
 Доменная модель правил прохождения теста
 */
public class TestingRules {

    private String testName;

    private Integer dayAttempts;

    private Integer allAttempts;

    // Конструктор
    public TestingRules (
            String currentTestName,
            Integer currentDayAttempts,
            Integer currentAllAttempts)
    {
        this.testName = currentTestName;


        this.dayAttempts = Objects.requireNonNullElse(currentDayAttempts, 1);


        this.allAttempts = Objects.requireNonNullElse(currentAllAttempts, 3);
    }

    // Геттеры и сеттеры

    public String getTestName() {
        return testName;
    }

    public Integer getDayAttempts() {
        return dayAttempts;
    }

    public Integer getAllAttempts() {
        return allAttempts;
    }

    public void setDayAttempts(Integer dayAttempts) {
        this.dayAttempts = dayAttempts;
    }


    public void setAllAttempts(Integer allAttempts) {
        this.allAttempts = allAttempts;
    }
}
