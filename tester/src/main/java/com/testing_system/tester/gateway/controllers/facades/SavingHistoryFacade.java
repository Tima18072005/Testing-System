package com.testing_system.tester.gateway.controllers.facades;

import com.testing_system.tester.creation_test_module.core.ports.first.MarkRulesUseCase;
import com.testing_system.tester.testing_attempts_module.core.domain.TestAttempt;
import com.testing_system.tester.testing_attempts_module.core.ports.first.AttemptHistoryUseCase;
import com.testing_system.tester.testing_attempts_module.infrastructure.dto.response.TestAttemptDTOToSave;
import org.springframework.stereotype.Service;

import java.util.List;


/*
Фасад для сохранения истории
 */
@Service
public class SavingHistoryFacade {

    private final AttemptHistoryUseCase firstPort;
    private final MarkRulesUseCase firstPort1;

    public SavingHistoryFacade(AttemptHistoryUseCase firstPort, MarkRulesUseCase firstPort1) {
        this.firstPort = firstPort;
        this.firstPort1 = firstPort1;
    }

    public TestAttempt saveAttempt(TestAttemptDTOToSave dtoToSave, List<Integer> currentAnswersIds){

        var marks = firstPort1.calculateMark(currentAnswersIds);

        TestAttempt newAttempt = new TestAttempt(dtoToSave.testerId(), dtoToSave.testName(), marks.getKey(), marks.getValue(), dtoToSave.date(), dtoToSave.IP());
        firstPort.saveAttemptToHistory(newAttempt);
        return newAttempt;
    }
}
