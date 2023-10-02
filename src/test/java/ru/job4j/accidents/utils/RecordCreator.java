package ru.job4j.accidents.utils;

import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;

import java.util.Set;

public final class RecordCreator {

    private RecordCreator() {
    }

    public static Accident createAccident(AccidentType type, Set<Rule> rules) {
        Accident accident = new Accident();
        accident.setId(1);
        accident.setName("accident");
        accident.setText("text");
        accident.setAddress("address");
        accident.setType(type);
        accident.setRules(rules);
        return accident;
    }

    public static AccidentType createType() {
        AccidentType type = new AccidentType();
        type.setId(1);
        type.setName("type");
        return type;
    }

    public static Set<Rule> createRules() {
        Rule rule = new Rule();
        rule.setId(1);
        rule.setName("rule");
        return Set.of(rule);
    }
}
