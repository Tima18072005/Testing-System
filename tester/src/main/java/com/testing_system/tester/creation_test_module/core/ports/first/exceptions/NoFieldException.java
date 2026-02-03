package com.testing_system.tester.creation_test_module.core.ports.first.exceptions;

// Кастомное исклчение отстутствие учебной дисциплины
public class NoFieldException extends RuntimeException {

    public NoFieldException(String message) { super(message);}

    public NoFieldException() { super();}
}
