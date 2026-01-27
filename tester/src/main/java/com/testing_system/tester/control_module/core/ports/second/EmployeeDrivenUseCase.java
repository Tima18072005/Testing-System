package com.testing_system.tester.control_module.core.ports.second;


import com.testing_system.tester.control_module.core.domain.Employee;

import java.util.List;
import java.util.Optional;

// Вторичный порт для работы с данными сотрудника
public interface EmployeeDrivenUseCase {

    public List<Employee> getAllEmployee();

    public Optional<Employee> getEmployee(Integer currentId);

    public void saveEmployee(Employee currentEmployee);

    public void deleteEmployee(Integer currentId);
}
