package com.testing_system.tester.control_module.core.ports.first.exceptions;

// Кастомное исключение отсутствие группы

public class NoGroupException extends RuntimeException {

    public NoGroupException(String message) {
        super(message);
    }

    public NoGroupException() {
        super();
    }
}
