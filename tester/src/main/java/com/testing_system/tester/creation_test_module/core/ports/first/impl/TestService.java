package com.testing_system.tester.creation_test_module.core.ports.first.impl;

import com.testing_system.tester.creation_test_module.core.domain.Test;
import com.testing_system.tester.creation_test_module.core.ports.first.TestUseCase;
import com.testing_system.tester.creation_test_module.core.ports.first.exceptions.NoTestException;
import com.testing_system.tester.creation_test_module.core.ports.second.TestDrivenUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


// Класс-оркестратор для работы с метаданными тестов
public class TestService implements TestUseCase {


    private final TestDrivenUseCase secondPort;
    private static final Logger logger = LoggerFactory.getLogger(TestService.class);

    public TestService(TestDrivenUseCase secondPort) {
        this.secondPort = secondPort;
    }

    @Override
    public void makeTest(Test currentTest) {

        if (secondPort.getTest(currentTest.getTestName()).isPresent())
            throw new IllegalArgumentException("Error! This test is exist! Test name: "
                    + currentTest.getTestName());

        secondPort.saveTest(currentTest);
    }

    @Override
    public void deleteTest(String currentTestName) {

        if (secondPort.getTest(currentTestName).isEmpty())
            throw new IllegalArgumentException("Error! You can't delete non-existent test! Test name: "
                    + currentTestName);

        secondPort.deleteTest(currentTestName);
    }

    @Override
    public Test getTestByName(String currentTestName) {
        return secondPort.getTest(currentTestName).orElseThrow(
                () -> new NoTestException("Test not fond! Test name: " + currentTestName));
    }

    // При пустом списке контроллер отправит соответсвующее ДТО
    @Override
    public List<Test> getAllTests() {
        return secondPort.getAllTests();
    }

    // Если список пуст, контроллер отправит соответсвующий ДТО
    @Override
    public List<Test> getAuthorTests(Integer currentAuthorId) {


        var allTests = getAllTests();


        return allTests.stream().filter(
                test -> test.getAuthorId().equals(currentAuthorId)).toList();
    }

    @Override
    public List<Test> getCoAuthorTests(Integer currentCoAuthorId) {

        var allTests = getAllTests();

        return allTests.stream()
                .filter(test -> test.getCo_authorsIds().stream()
                        .anyMatch(id -> id.equals(currentCoAuthorId))).toList();
    }

    /*
    Кидает NoTestException()
     */

    @Override
    public void makeCoAuthor(String currentTestName, Integer currentCoAuthorId)  {

        var currentTest = getTestByName(currentTestName);

        if (currentTest.getCo_authorsIds().contains(currentCoAuthorId)) throw new IllegalArgumentException(
                "Error! You can't assign employee twice! Employee id: " + currentCoAuthorId + ", Test id: " + currentTestName);

        currentTest.addCoAuthor(currentCoAuthorId);
        secondPort.saveTest(currentTest);
        logger.info("{}is coauthor for test:{}", currentCoAuthorId, currentTestName);

    }


    /*
   Кидает NoTestException()
    */
    @Override
    public void deleteCoAuthor(String currentTestName, Integer currentCoAuthorId) {

        var currentTest = getTestByName(currentTestName);

        if (!currentTest.getCo_authorsIds().contains(currentCoAuthorId)) throw new IllegalArgumentException(
                "Error! You can't delete employee twice! Employee id: " + currentCoAuthorId + ", Test id: " + currentTestName);

        currentTest.removeCoAuthor(currentCoAuthorId);
        secondPort.saveTest(currentTest);
        logger.info("{}isn't coauthor for test:{}", currentCoAuthorId, currentTestName);
    }

    /*
  Кидает NoTestException()
   */
    @Override
    public void changeQuestionBatch(String currentTestName, Integer newBatchSize) {

        var currentTest = getTestByName(currentTestName);

        currentTest.setQuestionBatch(newBatchSize);
        secondPort.saveTest(currentTest);
        logger.info("New batch size for test{} is {}", currentTest, newBatchSize);// Понять,чем отличается данный формат
    }
}
