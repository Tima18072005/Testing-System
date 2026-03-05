package com.testing_system.tester.creation_test_module.infrastructure.mappers;

import com.testing_system.tester.creation_test_module.core.domain.Field;
import com.testing_system.tester.creation_test_module.infrastructure.dto.db.FieldEntity;
import com.testing_system.tester.creation_test_module.infrastructure.dto.response.FieldDTO;
import org.springframework.stereotype.Component;

/*
Маппер для учебных дисциплин
 */
@Component
public class FieldMapper {

    public Field entityToDomain(FieldEntity currentEntity){
        return new Field(currentEntity.getFieldName());
    }

    public FieldEntity domainToEntity(Field currentField){
        return new FieldEntity(currentField.getFieldName());
    }

    public Field dtoToDomain(FieldDTO currentDTO){
        return new Field(currentDTO.getFieldName());
    }

    public FieldDTO domainToDTO(Field currentField){
        return new FieldDTO(currentField.getFieldName());
    }
}
