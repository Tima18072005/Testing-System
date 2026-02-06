package com.testing_system.tester.creation_test_module.core.domain;

import java.util.List;
import java.util.Map;

// Значения оценок в 100-бальной и 5-бальной системах
public enum MarkValues {

    PASSED(50, 3),
    GOOD(70, 4),
    EXCELLENT(90, 5);

    public final Integer mark100;
    public final Integer mark5;

    private MarkValues(Integer mark100, Integer mark5){

        this.mark100 = mark100;
        this.mark5 = mark5;
    }

  public static Map<Integer, Integer> getAllValues(){

        return Map.of(PASSED.mark100, PASSED.mark5,
                GOOD.mark100, GOOD.mark5,
                EXCELLENT.mark100, EXCELLENT.mark5
                );
  }
}
