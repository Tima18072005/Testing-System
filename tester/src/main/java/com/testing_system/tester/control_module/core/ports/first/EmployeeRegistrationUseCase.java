package com.testing_system.tester.control_module.core.ports.first;

import com.testing_system.tester.control_module.core.domain.Employee;
import com.testing_system.tester.control_module.core.domain.EmployeeStatus;

import java.util.List;
import java.util.Optional;

// Первичный порт для регистрации сотрудников
public interface EmployeeRegistrationUseCase {

    // Функции поиска

    public Employee findEmpById(Integer currentId);

    public List<Employee> findEmpByName(String currentFirstName, String currentLastName);

    public List<Employee> filterEmpByStatus(EmployeeStatus currentStatus);

    public List<Employee> getAllEmployee();

    public boolean adminInBase(); // Проверка наличия админа в базе данных для фильтра

    // Функции регистрирования/удаления

    public void regEmployee(Employee currentEmployee);

    public void deleteEmployee(Integer currentId);

}
