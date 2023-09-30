package ru.job4j.accidents.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Модель статья инцидента
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Rule {

    /** Идентификатор статьи инцидента */
    @EqualsAndHashCode.Include
    private int id;

    /** Наименование статьи инцидента */
    private String name;
}
