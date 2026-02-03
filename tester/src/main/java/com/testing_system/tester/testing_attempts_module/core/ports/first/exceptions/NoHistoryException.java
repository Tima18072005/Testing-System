package com.testing_system.tester.testing_attempts_module.core.ports.first.exceptions;

// Кастомное исключение - отсутствие истории прохождений
public class NoHistoryException extends RuntimeException {

    public NoHistoryException(String message) {
        super(message);
    }

    public NoHistoryException() {
        super();
    }
}
