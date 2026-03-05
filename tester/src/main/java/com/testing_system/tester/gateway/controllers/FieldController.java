package com.testing_system.tester.gateway.controllers;

/*
Задачи контроллера:

    - Создание/удаление учебных дисциплин
    - Поиск и просмотр учебных дисциплин

Чтение доступно преподавателям
Создание доступно админу и ответсвенным преподавателям
Удаление только админу
 */

import com.testing_system.tester.creation_test_module.core.domain.Field;
import com.testing_system.tester.creation_test_module.core.ports.first.FieldUseCase;
import com.testing_system.tester.creation_test_module.infrastructure.dto.response.FieldDTO;
import com.testing_system.tester.creation_test_module.infrastructure.mappers.FieldMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/field")
public class FieldController {

    private final FieldUseCase firstUseCase;
    private final FieldMapper mapper;

    public FieldController(FieldUseCase firstUseCase, FieldMapper mapper) {
        this.firstUseCase = firstUseCase;
        this.mapper = mapper;
    }

    @GetMapping("/get/{name}")
    public ResponseEntity<FieldDTO> getFieldByName(@PathVariable("name") String currentFieldName){

        Field currentField = firstUseCase.getFieldByName(currentFieldName);

        return ResponseEntity.status(HttpStatus.FOUND).body(mapper.domainToDTO(currentField));
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<FieldDTO>> getAll(){

        List<Field> allFields = firstUseCase.getAllFieldNames();

        return ResponseEntity.status(HttpStatus.FOUND).body(allFields.stream().map(mapper::domainToDTO).toList());
    }

    @PostMapping("/save")
    public ResponseEntity<FieldDTO> saveField(@RequestBody FieldDTO currentDto){

        Field savedField = firstUseCase.makeNewField(mapper.dtoToDomain(currentDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.domainToDTO(savedField));
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<Void> deleteField(@PathVariable("name") String currentField){
        firstUseCase.deleteField(currentField);
        return ResponseEntity.noContent().build();
    }
}
