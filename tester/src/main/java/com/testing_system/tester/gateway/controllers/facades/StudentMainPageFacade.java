package com.testing_system.tester.gateway.controllers.facades;

import com.testing_system.tester.control_module.core.ports.first.GroupQueryUseCase;
import com.testing_system.tester.creation_test_module.infrastructure.dto.response.StudentMainPageDTO;
import com.testing_system.tester.testing_attempts_module.core.ports.first.AttemptHistoryUseCase;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/*
Фасад для вызова главной страницы студента
 */
@Service
public class StudentMainPageFacade {

    private final GroupQueryUseCase firstPort;
    private final AttemptHistoryUseCase firstPort1;

    public StudentMainPageFacade(GroupQueryUseCase firstPort, AttemptHistoryUseCase firstPort1) {
        this.firstPort = firstPort;
        this.firstPort1 = firstPort1;
    }

    public StudentMainPageDTO getStudentMainPage(Integer currentStudentId, String currentGroupName ){

        List<String> groupTests = firstPort.getTestsAssign(currentGroupName);

        Map.Entry<List<String>, List<String>> entryTests = firstPort1.testsFilter(currentStudentId, groupTests);

        return new StudentMainPageDTO(entryTests.getKey(), entryTests.getValue());
    }
}
