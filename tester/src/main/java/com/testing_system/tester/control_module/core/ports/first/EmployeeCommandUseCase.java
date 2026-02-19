package com.testing_system.tester.control_module.core.ports.first;

import com.testing_system.tester.control_module.core.domain.Employee;

/*
 Первичный порт.
 Выполняемые задачи:
  - Повышение/понижение уровня доступа сотрудника
  - Смена сотрудником пароля (программа работает с хешем пароля)
 */
public interface EmployeeCommandUseCase{

    public Employee empLevelUp(Integer currentId);

    public Employee empLevelLow(Integer currentId);

    public Employee changePassword(Integer currentId, String newPassword, String newPassword2);
}
