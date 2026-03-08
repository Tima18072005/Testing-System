package com.testing_system.tester.gateway.controllers;


import com.testing_system.tester.creation_test_module.core.domain.Test;
import com.testing_system.tester.creation_test_module.core.ports.first.TestQueryUseCase;
import com.testing_system.tester.creation_test_module.core.ports.first.TestVersionUseCase;
import com.testing_system.tester.creation_test_module.infrastructure.dto.response.*;
import com.testing_system.tester.creation_test_module.infrastructure.mappers.TestMapper;
import com.testing_system.tester.gateway.controllers.facades.StudentMainPageFacade;
import com.testing_system.tester.gateway.controllers.facades.TestVersionQueryFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
Задачи контроллера:

    - Доступ ко всем тестам (только метаданные)
    - Главная страница преподавателя (тесты его авторства/соавторства)
    - Главная страница студента (фасад: список пройденных и непройденных тестов)
    - Фильтрация по предмету/автору/соавтору (только метаданные)
    - Поиск по названию (только метаданные)
    - Доступ к информации теста (фасад:метаданные + правила прохождения + список всех версий)
    - Доступ к конкретной версии теста(метаданные + контент)

Все выше перечисленное доступно преподавателям
Главны страницы доступны только конкретным пользователям
 */
@RestController
@RequestMapping("/test")
public class TestQueryController {

    private final TestMapper mapper;

    private final TestQueryUseCase firstPort;
    private final TestVersionUseCase firstPort1;//?

    private final StudentMainPageFacade facade;
    private final TestVersionQueryFacade facade1;

    public TestQueryController(TestMapper mapper, TestQueryUseCase firstPort, TestVersionUseCase firstPort1, StudentMainPageFacade facade, TestVersionQueryFacade facade1) {
        this.mapper = mapper;
        this.firstPort = firstPort;
        this.firstPort1 = firstPort1;
        this.facade = facade;
        this.facade1 = facade1;
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<TestDTO>> getAllTest(){

        List<Test> allTests = firstPort.getAllTests();
        return ResponseEntity.status(HttpStatus.FOUND).body(allTests.stream().map(mapper::domainToDTO).toList());

    }

    @GetMapping("/employee-main-page/{id}")
    public ResponseEntity<EmployeeMainPageDTO> employeeMainPage(@PathVariable("id") Integer currentId){

            List<Test> authorTests = firstPort.getAuthorTests(currentId);

            List<Test> co_authorTests = firstPort.getCoAuthorTests(currentId);

            EmployeeMainPageDTO currentDTO = new EmployeeMainPageDTO(
                    authorTests.stream().map(mapper::domainToDTO).toList(),
                    co_authorTests.stream().map(mapper::domainToDTO).toList()
                    );
            return ResponseEntity.status(HttpStatus.FOUND).body(currentDTO);
    }

    @GetMapping("/student-main-page/{name}/{id}")
    public ResponseEntity<StudentMainPageDTO> studentMainPage(@PathVariable("id") Integer currentStudentId,
                                                              @PathVariable("name") String currentGroup){

        StudentMainPageDTO currentDTO = facade.getStudentMainPage(currentStudentId, currentGroup);
        return ResponseEntity.status(HttpStatus.FOUND).body(currentDTO);
    }


    @GetMapping("/get/by-field/{name}")
    public ResponseEntity<List<TestDTO>> getTestsByField(@PathVariable("name") String currentName){

        List<Test> fieldTests = firstPort.getFieldTests(currentName);

        return ResponseEntity.status(HttpStatus.FOUND).body(fieldTests.stream().map(mapper::domainToDTO).toList());
    }


    @GetMapping("/get/by-author/{id}")
    public ResponseEntity<List<TestDTO>> getTestsByAuthor(@PathVariable("id") Integer currentId){

        List<Test> authorTests = firstPort.getAuthorTests(currentId);

        return ResponseEntity.status(HttpStatus.FOUND).body(authorTests.stream().map(mapper::domainToDTO).toList());
    }


    @GetMapping("/get/by-co-author/{id}")
    public ResponseEntity<List<TestDTO>> getTestsByCoAuthor(@PathVariable("id") Integer currentId){

        List<Test> authorTests = firstPort.getCoAuthorTests(currentId);

        return ResponseEntity.status(HttpStatus.FOUND).body(authorTests.stream().map(mapper::domainToDTO).toList());
    }


    @GetMapping("/get/by-name/{name}")
    public ResponseEntity<TestDTO> getCurrentTest(@PathVariable("name") String currentTestName){

        Test currentTest = firstPort.getTestByName(currentTestName);

        return ResponseEntity.status(HttpStatus.FOUND).body(mapper.domainToDTO(currentTest));
    }


    @GetMapping("/get/test-main-page/{name}")
    public ResponseEntity<TestMainPageDTO> getTestMainPage(@PathVariable("name") String currentTestName){

        TestMainPageDTO currentTestMainPage = facade1.getTestMainPage(currentTestName);

        return ResponseEntity.status(HttpStatus.FOUND).body(currentTestMainPage);
    }

    @GetMapping("/get/test-version/{id}")
    public ResponseEntity<TestVersionFullDTO> getTestVersion(@PathVariable("id") Integer currentVersionId){

        TestVersionFullDTO currentDTO = facade1.getCurrentVersion(currentVersionId);

        return ResponseEntity.status(HttpStatus.FOUND).body(currentDTO);
    }


}
