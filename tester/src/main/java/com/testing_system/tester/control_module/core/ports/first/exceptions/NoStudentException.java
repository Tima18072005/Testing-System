package com.testing_system.tester.control_module.core.ports.first.exceptions;

// Кастомное исключение отсутствие студента
public class NoStudentException extends RuntimeException {

    public NoStudentException(String message) {
        super(message);
    }

    public NoStudentException() {
        super();
    }
}
