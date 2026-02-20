package com.testing_system.tester.gateway.controllers;

import com.testing_system.tester.control_module.core.ports.first.StudentQueryUseCase;
import com.testing_system.tester.control_module.infrastructure.dto.response.student.StudentFullDTO;
import com.testing_system.tester.control_module.infrastructure.mappers.StudentMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
Задачи контроллера:

    - Чтение данных о студентах
    - Фильтрация данных для поиска

Чтение доступно преподавателям
Изменение доступно админу и отвественным преподавателям
 */
@RestController
@RequestMapping("/student/get")
public class StudentQueryController {

    private final StudentQueryUseCase firstPort;
    private final StudentMapper mapper;

    public StudentQueryController(StudentQueryUseCase firstPort, StudentMapper mapper) {
        this.firstPort = firstPort;
        this.mapper = mapper;
    }

    @GetMapping("/by-id/{id}")
    public ResponseEntity<StudentFullDTO> getStudentById(@PathVariable("id") Integer currentId){

        var findStudent = firstPort.getStudentById(currentId);
        return ResponseEntity.status(HttpStatus.FOUND).body(mapper.domainToFullDTO(findStudent));
    }

    // Добавить пагинацию на все методы

    @GetMapping("/all")
    public ResponseEntity<List<StudentFullDTO>> getAllStudents(){

        var allStudents = firstPort.getAllStudents();
        return ResponseEntity.status(HttpStatus.FOUND).body(allStudents.stream().map(mapper::domainToFullDTO).toList());
    }


    @GetMapping("/filter/by-group/{group}")
    public ResponseEntity<List<StudentFullDTO>> getStudentsByGroup( @PathVariable("group") String currentGroupNum){

        var groupStudents = firstPort.getStudentsByGroup(currentGroupNum);

        return ResponseEntity.status(HttpStatus.FOUND).body(groupStudents.stream().map(mapper::domainToFullDTO).toList());
    }

    @GetMapping("/filter/by-full-name/{first-name}/{last-name}")
    public ResponseEntity<List<StudentFullDTO>> getStudentsByFullName(@PathVariable("first-name") String firstName, @PathVariable("last-name") String lastName){

        var studentsWithFullName = firstPort.getStudentByName(firstName, lastName);
        return ResponseEntity.status(HttpStatus.FOUND).body(studentsWithFullName.stream().map(mapper::domainToFullDTO).toList());
    }

    @GetMapping("/filter/by-first-name/{first-name}")
    public ResponseEntity<List<StudentFullDTO>> getStudentsByFirstName(@PathVariable("first-name") String firstName){

        var studentsWithFirstName = firstPort.getStudentByFirstName(firstName);
        return ResponseEntity.status(HttpStatus.FOUND).body(studentsWithFirstName.stream().map(mapper::domainToFullDTO).toList());
    }

    // Не работает
    @GetMapping("/filter/by-last-name/{last-name}")
    public ResponseEntity<List<StudentFullDTO>> getStudentsByLastName(@PathVariable("last-name") String lastName){

        var studentsWithLastName = firstPort.getStudentByLastName(lastName);
        return ResponseEntity.status(HttpStatus.FOUND).body(studentsWithLastName.stream().map(mapper::domainToFullDTO).toList());
    }
}
