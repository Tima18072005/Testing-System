package com.testing_system.tester.creation_test_module.core.domain;

// Доменная модель таблицы оценивания в 100-бальной системе
// Создание при запуске программы
public class MarkTable {

    private Integer passed;

    private Integer good;

    private Integer excellent;

    public MarkTable(){

        this.passed = 50;

        this.good = 70;

        this.excellent = 86;
    }


    // Геттеры

    public Integer getPassed() {
        return passed;
    }

    public Integer getGood() {
        return good;
    }

    public Integer getExcellent() {
        return excellent;
    }

    // Сеттеры

    public void setPassed(Integer passed) {
        this.passed = passed;
    }

    public void setGood(Integer good) {
        this.good = good;
    }

    public void setExcellent(Integer excellent) {
        this.excellent = excellent;
    }
}
