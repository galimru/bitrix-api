package ru.zipal.bitrix.api.model;

public enum BitrixOperation {
    ADD("add"),
    DELETE("delete"),
    GET("get"),
    LIST("list"),
    UPDATE("update");

    private String name;

    BitrixOperation(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
