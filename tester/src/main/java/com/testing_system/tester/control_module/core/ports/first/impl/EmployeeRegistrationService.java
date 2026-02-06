package com.testing_system.tester.control_module.core.ports.first.impl;

import com.testing_system.tester.control_module.core.domain.Employee;
import com.testing_system.tester.control_module.core.domain.EmployeeStatus;
import com.testing_system.tester.control_module.core.ports.first.EmployeeRegistrationUseCase;
import com.testing_system.tester.control_module.core.ports.first.exceptions.NoEmployeeException;
import com.testing_system.tester.control_module.core.ports.second.EmployeeDrivenUseCase;

import java.util.List;

/*
 Сервис-оркестратор для работы с учетными записями сотрудников
 */
public class EmployeeRegistrationService implements EmployeeRegistrationUseCase {

    /*
     Реализация использует только вторичный порт
     */
    private final EmployeeDrivenUseCase secondPort;

    public EmployeeRegistrationService(EmployeeDrivenUseCase secondPort) {
        this.secondPort = secondPort;
    }

    @Override
    public Employee findEmpById(Integer currentId) {
        return secondPort.getEmployee(currentId).orElseThrow(
                ()-> new NoEmployeeException("Employee not found! Employee ID: " + currentId));
    }

    /*
    При отсутствии учетных записей контроллер вернёт соответствующее DTO
    Метод может быть изменён посредствам расширения вторичного порта
     */
    @Override
    public List<Employee> findEmpByName(String currentFirstName, String currentLastName) {
        return getAllEmployee().stream()
                .filter(employee -> employee.getFirstName().equals(currentFirstName))
                .filter(employee -> employee.getLastName().equals(currentLastName))
                .toList();
    }

    /*
    При отсутствии учетных записей контроллер вернёт соответствующее DTO
    Метод может быть изменён посредствам расширения вторичного порта
     */
    @Override
    public List<Employee> filterEmployeeByFirstName(String currentFirstName) {
        return getAllEmployee().stream()
                .filter(employee -> employee.getFirstName().equals(currentFirstName))
                .toList();
    }

    /*
    При отсутствии учетных записей контроллер вернёт соответствующее DTO
    Метод может быть изменён посредствам расширения вторичного порта
     */
    @Override
    public List<Employee> filterEmployeeByLastName(String currentLastName) {
        return getAllEmployee().stream()
                .filter(employee -> employee.getLastName().equals(currentLastName))
                .toList();
    }

    /*
    При отсутствии учетных записей контроллер вернёт соответствующее DTO
    Метод может быть изменён посредствам расширения вторичного порта
     */
    @Override
    public List<Employee> filterEmpByStatus(EmployeeStatus currentStatus) {
        return getAllEmployee().stream()
                .filter(employee -> employee.getEmpStatus().equals(currentStatus))
                .toList();
    }


    @Override
    public List<Employee> getAllEmployee() {
        return secondPort.getAllEmployee();
    }

    /*
    Используется только фильтром для проверки наличия админа при каждом запросе
     */
    @Override
    public boolean adminInBase() {

        return secondPort.getAdmin().isPresent();
    }

    /*
    Назначение статуса происходит при маппинге из ДТО в доменную сущность
     */
    @Override
    public void regEmployee(Employee currentEmployee) {

        if (secondPort.getEmployee(currentEmployee.getEmpId()).isPresent())
            throw new IllegalArgumentException("Error! You can't save employee with same ids! Employee ID: "
                    + currentEmployee.getEmpId());

        secondPort.saveEmployee(currentEmployee);

    }

    /*
    Метод предполагает каскадирование
    При удалении сотрудника удаляются все его тесты, версии тестов из под его редактуры, попытки прохождения
     */
    @Override
    public void deleteEmployee(Integer currentId) {

        if (secondPort.getEmployee(currentId).isEmpty())
            throw new IllegalArgumentException("Error! You can't delete non-existent employee! Employee ID: "
                    + currentId);

        secondPort.deleteEmployee(currentId);
    }
}
