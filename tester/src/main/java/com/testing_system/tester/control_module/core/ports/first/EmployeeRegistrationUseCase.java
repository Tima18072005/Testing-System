package com.testing_system.tester.control_module.core.ports.first;

import com.testing_system.tester.control_module.core.domain.Employee;



/*
 Первичный порт для регистрации сотрудников
 Выполняет задачи:
  - CRUD для учетных записей сотрудников
    (удаление предполагает каскадирование; при регистрировании сотрудников админ сам вводит пароль, который может быть изменен самим сотрудником)

 */
public interface EmployeeRegistrationUseCase {

    public Employee regEmployee(Employee currentEmployee);

    public void deleteEmployee(Integer currentId);

}
