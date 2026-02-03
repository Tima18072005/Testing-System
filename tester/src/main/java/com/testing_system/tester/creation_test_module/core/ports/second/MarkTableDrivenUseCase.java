package com.testing_system.tester.creation_test_module.core.ports.second;



import com.testing_system.tester.creation_test_module.core.domain.Answer;
import com.testing_system.tester.creation_test_module.core.domain.MarkTable;

import java.util.List;
import java.util.Optional;

// Вторичный порт для работы с настройками оценок

public interface MarkTableDrivenUseCase {

    public void saveMarkTable(MarkTable currentTable);

    public void deleteMarkTable();

    public Optional<MarkTable> getMarkTable();

    // Поиск вопросов для подсчёта оценки

    public List<Answer> getAnswers(List<Integer> currentAnswersIds);
}
