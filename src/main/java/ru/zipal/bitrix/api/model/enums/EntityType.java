package ru.zipal.bitrix.api.model.enums;

public enum EntityType {
    USER("user"),
    ACTIVITY("crm.activity"),
    CONTACT("crm.contact"),
    LEAD("crm.lead"),
    DEAL("crm.deal"),
    COMPANY("crm.company");

    private String name;

    EntityType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
