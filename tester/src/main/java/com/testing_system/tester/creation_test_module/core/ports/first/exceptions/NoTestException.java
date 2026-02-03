package com.testing_system.tester.creation_test_module.core.ports.first.exceptions;

// Кастомная ошибка отсутствие теста
public class NoTestException extends RuntimeException {

    public NoTestException(String message) {
        super(message);
    }

    public NoTestException() {
        super();
    }
}
