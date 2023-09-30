package ru.job4j.accidents.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

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

    /** Наименование инцидента */
    private String name;

    /** Описание инцидента */
    private String text;

    /** Адрес инцидента */
    private String address;

    /** Тип инцидента */
    private AccidentType type;

    /** Статьи инцидента */
    private Set<Rule> rules;
}