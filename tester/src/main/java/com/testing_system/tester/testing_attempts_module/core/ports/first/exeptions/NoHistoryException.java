package com.testing_system.tester.testing_attempts_module.core.ports.first.exeptions;

// Кастомное исключение - отсутствие истории прохождений
public class NoHistoryException extends Exception {

    public NoHistoryException(String message) {
        super(message);
    }

    public NoHistoryException() {
        super();
    }
}
