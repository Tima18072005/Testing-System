package com.testing_system.tester.creation_test_module.infrastructure.second_adapters;

import com.testing_system.tester.creation_test_module.core.domain.Field;
import com.testing_system.tester.creation_test_module.core.ports.second.FieldDrivenUseCase;
import com.testing_system.tester.creation_test_module.infrastructure.mappers.FieldMapper;
import com.testing_system.tester.creation_test_module.infrastructure.repos.FieldRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/*
Втоичный адаптер для учебных дисциплин
 */
@Service
public class FieldSecondAdapter implements FieldDrivenUseCase {

    private final FieldRepository JpaRepository;
    private final FieldMapper mapper;

    public FieldSecondAdapter(FieldRepository jpaRepository, FieldMapper mapper) {
        JpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Field> getAllFields() {

        var allFields = JpaRepository.findAll();
        if (allFields.isEmpty()) return List.of();

        return allFields.stream().map(mapper::entityToDomain).toList();
    }

    @Override
    public Optional<Field> getField(String currentFieldName) {
        var currentField = JpaRepository.findById(currentFieldName);
        return currentField.map(mapper::entityToDomain);
    }

    @Override
    public void saveField(Field currentField) {

        JpaRepository.save(mapper.domainToEntity(currentField));
    }

    @Override
    public void deleteField(String currentFieldName) {

        JpaRepository.deleteById(currentFieldName);
    }
}
