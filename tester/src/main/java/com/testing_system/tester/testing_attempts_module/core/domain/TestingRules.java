package com.testing_system.tester.testing_attempts_module.core.domain;

// Доменная модель правил прохождения теста
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
        this.dayAttempts = currentDayAttempts;
        this.allAttempts = currentAllAttempts;
    }

    // Геттеры и сеттеры

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public Integer getDayAttempts() {
        return dayAttempts;
    }

    public void setDayAttempts(Integer dayAttempts) {
        this.dayAttempts = dayAttempts;
    }

    public Integer getAllAttempts() {
        return allAttempts;
    }

    public void setAllAttempts(Integer allAttempts) {
        this.allAttempts = allAttempts;
    }
}
