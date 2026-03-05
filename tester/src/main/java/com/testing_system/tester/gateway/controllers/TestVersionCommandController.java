package com.testing_system.tester.gateway.controllers;


import com.testing_system.tester.creation_test_module.core.ports.first.TestVersionUseCase;
import com.testing_system.tester.creation_test_module.infrastructure.mappers.TestVersionMapper;
;

/*
Задачи контроллера:

    - Создать версию
    - Удалить версию
    - Сделать черновиком/чистовиком
    - Старт прохождения (фасад)

Создать версию может автор/соавтор/админ
Удалить версию, сделать ее черновиком/чистовиком могут только автор и админ
 */
public class TestVersionCommandController {


    private final TestVersionUseCase firstPort;
    private final TestVersionMapper mapper;

    public TestVersionCommandController(TestVersionUseCase firstPort, TestVersionMapper mapper) {
        this.firstPort = firstPort;
        this.mapper = mapper;
    }
}
