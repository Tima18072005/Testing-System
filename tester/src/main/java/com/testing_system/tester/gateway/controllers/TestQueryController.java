package com.testing_system.tester.gateway.controllers;

import com.testing_system.tester.creation_test_module.core.ports.first.TestQueryUseCase;
import com.testing_system.tester.creation_test_module.core.ports.first.TestVersionUseCase;
import com.testing_system.tester.creation_test_module.infrastructure.mappers.TestMapper;

/*
Задачи контроллера:

    - Доступ ко всем тестам (только метаданные)
    - Главная страница преподавателя (тесты его авторства/соавторства)
    - Главная страница студента (фасад: список пройденных и непройденных тестов)
    - Фильтрация по предмету/автору/соавтору (только метаданные)
    - Поиск по названию (только метаданные)
    - Доступ к информации теста (фасад:метаданные + правила прохождения + список всех версий)
    - Доступ к конкретной версии теста(метаданные + контент)

Все выше перечисленное доступно преподавателям
Главны страницы доступны только конкретным пользователям
 */
public class TestQueryController {

    private final TestMapper mapper;
    private final TestQueryUseCase firstPort;
    private final TestVersionUseCase firstPort1;

    public TestQueryController(TestMapper mapper, TestQueryUseCase firstPort, TestVersionUseCase firstPort1) {
        this.mapper = mapper;
        this.firstPort = firstPort;
        this.firstPort1 = firstPort1;
    }
}
