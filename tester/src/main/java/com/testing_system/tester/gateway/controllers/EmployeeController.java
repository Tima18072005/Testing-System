package com.testing_system.tester.gateway.controllers;

import com.testing_system.tester.control_module.core.domain.EmployeeStatus;
import com.testing_system.tester.control_module.core.ports.first.EmployeeCommandUseCase;
import com.testing_system.tester.control_module.core.ports.first.EmployeeQueryUseCase;
import com.testing_system.tester.control_module.infrastructure.dto.response.EmployeeFullDTO;
import com.testing_system.tester.control_module.infrastructure.mappers.EmployeeMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
Задачи контроллера:

    - Просмотр списка всех сотрудников
    - Поиск и фильтрация
    - Повышение/понижение уровня доступа (преподаватель -> ответственный преподаватель)
    - Изменение пароля

Чтение доступно преодавателям
Повышение/понижение уровня допуска доступно только админам
Каждый сотрудник может изменить свой пароль, кроме него никто другой
При регистрировании админом сотрудника пароль устанавливается стандартный
Сотрудник может его поменять (рекомендуется)
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeQueryUseCase queryFirstPort;
    private final EmployeeCommandUseCase commandFirstPort;
    private final EmployeeMapper mapper;

    public EmployeeController(EmployeeQueryUseCase queryFirstPort, EmployeeCommandUseCase commandFirstPort, EmployeeMapper mapper) {
        this.queryFirstPort = queryFirstPort;
        this.commandFirstPort = commandFirstPort;
        this.mapper = mapper;
    }

    @GetMapping("get/{id}")
    public ResponseEntity<EmployeeFullDTO> getEmployeeById(@PathVariable("id") Integer currentId){

        var employeeToGet = queryFirstPort.findEmpById(currentId);
        return ResponseEntity.status(HttpStatus.FOUND).body(mapper.domainToFullDTO(employeeToGet));
    }

    // Добавить пагинацию

    @GetMapping("/get-all")
    public ResponseEntity<List<EmployeeFullDTO>> getAllEmployee(){
        var allEmployees = queryFirstPort.getAllEmployee();
        return ResponseEntity.status(HttpStatus.FOUND).body(allEmployees.stream().map(mapper::domainToFullDTO).toList());
    }

    @GetMapping("get/{first-name}/{last-name}")
    public ResponseEntity<List<EmployeeFullDTO>> getEmployeesByFullName(@PathVariable("first-name") String firstName, @PathVariable("last-name") String lastName){

        var employeesByFullName = queryFirstPort.findEmpByName(firstName, lastName);

        return ResponseEntity.status(HttpStatus.FOUND).body(employeesByFullName.stream().map(mapper::domainToFullDTO).toList());
    }

    @GetMapping("get/{first-name}")
    public ResponseEntity<List<EmployeeFullDTO>> getEmployeesByFirstName(@PathVariable("first-name") String firstName){

        var employeeByFirstName = queryFirstPort.filterEmployeeByFirstName(firstName);

        return ResponseEntity.status(HttpStatus.FOUND).body(employeeByFirstName.stream().map(mapper::domainToFullDTO).toList());
    }

    @GetMapping("get/{last-name}")
    public ResponseEntity<List<EmployeeFullDTO>> getEmployeesBySecondName(@PathVariable("last-name") String lastName){

        var employeeByLastName = queryFirstPort.filterEmployeeByLastName(lastName);

        return ResponseEntity.status(HttpStatus.FOUND).body(employeeByLastName.stream().map(mapper::domainToFullDTO).toList());
    }

    @GetMapping("get/{status}")
    public ResponseEntity<List<EmployeeFullDTO>> getEmployeesByStatus(@PathVariable("status") EmployeeStatus currentStatus){

        var employeeByStatus = queryFirstPort.filterEmpByStatus(currentStatus);

        return ResponseEntity.status(HttpStatus.FOUND).body(employeeByStatus.stream().map(mapper::domainToFullDTO).toList());
    }

    @PostMapping("/level-up/{id}")
    public ResponseEntity<EmployeeFullDTO> employeeLevelUp(@PathVariable("id") Integer currentId){

        var levelUpEmployee = commandFirstPort.empLevelUp(currentId);

        return ResponseEntity.status(HttpStatus.OK).body(mapper.domainToFullDTO(levelUpEmployee));
    }

    @PostMapping("/level-down/{id}")
    public ResponseEntity<EmployeeFullDTO> employeeLevelDown(@PathVariable("id") Integer currentId){

        var levelDownEmployee = commandFirstPort.empLevelLow(currentId);

        return ResponseEntity.status(HttpStatus.OK).body(mapper.domainToFullDTO(levelDownEmployee));
    }

    @PostMapping("/change-password/{id}")
    public ResponseEntity<EmployeeFullDTO> changePassword(@PathVariable("id") Integer currentId, @RequestBody String newPassword1,@RequestBody String newPassword2){

        // Пароли нужно хешировать
        var changedEmployee = commandFirstPort.changePassword(currentId, newPassword1, newPassword2);

        return ResponseEntity.status(HttpStatus.OK).body(mapper.domainToFullDTO(changedEmployee));
    }

}
