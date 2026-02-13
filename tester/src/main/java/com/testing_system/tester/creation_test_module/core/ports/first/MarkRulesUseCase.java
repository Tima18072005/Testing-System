package com.testing_system.tester.creation_test_module.core.ports.first;


import java.util.List;
import java.util.Map;

/*
 Первичный порт для работы с правилами оценивания
 Задачи:

    - Доступ к правилам оценивания
    - Расчет оценки после прохождения
 */
public interface MarkRulesUseCase {

    public Map<Integer, Integer> getMarkValues();

    public Map.Entry<Integer,Integer> calculateMark(List<Integer> currentAnswerIds);
}
