package com.testing_system.tester.gateway.controllers;

import com.testing_system.tester.control_module.core.ports.first.GroupCommandUseCase;
import com.testing_system.tester.control_module.core.ports.first.GroupQueryUseCase;
import com.testing_system.tester.control_module.infrastructure.dto.response.group.AssignationDTO;
import com.testing_system.tester.control_module.infrastructure.dto.response.group.GroupFullDTO;
import com.testing_system.tester.control_module.infrastructure.dto.response.group.GroupQueryDTO;
import com.testing_system.tester.control_module.infrastructure.mappers.GroupMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
Задачи контроллера:

    - Просмотр всех групп
    - Поиск и фильтрация
    - Просмотр и изменение назначений тестов и предметов группам


На главной странице студента отображаются предметы
Чтение доступно преподавателю
Назначение учебных дисциплин доступно админу и ответственным преподавателям
Назначение тестов доступно преподавателю
 */
@RestController
@RequestMapping("/group")
public class GroupController {

    private final GroupQueryUseCase queryFirstPort;
    private final GroupCommandUseCase commandFirstPort;
    private final GroupMapper mapper;

    public GroupController(GroupQueryUseCase firstPort, GroupCommandUseCase commandFirstPort, GroupMapper mapper) {
        this.queryFirstPort = firstPort;
        this.commandFirstPort = commandFirstPort;
        this.mapper = mapper;
    }


    @GetMapping("/get/by-name/{name}")
    public ResponseEntity<GroupFullDTO> getGroupByName(@PathVariable("name") String currentName){
        var findGroup = queryFirstPort.getGroupByName(currentName);
        return ResponseEntity.status(HttpStatus.FOUND).body(mapper.domainToFullDTO(findGroup));
    }


    @GetMapping("/get/all")
    public ResponseEntity<List<GroupQueryDTO>> getAllGroups(){
        var allGroups = queryFirstPort.getAllGroups();
        return ResponseEntity.status(HttpStatus.FOUND).body(allGroups.stream().map(mapper::domainToQueryDTO).toList());
    }


    @GetMapping("/get/filter/by-name/{name}")
    public ResponseEntity<List<GroupQueryDTO>> getByName(@PathVariable("name") String groupName){

        var groupsByName = queryFirstPort.filterGroupByName(groupName);
        return ResponseEntity.status(HttpStatus.FOUND).body(groupsByName.stream().map(mapper::domainToQueryDTO).toList());
    }



    @GetMapping("/get/filter/by-number/{number}")
    public ResponseEntity<List<GroupQueryDTO>> getByNumber( @PathVariable("number") Integer currentNumber){

        var groupsByNumber = queryFirstPort.filterGroupByNumber(currentNumber);
        return ResponseEntity.status(HttpStatus.FOUND).body(groupsByNumber.stream().map(mapper::domainToQueryDTO).toList());
    }

    // Post-методы не протестированы

    @PostMapping("/assign/test/{number}")
    public ResponseEntity<GroupFullDTO> assignTest(@PathVariable("number") String groupNum, @RequestBody AssignationDTO currentDTO){

        var changedGroup = commandFirstPort.testAssign(groupNum, currentDTO.testName(), currentDTO.fieldName());
        return ResponseEntity.status(HttpStatus.OK).body(mapper.domainToFullDTO(changedGroup));
    }


    @PostMapping("/assign/field/{number}")
    public ResponseEntity<GroupFullDTO> assignField(@PathVariable("number") String groupNum,@RequestBody AssignationDTO currentDTO ){

        var changedGroup = commandFirstPort.fieldAssign(groupNum, currentDTO.fieldName());
        return ResponseEntity.status(HttpStatus.OK).body(mapper.domainToFullDTO(changedGroup));
    }

    @PostMapping("/delete-assign/test/{number}")
    public ResponseEntity<GroupFullDTO> deleteAssignTest(@PathVariable("number") String groupNum,@RequestBody AssignationDTO currentDTO){

        var changedGroup = commandFirstPort.testAssignDelete(groupNum, currentDTO.testName());
        return ResponseEntity.status(HttpStatus.OK).body(mapper.domainToFullDTO(changedGroup));
    }

    @PostMapping("/delete-assign/field/{number}")
    public ResponseEntity<GroupFullDTO> deleteAssignField(@PathVariable("number") String groupNum,@RequestBody AssignationDTO currentDTO ){

        var changedGroup = commandFirstPort.fieldAssignDelete(groupNum, currentDTO.fieldName());
        return ResponseEntity.status(HttpStatus.OK).body(mapper.domainToFullDTO(changedGroup));
    }
}
