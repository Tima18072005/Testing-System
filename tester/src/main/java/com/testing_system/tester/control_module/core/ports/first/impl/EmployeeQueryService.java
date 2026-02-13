package com.testing_system.tester.control_module.core.ports.first.impl;

import com.testing_system.tester.control_module.core.domain.Employee;
import com.testing_system.tester.control_module.core.domain.EmployeeStatus;
import com.testing_system.tester.control_module.core.ports.first.EmployeeQueryUseCase;
import com.testing_system.tester.control_module.core.ports.first.exceptions.NoEmployeeException;
import com.testing_system.tester.control_module.core.ports.second.EmployeeDrivenUseCase;

import java.util.List;

/*
 Сервис-оркестратор для предоставления доступа к данным учетных записей сотрудника
 */
public class EmployeeQueryService implements EmployeeQueryUseCase {

    // Реализация использует вторичный порт
    private final EmployeeDrivenUseCase secondPort;

    public EmployeeQueryService(EmployeeDrivenUseCase secondPort) {
        this.secondPort = secondPort;
    }

    @Override
    public boolean getEmployee(Integer currentEmployeeId) {
        return secondPort.getEmployee(currentEmployeeId).isPresent();
    }

    // Используется фильтром для проверки наличия админа в базе
    @Override
    public boolean getAdmin() {
        return secondPort.getAdmin().isPresent();
    }

    @Override
    public Employee findEmpById(Integer currentId) {
        return secondPort.getEmployee(currentId).orElseThrow(
                ()-> new NoEmployeeException("Employee not found! Employee ID: " + currentId));
    }

    @Override
    public List<Employee> getAllEmployee() {
        return secondPort.getAllEmployee();
    }

    // Следующие методы можно ускорить при помощи расширения вторичного порта

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


}
