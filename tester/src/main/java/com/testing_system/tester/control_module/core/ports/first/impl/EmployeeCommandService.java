package com.testing_system.tester.control_module.core.ports.first.impl;

import com.testing_system.tester.control_module.core.domain.Employee;
import com.testing_system.tester.control_module.core.domain.EmployeeStatus;
import com.testing_system.tester.control_module.core.ports.first.EmployeeCommandUseCase;
import com.testing_system.tester.control_module.core.ports.first.EmployeeQueryUseCase;
import com.testing_system.tester.control_module.core.ports.second.EmployeeDrivenUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/*
 Сервис-оркестратор для изменения данных в учетных записях сотрудника
 */
@Service
public class EmployeeCommandService implements EmployeeCommandUseCase {


    //Реализация использует первичный и вторичный порты
    private final EmployeeQueryUseCase firstPort;
    private final EmployeeDrivenUseCase secondPort;
    private final Logger logger = LoggerFactory.getLogger(EmployeeCommandService.class);

    public EmployeeCommandService(EmployeeQueryUseCase firstPort, EmployeeDrivenUseCase secondPort) {
        this.firstPort = firstPort;
        this.secondPort = secondPort;
    }

    //Кидает NoEmployeeException
    @Override
    public Employee empLevelUp(Integer currentId) {

        var currentEmployee = firstPort.findEmpById(currentId);

        if (currentEmployee.getEmpStatus().equals(EmployeeStatus.ADMIN))
            throw new IllegalArgumentException("Error! You can't level down admin!");

        currentEmployee.setEmpStatus(EmployeeStatus.RESPONSE_TEACHER);
        secondPort.saveEmployee(currentEmployee);
        logger.info("Employee with id {} is response teacher now!", currentId);
        return currentEmployee;
    }

    //Кидает NoEmployeeException
    @Override
    public Employee empLevelLow(Integer currentId) {

        var currentEmployee = firstPort.findEmpById(currentId);

        if (currentEmployee.getEmpStatus().equals(EmployeeStatus.ADMIN))
            throw new IllegalArgumentException("Error! You can't level down admin!");

        currentEmployee.setEmpStatus(EmployeeStatus.TEACHER);
        secondPort.saveEmployee(currentEmployee);
        logger.info("Employee with id {} is teacher now!", currentId);
        return currentEmployee;
    }

    /*
   Кидает NoEmployeeException
   Пароли хешируются в маппере
   Проверка паролей - часть технического аспекта
    */
    @Override
    public Employee changePassword(Integer currentId, String newPassword, String newPassword2) {

        var currentEmployee = firstPort.findEmpById(currentId);
        if (!newPassword.equals(newPassword2))
            throw new IllegalArgumentException("Error! Passwords are not same! ");

        currentEmployee.setHashPass(newPassword);
        secondPort.saveEmployee(currentEmployee);
        logger.info("Employee with id {} changed password", currentId);
        return currentEmployee;
    }
}
