package com.testing_system.tester.control_module.core.ports.first;

/*
 Первичный порт.
 Выполняемые задачи:
  - Повышение/понижение уровня доступа сотрудника
  - Смена сотрудником пароля (программа работает с хешем пароля)
 */
public interface EmployeeSettingsUseCase{

    public void empLevelUp(Integer currentId);

    public void empLevelLow(Integer currentId);

    public void changePassword(Integer currentId, String newPassword, String newPassword2);
}
