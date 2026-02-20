package com.testing_system.tester.gateway.controllers;

import com.testing_system.tester.control_module.core.ports.first.EmployeeRegistrationUseCase;
import com.testing_system.tester.control_module.core.ports.first.StudentRegistrationUseCase;
import com.testing_system.tester.control_module.infrastructure.dto.response.employee.EmployeeFullDTO;
import com.testing_system.tester.control_module.infrastructure.dto.response.employee.EmployeeSaveDTO;
import com.testing_system.tester.control_module.infrastructure.dto.response.group.GroupFullDTO;
import com.testing_system.tester.control_module.infrastructure.dto.response.group.GroupSaveDTO;
import com.testing_system.tester.control_module.infrastructure.dto.response.student.StudentFullDTO;
import com.testing_system.tester.control_module.infrastructure.dto.response.student.StudentSaveDTO;
import com.testing_system.tester.control_module.infrastructure.mappers.EmployeeMapper;
import com.testing_system.tester.control_module.infrastructure.mappers.GroupMapper;
import com.testing_system.tester.control_module.infrastructure.mappers.StudentMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*
Задачи контроллера:

    - Регистрирование и удаление групп
    - Регистрирование и удаление студентов (группами и по одному)

Удаление только для админа
Регистрирование сотрудников и студентов для админа и ответственных преподавателей
 */
@RestController
@RequestMapping("/registration")
public class RegistrationController {

    private final StudentRegistrationUseCase studentFirstPort;
    private final EmployeeRegistrationUseCase firstPort;
    private final EmployeeMapper employeeMapper;
    private final StudentMapper studentMapper;
    private final GroupMapper groupMapper;

    public RegistrationController(StudentRegistrationUseCase studentFirstPort, EmployeeRegistrationUseCase firstPort, EmployeeMapper employeeMapper, StudentMapper studentMapper, GroupMapper groupMapper) {
        this.studentFirstPort = studentFirstPort;
        this.firstPort = firstPort;
        this.employeeMapper = employeeMapper;
        this.studentMapper = studentMapper;
        this.groupMapper = groupMapper;
    }

    @PostMapping("/new-student")
    public ResponseEntity<StudentFullDTO> saveStudent(@RequestBody StudentSaveDTO currentDTO){

        var savedStudent = studentFirstPort.regStudent(studentMapper.saveDTOToDomain(currentDTO));

        return ResponseEntity.status(HttpStatus.CREATED).body(studentMapper.domainToFullDTO(savedStudent));
    }

    @PostMapping("/new-group")
    public ResponseEntity<GroupFullDTO> saveGroup(@RequestBody GroupSaveDTO currentDTO){

        var savedGroup = studentFirstPort.makeGroup(groupMapper.saveDTOToDomain(currentDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(groupMapper.domainToFullDTO(savedGroup));
    }

    @PostMapping("/new-employee")
    public ResponseEntity<EmployeeFullDTO> regEmployee(@RequestBody EmployeeSaveDTO currentDTO){
        var savedEmployee = firstPort.regEmployee(employeeMapper.saveDTOToDomain(currentDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeMapper.domainToFullDTO(savedEmployee));
    }

    @PostMapping("/new-admin")
    public ResponseEntity<EmployeeFullDTO> regAdmin( @RequestBody EmployeeSaveDTO currentDTO){
        var savedEmployee = firstPort.regEmployee(employeeMapper.saveAdminDTOToDomain(currentDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeMapper.domainToFullDTO(savedEmployee));
    }


    @DeleteMapping("/delete-group/{name}")
    public ResponseEntity<Void> deleteGroup(@PathVariable("name") String groupForDeleteName){

        studentFirstPort.deleteGroup(groupForDeleteName);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete-student/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable("id") Integer studentForDeleteId){

        studentFirstPort.deleteStudent(studentForDeleteId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete-employee/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable("id") Integer employeeForDeleteId){

        firstPort.deleteEmployee(employeeForDeleteId);
        return ResponseEntity.noContent().build();
    }


}
