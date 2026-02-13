package com.testing_system.tester.creation_test_module.core.ports.first;

import com.testing_system.tester.creation_test_module.core.domain.Test;

import java.util.List;

/*
Первичный порт
Задачи:

    - Доступ к данным теста
    - Фильтрация данных при просмотре
 */
public interface TestQueryUseCase {

    public boolean findTest(String currentTestName);

    public Test getTestByName(String currentTestName) ;

    public List<Test> getAllTests();

    public List<Test> getFieldTests(String currentFieldName);

    public List<Test> getAuthorTests(Integer currentAuthorId);

    public List<Test> getCoAuthorTests(Integer currentCoAuthorId);
}
