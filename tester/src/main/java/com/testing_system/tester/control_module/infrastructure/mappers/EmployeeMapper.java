package com.testing_system.tester.control_module.infrastructure.mappers;

import com.testing_system.tester.control_module.core.domain.Employee;
import com.testing_system.tester.control_module.core.domain.EmployeeStatus;
import com.testing_system.tester.control_module.infrastructure.dto.db.EmployeeEntity;
import com.testing_system.tester.control_module.infrastructure.dto.response.employee.EmployeeFullDTO;
import com.testing_system.tester.control_module.infrastructure.dto.response.employee.EmployeeSaveDTO;
import org.springframework.stereotype.Component;

/*
Маппер для доменной модели сотрудника
 */
@Component
public class EmployeeMapper {


    public EmployeeEntity mapDomainToEntity(Employee currentEmployee){


        if (currentEmployee.getPatronymic()==null) return new EmployeeEntity(
                currentEmployee.getEmpId(),
                currentEmployee.getFirstName(),
                currentEmployee.getLastName(),
                currentEmployee.getEmpStatus(),
                currentEmployee.getHashPass()
        );

        return new EmployeeEntity(
                currentEmployee.getEmpId(),
                currentEmployee.getFirstName(),
                currentEmployee.getLastName(),
                currentEmployee.getPatronymic(),
                currentEmployee.getEmpStatus(),
                currentEmployee.getHashPass()
        );
    }

    public Employee mapEntityToDomain(EmployeeEntity currentEntity){

        if (currentEntity.getPatronymic() == null) return new Employee(
                currentEntity.getEmpId(),
                currentEntity.getFirstName(),
                currentEntity.getLastName(),
                currentEntity.getEmpStatus(),
                currentEntity.getHashPass());

        return new Employee(
                currentEntity.getEmpId(),
                currentEntity.getFirstName(),
                currentEntity.getLastName(),
                currentEntity.getPatronymic(),
                currentEntity.getEmpStatus(),
                currentEntity.getHashPass()
        );
    }

    // При регистрации пароль хешируется в маппинге, программа не работает с самими паролями в чистом виде

    public Employee saveDTOToDomain(EmployeeSaveDTO currentDTO){
        if (currentDTO.getPatronymic()==null) return new Employee(
                currentDTO.getEmpId(),
                currentDTO.getFirstName(),
                currentDTO.getLastName(),
                EmployeeStatus.TEACHER,
                currentDTO.getHashPass()
        );

        return new Employee(
                currentDTO.getEmpId(),
                currentDTO.getFirstName(),
                currentDTO.getLastName(),
                currentDTO.getPatronymic(),
                EmployeeStatus.TEACHER,
                currentDTO.getHashPass());
    }

    public Employee saveAdminDTOToDomain(EmployeeSaveDTO currentDTO){
        if (currentDTO.getPatronymic()==null) return new Employee(
                currentDTO.getEmpId(),
                currentDTO.getFirstName(),
                currentDTO.getLastName(),
                EmployeeStatus.ADMIN,
                currentDTO.getHashPass()
        );

        return new Employee(
                currentDTO.getEmpId(),
                currentDTO.getFirstName(),
                currentDTO.getLastName(),
                currentDTO.getPatronymic(),
                EmployeeStatus.ADMIN,
                currentDTO.getHashPass());
    }

    public EmployeeFullDTO domainToFullDTO(Employee currentEmployee){
        if (currentEmployee.getPatronymic()==null) return new EmployeeFullDTO(
                currentEmployee.getEmpId(),
                currentEmployee.getFirstName(),
                currentEmployee.getLastName(),
                currentEmployee.getEmpStatus()
        );

        return new EmployeeFullDTO(
                currentEmployee.getEmpId(),
                currentEmployee.getFirstName(),
                currentEmployee.getLastName(),
                currentEmployee.getPatronymic(),
                currentEmployee.getEmpStatus()
        );
    }


}
