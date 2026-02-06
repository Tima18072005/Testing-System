package com.testing_system.tester.creation_test_module.core.ports.second;

import com.testing_system.tester.creation_test_module.core.domain.Answer;

import java.util.List;

// Вторчный порт для доступа к ответам для расчёта оценки
public interface AnswerDrivenUseCase {

    public List<Answer> getAnswers(List<Integer> currentAnswerIds);
}
