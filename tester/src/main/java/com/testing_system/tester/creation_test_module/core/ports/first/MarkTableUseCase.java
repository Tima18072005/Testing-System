package com.testing_system.tester.creation_test_module.core.ports.first;


import com.testing_system.tester.creation_test_module.core.domain.MarkTable;

import java.util.List;

// Первичный порт для работы с настройками оценок

public interface MarkTableUseCase {

    // Добавить создание+инициализацию при отсутствии
    public MarkTable getMarkTable();

    public void saveMarkTable(MarkTable currentTable);

    public void deleteMarkTable();

    public void changePassedMinimal();

    public void changePassedGood();

    public void changePassedExcellent();

    // Использовать функцию getMarkTable()
    public Integer calculateMark(List<Integer> currentAnswerIds);
}
