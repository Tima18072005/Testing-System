package com.testing_system.tester.creation_test_module.core.ports.first.impl;

import com.testing_system.tester.creation_test_module.core.domain.AnswerStatus;
import com.testing_system.tester.creation_test_module.core.domain.MarkValues;
import com.testing_system.tester.creation_test_module.core.ports.first.MarkRulesUseCase;
import com.testing_system.tester.creation_test_module.core.ports.second.TestVersionDrivenUseCase;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/*
 Сервис-оркестратор для оценивания
 */
@Service
public class MarkRulesService implements MarkRulesUseCase {

    // Реализация использует вторичный порт
    private final TestVersionDrivenUseCase secondPort;

    public MarkRulesService(TestVersionDrivenUseCase secondPort) {
        this.secondPort = secondPort;
    }

    @Override
    public Map<Integer, Integer> getMarkValues() {
         return MarkValues.getAllValues();
    }


    @Override
    public Map.Entry<Integer, Integer> calculateMark(List<Integer> currentAnswerIds) {

        var currentStatuses = secondPort.getAnswers(currentAnswerIds).stream()
                .filter(answer -> answer.getAnswerStatus().equals(AnswerStatus.CORRECT))
                .toList();

        // Проверить правильность вычислений
        int mark100 = (int) (100 *  (double)(currentStatuses.size() / currentAnswerIds.size()));

        if (mark100 < MarkValues.PASSED.mark100) return Map.entry(mark100, 2);

        if (mark100 < MarkValues.GOOD.mark100) return Map.entry(mark100, MarkValues.PASSED.mark5);

        if (mark100 < MarkValues.EXCELLENT.mark100) return Map.entry(mark100, MarkValues.GOOD.mark5);

        return Map.entry(mark100, MarkValues.EXCELLENT.mark5);
    }
}
