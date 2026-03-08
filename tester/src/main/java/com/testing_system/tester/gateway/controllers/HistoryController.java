package com.testing_system.tester.gateway.controllers;

import com.testing_system.tester.creation_test_module.core.ports.first.MarkRulesUseCase;
import com.testing_system.tester.gateway.controllers.facades.SavingHistoryFacade;
import com.testing_system.tester.testing_attempts_module.infrastructure.dto.response.TestAttemptDTO;
import com.testing_system.tester.testing_attempts_module.core.ports.first.AttemptHistoryUseCase;
import com.testing_system.tester.testing_attempts_module.infrastructure.dto.response.TestAttemptDTOToSave;
import com.testing_system.tester.testing_attempts_module.infrastructure.mappers.TestAttemptMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/*
Задачи контроллера:

    - Сохранение истории прохождений (расчет оценки + сохранение результатов)
    - Чтение истории прохождений теста
    - Чтение истории прохождений для конкретного студента
    - Удаление истории сообщений
    - Чтение правил оценивания

Чтение общей истории прохождений для теста доступны для преподавателя
 */
@RestController
@RequestMapping("/test-attempts")
public class HistoryController {

    private final AttemptHistoryUseCase firstPort;
    private final MarkRulesUseCase firstPort1;
    private final SavingHistoryFacade facade;
    private final TestAttemptMapper mapper;

    public HistoryController(AttemptHistoryUseCase firstPort, MarkRulesUseCase firstPort1, SavingHistoryFacade facade, TestAttemptMapper mapper) {
        this.firstPort = firstPort;
        this.firstPort1 = firstPort1;
        this.facade = facade;
        this.mapper = mapper;
    }

    @GetMapping("/get-history/{name}")
    public ResponseEntity<List<TestAttemptDTO>> getTestingHistory(@PathVariable("name") String currentTestName){

        var testHistory = firstPort.getTestingHistory(currentTestName);
        return ResponseEntity.status(HttpStatus.FOUND).body(testHistory.stream().map(mapper::domainToDTO).toList());
    }


    @GetMapping("/get-history/{name}/{id}")
    public ResponseEntity<List<TestAttemptDTO>> getStudentTestingHistory(@PathVariable("id") Integer currentStudentId,
                                                                         @PathVariable("name") String currentTestName){

        var studentHistory = firstPort.getTestingHistory(currentTestName, currentStudentId);
        return ResponseEntity.status(HttpStatus.FOUND).body(studentHistory.stream().map(mapper::domainToDTO).toList());
    }


    @GetMapping("/get-attempt/{id}")
    public ResponseEntity<TestAttemptDTO> getTestAttempt(@PathVariable("id") Integer currentId){

        var testAttempt = firstPort.getAttemptById(currentId);
        return ResponseEntity.status(HttpStatus.FOUND).body(mapper.domainToDTO(testAttempt));
    }


    @PostMapping("/save/new_attempt") // При первом сохранении id = null
    public ResponseEntity<TestAttemptDTO> saveTestAttempt(@RequestBody TestAttemptDTOToSave currentDto, @RequestBody List<Integer> answerIds){

        var newAttempt = facade.saveAttempt(currentDto, answerIds);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.domainToDTO(newAttempt));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAttempt(@PathVariable("id")Integer currentId){

        firstPort.deleteAttemptById(currentId);
        return ResponseEntity.noContent().build();
    }



    @DeleteMapping("/delete-history/{name}")
    public ResponseEntity<Void> deleteHistory(@PathVariable("name") String currentTestName){
        firstPort.getTestingHistory(currentTestName);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/get-marks")
    public ResponseEntity<Map<Integer, Integer>> getMarkValues(){

        return ResponseEntity.status(HttpStatus.FOUND).body(firstPort1.getMarkValues());
    }

}
