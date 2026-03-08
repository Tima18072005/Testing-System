package com.testing_system.tester.gateway.controllers.facades;

import com.testing_system.tester.creation_test_module.core.ports.first.TestQueryUseCase;
import com.testing_system.tester.creation_test_module.core.ports.first.TestVersionUseCase;
import com.testing_system.tester.creation_test_module.infrastructure.dto.response.*;
import com.testing_system.tester.creation_test_module.infrastructure.mappers.AnswerMapper;
import com.testing_system.tester.creation_test_module.infrastructure.mappers.QuestionMapper;
import com.testing_system.tester.creation_test_module.infrastructure.mappers.TestMapper;
import com.testing_system.tester.creation_test_module.infrastructure.mappers.TestVersionMapper;
import com.testing_system.tester.testing_attempts_module.core.ports.first.TestingRulesUseCase;
import com.testing_system.tester.testing_attempts_module.infrastructure.dto.response.TestingRulesDTO;
import com.testing_system.tester.testing_attempts_module.infrastructure.mappers.TestingRulesMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
Фасад для доступа к версиям теста
 */
@Service
public class TestVersionQueryFacade {

    private final TestQueryUseCase firstPort;
    private final TestingRulesUseCase firstPort1;
    private final TestVersionUseCase firstPort2;

    private final TestMapper mapper;
    private final TestingRulesMapper mapper1;
    private final TestVersionMapper mapper2;
    private final QuestionMapper mapper3;
    private final AnswerMapper mapper4;

    public TestVersionQueryFacade(TestQueryUseCase firstPort, TestingRulesUseCase firstPort1, TestVersionUseCase firstPort2, TestMapper mapper, TestingRulesMapper mapper1, TestVersionMapper mapper2, QuestionMapper mapper3, AnswerMapper mapper4) {
        this.firstPort = firstPort;
        this.firstPort1 = firstPort1;
        this.firstPort2 = firstPort2;
        this.mapper = mapper;
        this.mapper1 = mapper1;
        this.mapper2 = mapper2;
        this.mapper3 = mapper3;
        this.mapper4 = mapper4;
    }

    public TestMainPageDTO getTestMainPage(String currentTestName){

        TestDTO currentTestMetadata = mapper.domainToDTO(firstPort.getTestByName(currentTestName));

        TestingRulesDTO currentTestingRules = mapper1.domainToDTO(firstPort1.getTestingRules(currentTestName));

        List<TestVersionDTO> allTestVersions = firstPort2.getAllTestVersion(currentTestName)
                .stream().map(mapper2::domainToDTO).toList();

        return new TestMainPageDTO(currentTestMetadata, currentTestingRules, allTestVersions);
    }

    public TestVersionFullDTO getCurrentVersion(Integer currentVersionId){

        TestVersionDTO currentMetadata = mapper2.domainToDTO(firstPort2.getVersionMetaDataById(currentVersionId));

        Map<QuestionDTO, List<AnswerDTO>> currentVersionContent = firstPort2.getVersionContentById(currentVersionId)
                .entrySet().stream().collect(Collectors.toMap(
                        entry->mapper3.domainToDTO(entry.getKey()),
                        entry -> entry.getValue().stream().map(mapper4::domainToDTO).toList()));

        return new TestVersionFullDTO(currentMetadata, currentVersionContent);
    }
}
