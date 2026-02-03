package com.testing_system.tester.testing_attempts_module.core.ports.first.exceptions;

// Кастомное исключение отстустствие правил

public class NoRulesException extends RuntimeException {
    public NoRulesException(String message) {
        super(message);
    }

    public NoRulesException() {
        super();
    }
}
