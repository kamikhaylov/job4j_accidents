package ru.job4j.accidents.repository.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AccidentModel {

    ID("id"),
    NAME("name"),
    TEXT("text"),
    ADDRESS("address"),
    TYPE_ID("typeId"),
    TYPE_NAME("typeName"),
    RULE_ID("ruleId"),
    RULE_NAME("ruleName");

    private final String parameterName;
}
