package com.testing_system.tester.creation_test_module.core.ports.second;

import com.testing_system.tester.creation_test_module.core.domain.Field;

import java.util.List;
import java.util.Optional;

// Вторичный порт для работы с данными учебных дисциплин

public interface FieldDrivenUseCase {

    public List<Field> getAllFields();

    public Optional<Field> getField(String currentFieldName);

    public void saveField(Field currentField);

    public void deleteField(String currentFieldName);
}
