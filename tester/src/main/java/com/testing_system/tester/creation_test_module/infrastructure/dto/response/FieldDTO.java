package com.testing_system.tester.creation_test_module.infrastructure.dto.response;

import lombok.NoArgsConstructor;
import lombok.Setter;

/*
Дто для создания и просмотра учебных дисциплин
 */
@Setter
@NoArgsConstructor
public class FieldDTO {

    private String fieldName;

    public FieldDTO(String currentFieldName){
        this.fieldName = currentFieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
