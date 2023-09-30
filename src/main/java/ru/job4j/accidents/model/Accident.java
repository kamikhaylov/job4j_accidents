package ru.job4j.accidents.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Set;

/**
 * Модель инцидента
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
@Table(name = "accidents")
public class Accident {

    /** Идентификатор инцидента */
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /** Наименование инцидента */
    private String name;

    /** Описание инцидента */
    private String text;

    /** Адрес инцидента */
    private String address;

    /** Тип инцидента */
    @ManyToOne
    @JoinColumn(name = "type_id")
    private AccidentType type;

    /** Статьи инцидента */
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "accident_rules",
            joinColumns = {@JoinColumn(name = "accident_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "rule_id", nullable = false, updatable = false)}
    )
    private Set<Rule> rules;
}