package com.testing_system.tester.gateway.controllers;

import com.testing_system.tester.creation_test_module.core.ports.first.MarkRulesUseCase;
import com.testing_system.tester.testing_attempts_module.core.ports.first.AttemptHistoryUseCase;
import com.testing_system.tester.testing_attempts_module.core.ports.first.TestingRulesUseCase;
import com.testing_system.tester.testing_attempts_module.infrastructure.mappers.TestAttemptMapper;

/*
Задачи контроллера:

    - Сохранение истории прохождений (расчет оценки + сохранение результатов)
    - Чтение истории прохождений теста
    - Чтение истории прохождений для конкретного студента
    - Удаление истории сообщений
    - Чтение правил оценивания

Чтение общей истории прохождений для теста доступны для преподавателя
 */
public class HistoryController {

    private final AttemptHistoryUseCase firstPort;
    private final MarkRulesUseCase firstPort1;
    // Добавить фасад для сохранения
    private final TestAttemptMapper mapper;

    public HistoryController(AttemptHistoryUseCase firstPort, MarkRulesUseCase firstPort1, TestAttemptMapper mapper) {
        this.firstPort = firstPort;
        this.firstPort1 = firstPort1;
        this.mapper = mapper;
    }
}
