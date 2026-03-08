package com.testing_system.tester.gateway.controllers;

import com.testing_system.tester.creation_test_module.core.ports.first.TestCommandUseCase;
import com.testing_system.tester.creation_test_module.infrastructure.mappers.TestMapper;
import com.testing_system.tester.testing_attempts_module.core.ports.first.TestingRulesUseCase;

/*
Задачи контроллера:

    - Создать тест (фасад: метаданные + правила + первая версия)
    - Удалить тест
    - Добавить/удалить соавтора
    - Изменить батч вопросов
    - Изменить количество попыток за день/общее

Создать тест может преподаватель
Удалить тест, изменить метаданные могут только автор теста и админ
 */
public class TestCommandController {

    private final TestCommandUseCase firstPort;
    private final TestingRulesUseCase firstPort1;
    private final TestMapper mapper;

    public TestCommandController(TestCommandUseCase firstPort, TestingRulesUseCase firstPort1, TestMapper mapper) {
        this.firstPort = firstPort;
        this.firstPort1 = firstPort1;
        this.mapper = mapper;
    }


}
