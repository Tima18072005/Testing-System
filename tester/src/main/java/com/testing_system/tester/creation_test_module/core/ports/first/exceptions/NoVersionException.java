package com.testing_system.tester.creation_test_module.core.ports.first.exceptions;

// Кастомная ошибка отсутствие версии теста
public class NoVersionException extends RuntimeException {

    public NoVersionException(String message) {
        super(message);
    }

    public NoVersionException() {
        super();
    }
}
