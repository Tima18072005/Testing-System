package com.testing_system.tester.testing_attempts_module.core.ports.first.exeptions;

// Кастомное исключение отстустствие правил

public class NoRulesException extends Exception {
    public NoRulesException(String message) {
        super(message);
    }

    public NoRulesException() {
        super();
    }
}
