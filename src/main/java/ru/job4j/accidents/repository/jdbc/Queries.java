package ru.job4j.accidents.repository.jdbc;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Queries {

    CREATE_ACCIDENT("createAccident"),
    UPDATE_ACCIDENT("updateAccident"),
    DELETE_ACCIDENT("deleteAccident"),
    FIND_ACCIDENT_BY_ID("findAccidentById"),
    FIND_ACCIDENT_ALL("findAccidentAll"),
    CREATE_ACCIDENT_RULES("createAccidentRules"),
    DELETE_ACCIDENT_RULES("deleteAccidentRules"),
    CREATE_ACCIDENT_TYPE("createAccidentType"),
    UPDATE_ACCIDENT_TYPE("updateAccidentType"),
    DELETE_ACCIDENT_TYPE("deleteAccidentType"),
    FIND_ACCIDENT_TYPE_BY_ID("findAccidentTypeById"),
    FIND_ACCIDENT_TYPE_ALL("findAccidentTypeAll"),
    CREATE_RULE("createRule"),
    UPDATE_RULE("updateRule"),
    DELETE_RULE("deleteRule"),
    FIND_RULE_BY_ID("findRuleById"),
    FIND_RULE_ALL("findRuleAll");

    private final String sqlKey;
}
