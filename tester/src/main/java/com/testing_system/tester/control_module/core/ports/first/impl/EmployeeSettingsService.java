package com.testing_system.tester.control_module.core.ports.first.impl;

import com.testing_system.tester.control_module.core.domain.EmployeeStatus;
import com.testing_system.tester.control_module.core.ports.first.EmployeeRegistrationUseCase;
import com.testing_system.tester.control_module.core.ports.first.EmployeeSettingsUseCase;
import com.testing_system.tester.control_module.core.ports.second.EmployeeDrivenUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 Сервис-оркестратор для изменения данных в учетных записях сотрудника
 */
public class EmployeeSettingsService implements EmployeeSettingsUseCase {


    /*
    Реализация использует первичный и вторичный порты
     */
    private final EmployeeRegistrationUseCase firstPort;
    private final EmployeeDrivenUseCase secondPort;
    private final Logger logger = LoggerFactory.getLogger(EmployeeSettingsService.class);

    public EmployeeSettingsService(EmployeeRegistrationUseCase firstPort, EmployeeDrivenUseCase secondPort) {
        this.firstPort = firstPort;
        this.secondPort = secondPort;
    }

    /*
    Кидает NoEmployeeException
     */
    @Override
    public void empLevelUp(Integer currentId) {

        var currentEmployee = firstPort.findEmpById(currentId);
        currentEmployee.setEmpStatus(EmployeeStatus.RESPONSE_TEACHER);
        secondPort.saveEmployee(currentEmployee);
        logger.info("Employee with id {} is response teacher now!", currentId);

    }

    /*
   Кидает NoEmployeeException
    */
    @Override
    public void empLevelLow(Integer currentId) {

        var currentEmployee = firstPort.findEmpById(currentId);
        currentEmployee.setEmpStatus(EmployeeStatus.TEACHER);
        secondPort.saveEmployee(currentEmployee);
        logger.info("Employee with id {} is teacher now!", currentId);

    }

    /*
   Кидает NoEmployeeException
   Пароли хешируются в маппере
   Проверка паролей - часть технического аспекта
    */
    @Override
    public void changePassword(Integer currentId, String newPassword, String newPassword2) {

        var currentEmployee = firstPort.findEmpById(currentId);
        if (!newPassword.equals(newPassword2))
            throw new IllegalArgumentException("Error! Passwords are not same! ");

        currentEmployee.setHashPass(newPassword);
        secondPort.saveEmployee(currentEmployee);
        logger.info("Employee with id {} changed password", currentId);

    }
}
