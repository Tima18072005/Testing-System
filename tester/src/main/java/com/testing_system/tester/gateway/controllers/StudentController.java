package com.testing_system.tester.gateway.controllers;


import com.testing_system.tester.control_module.core.ports.first.StudentCommandUseCase;
import com.testing_system.tester.control_module.infrastructure.dto.response.group.ChangeGroupDTO;
import com.testing_system.tester.control_module.infrastructure.dto.response.student.StudentFullDTO;
import com.testing_system.tester.control_module.infrastructure.mappers.StudentMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
Задачи контроллера:

    - Изменение номера группы студента
    - Массовый перевод студентов на следующий курс
    - Изменение статуса конкретного студента

Изменение доступно админу и отвественным преподавателям
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentCommandUseCase firstPort;
    private final StudentMapper mapper;

    public StudentController(StudentCommandUseCase firstPort, StudentMapper mapper) {
        this.firstPort = firstPort;
        this.mapper = mapper;
    }


    // Реализовать методы контроллера
    @PostMapping("/make-active/{id}")
    public ResponseEntity<StudentFullDTO> changeStatusToActive(@PathVariable("id") Integer currentId){

        var changedStudent = firstPort.activeStudent(currentId);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.domainToFullDTO(changedStudent));
    }


    @PostMapping("/make-debtor/{id}")
    public ResponseEntity<StudentFullDTO> changeStatusToDebtor(@PathVariable("id") Integer currentId){

        var changedStudent = firstPort.debtorStudent(currentId);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.domainToFullDTO(changedStudent));
    }


    @PostMapping("/make-expelled/{id}")
    public ResponseEntity<StudentFullDTO> changeStatusToExpelled(@PathVariable("id") Integer currentId){

        var changedStudent = firstPort.expelledStudent(currentId);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.domainToFullDTO(changedStudent));
    }


    @PostMapping("/make-graduated/{id}")
    public ResponseEntity<StudentFullDTO> changeStatusToSuccessfullyExpelled(@PathVariable("id") Integer currentId){

        var changedStudent = firstPort.graduatedStudent(currentId);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.domainToFullDTO(changedStudent));
    }


    @PostMapping("/change-group/{id}")
    public ResponseEntity<StudentFullDTO> changeStudentGroup(@PathVariable("id") Integer currentId, @RequestBody ChangeGroupDTO currentDTO){

        var changedStudent = firstPort.changeGroup(currentId, currentDTO.newGroup());
        return ResponseEntity.status(HttpStatus.OK).body(mapper.domainToFullDTO(changedStudent));

    }


    // Не протестировано
    @PostMapping("/promote-students")
    public ResponseEntity<Void> promoteStudents(@RequestBody List<StudentFullDTO> studentsToPromote){

        var domainStudentsToPromote = studentsToPromote.stream().map(mapper::fullDTOToDomain).toList();

        firstPort.promoteStudents(domainStudentsToPromote);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
