package com.testing_system.tester.control_module.core.ports.first;

// Первичный порт на изменение настроек учетной записи сотрудника
public interface EmployeeSettingsUseCase{

    // Повышение/понижение уровня доступа сотрудника

    public void empLevelUp(Integer currentId);

    public void empLevelLow(Integer currentId);

    public void changePassword(Integer currentId, String newPassword);
}
