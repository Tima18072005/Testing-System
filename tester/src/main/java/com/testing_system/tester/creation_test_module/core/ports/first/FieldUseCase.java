package com.testing_system.tester.creation_test_module.core.ports.first;

import com.testing_system.tester.creation_test_module.core.domain.Field;

import java.util.List;
import java.util.Optional;

// Первичный порт для работы с учебными дисциплинами
public interface FieldUseCase {

    public List<Field> getAllFieldNames();

    public Field getFieldByName(String currentFieldName);

    public Field makeNewField(Field currentField);

    public void deleteField(String currentFieldName);

}
