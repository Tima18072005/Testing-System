package com.testing_system.tester.control_module.core.ports.first.exceptions;

// Кастомное исключение отсутствие сотрудника
public class NoEmployeeException extends RuntimeException {

    public NoEmployeeException(String message) {
        super(message);
    }

    public NoEmployeeException() {
        super();
    }
}
