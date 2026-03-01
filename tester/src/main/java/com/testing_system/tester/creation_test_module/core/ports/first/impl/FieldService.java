package com.testing_system.tester.creation_test_module.core.ports.first.impl;

import com.testing_system.tester.creation_test_module.core.domain.Field;
import com.testing_system.tester.creation_test_module.core.ports.first.FieldUseCase;
import com.testing_system.tester.creation_test_module.core.ports.first.exceptions.NoFieldException;
import com.testing_system.tester.creation_test_module.core.ports.second.FieldDrivenUseCase;
import org.springframework.stereotype.Service;

import java.util.List;

/*
Сервис-оркестратор, работа с учебными дисциплинами
 */
@Service
public class FieldService implements FieldUseCase {

    // Реализация использует только вторичный порт
    private final FieldDrivenUseCase secondPort;

    public FieldService(FieldDrivenUseCase secondPort) {
        this.secondPort = secondPort;
    }


    @Override
    public boolean findField(String currentFieldName) {
        return secondPort.getField(currentFieldName).isPresent();
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

        if (findField(currentField.getFieldName()))
            throw new IllegalArgumentException("Error! This field is exist! Field name: " + currentField.getFieldName());

        secondPort.saveField(currentField);
    }

    @Override
    public void deleteField(String currentFieldName) {

        if (!findField(currentFieldName))
            throw new IllegalArgumentException("Error! You can't delete non-existent field! Field name: "
                    + currentFieldName);

        secondPort.deleteField(currentFieldName);
    }
}
