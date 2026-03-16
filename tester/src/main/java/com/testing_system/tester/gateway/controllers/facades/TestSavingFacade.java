package com.testing_system.tester.gateway.controllers.facades;

import com.testing_system.tester.creation_test_module.core.ports.first.TestCommandUseCase;
import com.testing_system.tester.creation_test_module.core.ports.first.TestVersionUseCase;
import com.testing_system.tester.creation_test_module.infrastructure.dto.response.TestDTOToSave;
import com.testing_system.tester.creation_test_module.infrastructure.dto.response.TestMainPageDTO;
import com.testing_system.tester.creation_test_module.infrastructure.mappers.AnswerMapper;
import com.testing_system.tester.creation_test_module.infrastructure.mappers.QuestionMapper;
import com.testing_system.tester.creation_test_module.infrastructure.mappers.TestMapper;
import com.testing_system.tester.creation_test_module.infrastructure.mappers.TestVersionMapper;
import com.testing_system.tester.testing_attempts_module.core.ports.first.TestingRulesUseCase;
import com.testing_system.tester.testing_attempts_module.infrastructure.mappers.TestingRulesMapper;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/*
Фасад для сохранения теста
 */
@Service
public class TestSavingFacade {

    private final TestCommandUseCase firstPort;
    private final TestingRulesUseCase firstPort1;
    private final TestVersionUseCase firstPort2;

    private final TestMapper mapper;
    private final TestingRulesMapper mapper1;
    private final TestVersionMapper mapper2;
    private final QuestionMapper mapper3;
    private final AnswerMapper mapper4;

    public TestSavingFacade(TestCommandUseCase firstPort, TestingRulesUseCase firstPort1, TestVersionUseCase firstPort2, TestMapper mapper, TestingRulesMapper mapper1, TestVersionMapper mapper2, QuestionMapper mapper3, AnswerMapper mapper4) {
        this.firstPort = firstPort;
        this.firstPort1 = firstPort1;
        this.firstPort2 = firstPort2;
        this.mapper = mapper;
        this.mapper1 = mapper1;
        this.mapper2 = mapper2;
        this.mapper3 = mapper3;
        this.mapper4 = mapper4;
    }

    public void saveNewTest(TestDTOToSave currentDTO){

        firstPort.makeTest(mapper.dtoToDomain(currentDTO.testMetadata()));

        firstPort1.makeTestingRules(mapper1.DTOToDomain(currentDTO.testingRules()));

        var firstVersionMetadata = mapper2.dtoToDomain(currentDTO.firstVersion().versionMetadata());

        var firstVersionContent = currentDTO.firstVersion().versionContent().entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> mapper3.dtoToDomain(entry.getKey()),
                        entry -> entry.getValue().stream().map(mapper4::dtoToDomain).toList()));

        firstPort2.makeNewVersion(firstVersionMetadata, firstVersionContent);
    }
}
