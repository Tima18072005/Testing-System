package com.testing_system.tester.gateway.controllers;

import com.testing_system.tester.testing_attempts_module.core.ports.first.AttemptHistoryUseCase;
import com.testing_system.tester.testing_attempts_module.core.ports.first.TestingRulesUseCase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
Задачи контроллера:

    - Проведение тестирований
    - Расчет оценки
    - Сохранение истории прохождений
    - Доступ к истории прохождений теста
    - Удаление истории прохождений
    - Доступ к правилам оценивания
    - Доступ к истории прохождений для конкретного студента

Чтение данных доступно студентам
Доступ к общей истории прохождений теста доступен только преподавателям
Расчёт оценки и сохранение попытки производится автоматически после прохождения теста
Очистка истории доступна только админу
 */
@RestController
@RequestMapping("/testing")
public class TestingController {

    private final AttemptHistoryUseCase historyFirstPort;
    private final TestingRulesUseCase rulesFirstPort;

    public TestingController(AttemptHistoryUseCase historyFirstPort, TestingRulesUseCase rulesFirstPort) {
        this.historyFirstPort = historyFirstPort;
        this.rulesFirstPort = rulesFirstPort;
    }
}
