package com.testing_system.tester.gateway.controllers;


import com.testing_system.tester.creation_test_module.core.domain.Answer;
import com.testing_system.tester.creation_test_module.core.domain.Question;
import com.testing_system.tester.creation_test_module.core.domain.TestVersion;
import com.testing_system.tester.creation_test_module.core.ports.first.TestVersionUseCase;
import com.testing_system.tester.creation_test_module.infrastructure.dto.response.TestVersionFullDTO;
import com.testing_system.tester.creation_test_module.infrastructure.dto.response.TestingDTO;
import com.testing_system.tester.creation_test_module.infrastructure.mappers.AnswerMapper;
import com.testing_system.tester.creation_test_module.infrastructure.mappers.QuestionMapper;
import com.testing_system.tester.creation_test_module.infrastructure.mappers.TestVersionMapper;
import com.testing_system.tester.gateway.controllers.facades.StartTestingFacade;
import com.testing_system.tester.gateway.controllers.facades.TestVersionQueryFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/*
Задачи контроллера:

    - Создать версию
    - Удалить версию
    - Сделать черновиком/чистовиком
    - Старт прохождения (фасад)

Создать версию может автор/соавтор/админ
Удалить версию, сделать ее черновиком/чистовиком могут только автор и админ
 */
@RestController
@RequestMapping("/test-version")
public class TestVersionCommandController {


    private final TestVersionUseCase firstPort;
    private final TestVersionQueryFacade facade;
    private final StartTestingFacade facade1;

    private final TestVersionMapper mapper;
    private final QuestionMapper mapper1;
    private final AnswerMapper mapper2;

    public TestVersionCommandController(TestVersionUseCase firstPort, TestVersionQueryFacade facade, StartTestingFacade facade1, TestVersionMapper mapper, QuestionMapper mapper1, AnswerMapper mapper2) {
        this.firstPort = firstPort;
        this.facade = facade;
        this.facade1 = facade1;
        this.mapper = mapper;
        this.mapper1 = mapper1;
        this.mapper2 = mapper2;
    }


    @PostMapping("/make-new")
    public ResponseEntity<TestVersionFullDTO> makeNewVersion(@RequestBody TestVersionFullDTO currentDTO){

        TestVersion currentMetadata = mapper.dtoToDomain(currentDTO.versionMetadata());

        Map<Question, List<Answer>> currentContent = currentDTO.versionContent().entrySet().stream().collect(Collectors.toMap(
                        entry -> mapper1.dtoToDomain(entry.getKey()),
                        entry -> entry.getValue().stream().map(mapper2::dtoToDomain).toList()));

        firstPort.makeNewVersion(currentMetadata, currentContent);

        return ResponseEntity.status(HttpStatus.CREATED).body(new TestVersionFullDTO(currentDTO.versionMetadata(), currentDTO.versionContent()));
    }


    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteVersion(@PathVariable("id") Integer currentVersionId){

        firstPort.deleteVersion(currentVersionId);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/{id}/make/ready")
    public ResponseEntity<TestVersionFullDTO> makeReady(@PathVariable("id") Integer currentVersionId){

        firstPort.makeReady(currentVersionId);

        TestVersionFullDTO currentVersion = facade.getCurrentVersion(currentVersionId);
        return ResponseEntity.status(HttpStatus.FOUND).body(currentVersion);
    }


    @PostMapping("/{id}/make/draft")
    public ResponseEntity<TestVersionFullDTO> makeDraft(@PathVariable("id") Integer currentVersionId){

        firstPort.makeDraft(currentVersionId);

        TestVersionFullDTO currentVersion = facade.getCurrentVersion(currentVersionId);
        return ResponseEntity.status(HttpStatus.FOUND).body(currentVersion);
    }


    @GetMapping("/start-testing/{name}/{id}")
    public ResponseEntity<TestingDTO> startTestingDTO(@PathVariable("name") String currentTestName,@PathVariable("id") Integer currentStudentId){

        TestingDTO currentVersionForTesting = facade1.startTesting(currentStudentId, currentTestName);

        return ResponseEntity.status(HttpStatus.FOUND).body(currentVersionForTesting);
    }

}
