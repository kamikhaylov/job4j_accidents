package ru.job4j.accidents.repository.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AccidentTypeModel {

    ID("id"),
    NAME("name");

    private final String parameterName;
}
