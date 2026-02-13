package com.testing_system.tester.control_module.core.ports.first;

import com.testing_system.tester.control_module.core.domain.Employee;
import com.testing_system.tester.control_module.core.domain.EmployeeStatus;

import java.util.List;

/*
Первичный порт
Задачи:
    - Предоставление доступа к учетным записям сотрудников
    - Фильтрация данных
    - Проверка наличия сотрудников в базе
    - Проверка наличия админа в базе
 */
public interface EmployeeQueryUseCase {

    public boolean getEmployee(Integer currentEmployeeId);

    public boolean getAdmin();

    public Employee findEmpById(Integer currentId);

    public List<Employee> findEmpByName(String currentFirstName, String currentLastName);

    public List<Employee> filterEmployeeByFirstName(String currentFirstName);

    public List<Employee> filterEmployeeByLastName(String currentLastName);

    public List<Employee> filterEmpByStatus(EmployeeStatus currentStatus);

    public List<Employee> getAllEmployee();

}
