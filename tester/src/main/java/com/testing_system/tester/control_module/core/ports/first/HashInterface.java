package com.testing_system.tester.control_module.core.ports.first;

// Первичный порт хеширование пароля
public interface HashInterface {

    // Хеширование пароля
    public String makeHash(String currentPassword);

    // Проверка совпадения введённого пароля с хешем
    public boolean isRightPassword(String currentPassword);

    // Проверка на совпадение двух паролей
    public boolean isSame(String newPassword, String newPassword2);
}
