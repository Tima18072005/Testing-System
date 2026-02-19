package com.testing_system.tester.control_module.core.ports.first.impl;

import com.testing_system.tester.control_module.core.domain.Employee;
import com.testing_system.tester.control_module.core.ports.first.EmployeeQueryUseCase;
import com.testing_system.tester.control_module.core.ports.first.EmployeeRegistrationUseCase;
import com.testing_system.tester.control_module.core.ports.second.EmployeeDrivenUseCase;
import org.springframework.stereotype.Service;


/*
 Сервис-оркестратор для работы с учетными записями сотрудников
 */
@Service
public class EmployeeRegistrationService implements EmployeeRegistrationUseCase {


    //Реализация использует первичный и вторичный порты
    private final EmployeeQueryUseCase firstPort;
    private final EmployeeDrivenUseCase secondPort;



    public EmployeeRegistrationService(EmployeeQueryUseCase firstPort, EmployeeDrivenUseCase secondPort) {
        this.firstPort = firstPort;
        this.secondPort = secondPort;
    }



    //Назначение статуса происходит при маппинге из регистрационного ДТО в доменную сущность
    @Override
    public Employee regEmployee(Employee currentEmployee) {

        if (firstPort.getEmployee(currentEmployee.getEmpId()))
            throw new IllegalArgumentException("Error! You can't save employee with same ids! Employee ID: %d"
                    .formatted(currentEmployee.getEmpId()));

        secondPort.saveEmployee(currentEmployee);
        return currentEmployee;
    }

    /*
    Метод предполагает каскадирование
    При удалении сотрудника удаляются все его тесты, версии тестов из под его редактуры, попытки прохождения
     */
    @Override
    public void deleteEmployee(Integer currentId) {

        if (!firstPort.getEmployee(currentId))
            throw new IllegalArgumentException("Error! You can't delete non-existent employee! Employee ID: %d"
                    .formatted(currentId));

        secondPort.deleteEmployee(currentId);
    }
}
