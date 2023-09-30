package ru.job4j.accidents.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.model.AccidentTypeModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RuleRowMapper implements RowMapper<Rule> {

    @Override
    public Rule mapRow(ResultSet rs, int rowNum) throws SQLException {
        Rule rule = new Rule();
        rule.setId(rs.getInt(AccidentTypeModel.ID.name()));
        rule.setName(rs.getString(AccidentTypeModel.NAME.name()));
        return rule;
    }
}
