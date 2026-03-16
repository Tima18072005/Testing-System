package com.testing_system.tester.gateway.controllers;

import com.testing_system.tester.creation_test_module.core.ports.first.TestCommandUseCase;
import com.testing_system.tester.creation_test_module.infrastructure.dto.response.TestDTOToSave;
import com.testing_system.tester.creation_test_module.infrastructure.dto.response.TestMainPageDTO;
import com.testing_system.tester.gateway.controllers.facades.TestSavingFacade;
import com.testing_system.tester.gateway.controllers.facades.TestVersionQueryFacade;
import com.testing_system.tester.testing_attempts_module.core.ports.first.TestingRulesUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*
Задачи контроллера:

    - Создать тест (фасад: метаданные + правила + первая версия)
    - Удалить тест
    - Добавить/удалить соавтора
    - Изменить батч вопросов
    - Изменить количество попыток за день/общее

Создать тест может преподаватель
Удалить тест, изменить метаданные могут только автор теста и админ
 */
@RestController
@RequestMapping("/test")
public class TestCommandController {

    private final TestCommandUseCase firstPort;
    private final TestingRulesUseCase firstPort1;
    private final TestSavingFacade facade;
    private final TestVersionQueryFacade facade1;


    public TestCommandController(TestCommandUseCase firstPort, TestingRulesUseCase firstPort1, TestSavingFacade facade, TestVersionQueryFacade facade1) {
        this.firstPort = firstPort;
        this.firstPort1 = firstPort1;
        this.facade = facade;
        this.facade1 = facade1;
    }

    @PostMapping("/make/new-test")
    public ResponseEntity<TestMainPageDTO> saveNewTest(@RequestBody TestDTOToSave currentDTO){

        facade.saveNewTest(currentDTO);
        TestMainPageDTO newTestDTO = facade1.getTestMainPage(currentDTO.testMetadata().getTestName());

        return ResponseEntity.status(HttpStatus.CREATED).body(newTestDTO);
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<Void> deleteTest(@PathVariable("name") String currentTestName){

        firstPort.deleteTest(currentTestName);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("{name}/add/co-author")
    public ResponseEntity<TestMainPageDTO> makeNewCoAuthor(@PathVariable("name") String currentTestName,@RequestBody Integer coAuthorId){

        firstPort.makeCoAuthor(currentTestName, coAuthorId);
        var testMainPage = facade1.getTestMainPage(currentTestName);
        return ResponseEntity.status(HttpStatus.FOUND).body(testMainPage);
    }

    @PostMapping("/{name}/delete/co-author")
    public ResponseEntity<TestMainPageDTO> deleteCoAuthor(@PathVariable("name") String currentTestName,@RequestBody Integer coAuthorId){

        firstPort.deleteCoAuthor(currentTestName,coAuthorId);
        var testMainPage = facade1.getTestMainPage(currentTestName);
        return ResponseEntity.status(HttpStatus.FOUND).body(testMainPage);
    }

    @PostMapping("/{name}/change/question-batch")
    public ResponseEntity<TestMainPageDTO> changeQuestionBatch(@PathVariable("name") String currentTestName,@RequestBody Integer newBatchSize){

        firstPort.changeQuestionBatch(currentTestName, newBatchSize);
        var testMainPage = facade1.getTestMainPage(currentTestName);
        return ResponseEntity.status(HttpStatus.FOUND).body(testMainPage);
    }

    @PostMapping("/{name}/change/day-attempts")
    public ResponseEntity<TestMainPageDTO> changeDayAttempt(@PathVariable("name") String currentTestName,@RequestBody Integer newDayAttempts){

        firstPort1.changeDayAttempts(currentTestName, newDayAttempts);
        var testMainPage = facade1.getTestMainPage(currentTestName);
        return ResponseEntity.status(HttpStatus.FOUND).body(testMainPage);
    }

    @PostMapping("/{name}/change/all-attempts")
    public ResponseEntity<TestMainPageDTO> changeAllAttempt(@PathVariable("name") String currentTestName,@RequestBody Integer newAllAttempts){

        firstPort1.changeAllAttempts(currentTestName, newAllAttempts);
        var testMainPage = facade1.getTestMainPage(currentTestName);
        return ResponseEntity.status(HttpStatus.FOUND).body(testMainPage);
    }

}
