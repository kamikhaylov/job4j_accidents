package ru.job4j.accidents.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Модель инцидента
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Accident {

    /** Идентификатор инцидента */
    @EqualsAndHashCode.Include
    private int id;

    /** Наименование */
    private String name;

    /** Описание */
    private String text;

    /** Адрес */
    private String address;

    /** Тип инцидента */
    private AccidentType type;
}