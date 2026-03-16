package com.testing_system.tester.gateway.controllers.facades;

import com.testing_system.tester.creation_test_module.core.ports.first.TestVersionUseCase;
import com.testing_system.tester.creation_test_module.infrastructure.dto.response.TestingDTO;
import com.testing_system.tester.creation_test_module.infrastructure.mappers.AnswerMapper;
import com.testing_system.tester.creation_test_module.infrastructure.mappers.QuestionMapper;
import com.testing_system.tester.creation_test_module.infrastructure.mappers.TestVersionMapper;
import com.testing_system.tester.testing_attempts_module.core.ports.first.TestingRulesUseCase;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/*
Фасад для старта прохождения тестирования
 */
@Service
public class StartTestingFacade {

    private final TestVersionUseCase firstPort;
    private final TestingRulesUseCase firstPort1;

    private final TestVersionMapper mapper; //?
    private final QuestionMapper mapper1;
    private final AnswerMapper mapper2;

    public StartTestingFacade(TestVersionUseCase firstPort, TestingRulesUseCase firstPort1, TestVersionMapper mapper, QuestionMapper mapper1, AnswerMapper mapper2) {
        this.firstPort = firstPort;
        this.firstPort1 = firstPort1;
        this.mapper = mapper;
        this.mapper1 = mapper1;
        this.mapper2 = mapper2;
    }

    public TestingDTO startTesting(Integer studentId, String currentTestName){

        if (!firstPort1.canTestingToDay(currentTestName, studentId)) throw new IllegalStateException(
                "Error! You can't testing more than %d attempts in one day!".formatted(
                        firstPort1.getTestingRules(currentTestName).getDayAttempts()));


        var currentContent = firstPort.getLastUsableVersion(currentTestName).entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> mapper1.domainToDTO(entry.getKey()),
                        entry -> entry.getValue().stream().map(mapper2::domainToTestingDTO).toList()));

        return new TestingDTO(currentContent);
    }
}
