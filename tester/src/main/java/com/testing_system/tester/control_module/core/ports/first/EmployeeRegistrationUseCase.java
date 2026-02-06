package com.testing_system.tester.control_module.core.ports.first;

import com.testing_system.tester.control_module.core.domain.Employee;
import com.testing_system.tester.control_module.core.domain.EmployeeStatus;

import java.util.List;


/*
 Первичный порт для регистрации сотрудников
 Выполняет задачи:
  - CRUD для учетных записей сотрудников
    (удаление предполагает каскадирование; при регистрировании сотрудников админ сам вводит пароль, который может быть изменен самим сотрудником)
  - Фильтрация при поиске учетных записей сотрудников
  - Предоставление информации о наличии админа в базе
 */
public interface EmployeeRegistrationUseCase {

    public Employee findEmpById(Integer currentId);

    public List<Employee> findEmpByName(String currentFirstName, String currentLastName);

    public List<Employee> filterEmployeeByFirstName(String currentFirstName);

    public List<Employee> filterEmployeeByLastName(String currentLastName);

    public List<Employee> filterEmpByStatus(EmployeeStatus currentStatus);

    public List<Employee> getAllEmployee();

    public boolean adminInBase();

    public void regEmployee(Employee currentEmployee);

    public void deleteEmployee(Integer currentId);

}
