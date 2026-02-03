package com.testing_system.tester.creation_test_module.core.ports.first.impl;

import com.testing_system.tester.creation_test_module.core.domain.Field;
import com.testing_system.tester.creation_test_module.core.ports.first.FieldUseCase;
import com.testing_system.tester.creation_test_module.core.ports.first.exceptions.NoFieldException;
import com.testing_system.tester.creation_test_module.core.ports.second.FieldDrivenUseCase;

import java.util.List;

// Сервис-оркестратор, работа с учебными дисциплинами
public class FieldService implements FieldUseCase {


    private final FieldDrivenUseCase secondPort;

    public FieldService(FieldDrivenUseCase secondPort) {
        this.secondPort = secondPort;
    }


    @Override
    public List<Field> getAllFieldNames() {
        return secondPort.getAllFields();
    }

    @Override
    public Field getFieldByName(String currentFieldName)  {
        return secondPort.getField(currentFieldName).orElseThrow(
                () -> new NoFieldException("Error! Field not found: " +  currentFieldName ));
    }


    @Override
    public void makeNewField(Field currentField) {

        if (secondPort.getField(currentField.getFieldName()).isPresent())
            throw new IllegalStateException("Error! This field is exist! Field name: " + currentField.getFieldName());

        secondPort.saveField(currentField);
    }

    @Override
    public void deleteField(String currentFieldName) {

        if (secondPort.getField(currentFieldName).isEmpty())
            throw new IllegalStateException("Error! You can't delete non-existent field! Field name: "
                    + currentFieldName);

        secondPort.deleteField(currentFieldName);
    }
}
