package com.testing_system.tester.creation_test_module.core.ports.first;

import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;
import java.util.Map;

// Первичный порт для работы с правилами оценивания, планируется расширение
public interface MarkRulesUseCase {

    public Map<Integer, Integer> getMarkValues();

    public Map.Entry<Integer,Integer> calculateMark(List<Integer> currentAnswerIds);
}
