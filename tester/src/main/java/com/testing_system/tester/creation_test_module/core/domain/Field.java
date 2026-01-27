package com.testing_system.tester.creation_test_module.core.domain;

// Доменная модель учебная дисциплина
public class Field {

    private final String fieldName;

    public Field(String currentFieldName){
        this.fieldName = currentFieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
