package com.testing_system.tester.creation_test_module.core.ports.first.impl;

import com.testing_system.tester.creation_test_module.core.domain.Test;
import com.testing_system.tester.creation_test_module.core.ports.first.FieldUseCase;
import com.testing_system.tester.creation_test_module.core.ports.first.TestCommandUseCase;
import com.testing_system.tester.creation_test_module.core.ports.first.TestQueryUseCase;
import com.testing_system.tester.creation_test_module.core.ports.second.TestDrivenUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/*
Сервис-оркестратор для работы с метаданными тестов
 */
@Service
public class TestCommandService implements TestCommandUseCase {

    // Реализация использует перввичные и вторичные порты
    private final TestQueryUseCase firstPort;
    private final FieldUseCase fieldFirstPort;
    private final TestDrivenUseCase secondPort;
    private static final Logger logger = LoggerFactory.getLogger(TestCommandService.class);



    public TestCommandService(TestQueryUseCase firstPort1, FieldUseCase firstPort, TestDrivenUseCase secondPort) {
        this.firstPort = firstPort1;
        this.fieldFirstPort = firstPort;
        this.secondPort = secondPort;
    }


    @Override
    public void makeTest(Test currentTest) {

        if (firstPort.findTest(currentTest.getTestName()))
            throw new IllegalArgumentException("Error! This test is exist! Test name: "
                    + currentTest.getTestName());

        if (!fieldFirstPort.findField(currentTest.getField()))
            throw new IllegalArgumentException("Error! You can't save test with non-existent field %f!"
                    .formatted(currentTest.getField()));

        secondPort.saveTest(currentTest);
    }


    @Override
    public void deleteTest(String currentTestName) {

        if (!firstPort.findTest(currentTestName))
            throw new IllegalArgumentException("Error! You can't delete non-existent test! Test name: "
                    + currentTestName);

        secondPort.deleteTest(currentTestName);
    }



    //Кидает NoTestException()
    @Override
    public void makeCoAuthor(String currentTestName, Integer currentCoAuthorId)  {

        var currentTest = firstPort.getTestByName(currentTestName);

        if (currentTest.getCo_authorsIds().contains(currentCoAuthorId)) throw new IllegalArgumentException(
                "Error! You can't assign employee twice! Employee id: %e. Test id: %t.".formatted( currentCoAuthorId , currentTestName));

        currentTest.addCoAuthor(currentCoAuthorId);
        secondPort.saveTest(currentTest);
        logger.info("{}is coauthor for test:{}", currentCoAuthorId, currentTestName);

    }


    //Кидает NoTestException()
    @Override
    public void deleteCoAuthor(String currentTestName, Integer currentCoAuthorId) {

        var currentTest = firstPort.getTestByName(currentTestName);

        if (!currentTest.getCo_authorsIds().contains(currentCoAuthorId)) throw new IllegalArgumentException(
                "Error! You can't delete employee twice! Employee id: %e. Test id: %t."
                        .formatted( currentCoAuthorId , currentTestName));

        currentTest.removeCoAuthor(currentCoAuthorId);
        secondPort.saveTest(currentTest);
        logger.info("{}isn't coauthor for test:{}", currentCoAuthorId, currentTestName);
    }

    //Кидает NoTestException()
    @Override
    public void changeQuestionBatch(String currentTestName, Integer newBatchSize) {

        var currentTest = firstPort.getTestByName(currentTestName);

        // Можно добавить enum с максимальным и минимальным размером батча
        if (newBatchSize < 5 || newBatchSize > 20)
            throw new IllegalArgumentException("Error! Incorrect question batch! ");

        currentTest.setQuestionBatch(newBatchSize);
        secondPort.saveTest(currentTest);
        logger.info("New batch size for test{} is {}", currentTest, newBatchSize);// Понять,чем отличается данный формат
    }
}
