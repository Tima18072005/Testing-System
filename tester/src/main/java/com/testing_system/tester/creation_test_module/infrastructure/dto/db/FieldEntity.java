package com.testing_system.tester.creation_test_module.infrastructure.dto.db;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
Репоизтарная сущность группа
 */
@Entity
@Table(name = "fields")
@NoArgsConstructor
@Setter
public class FieldEntity {

    @Id
    @Column(name = "field_name", nullable = false)
    private String fieldName;

    public FieldEntity(String currentFieldName){
        this.fieldName = currentFieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
