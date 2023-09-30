package ru.job4j.accidents.repository.mapper;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.model.AccidentModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;

public class AccidentResultSetExtractor implements ResultSetExtractor<List<Accident>> {

    @Override
    public List<Accident> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<Integer, Accident> accidents = new HashMap<>();

        while (rs.next()) {
            Accident accident = new Accident();
            accident.setId(rs.getInt(AccidentModel.ID.name()));
            accident.setName(rs.getString(AccidentModel.NAME.name()));
            accident.setText(rs.getString(AccidentModel.TEXT.name()));
            accident.setAddress(rs.getString(AccidentModel.ADDRESS.name()));
            accident.setAddress(rs.getString(AccidentModel.ADDRESS.name()));
            accident.setType(new AccidentType(
                    rs.getInt(AccidentModel.TYPE_ID.name()),
                    rs.getString(AccidentModel.TYPE_NAME.name())));
            accidents.putIfAbsent(accident.getId(), accident);
            if (isNull(accident.getRules())) {
                accident.setRules(new HashSet<>());
            }
            accidents.get(accident.getId()).getRules().add(new Rule(
                    rs.getInt(AccidentModel.RULE_ID.name()),
                    rs.getString(AccidentModel.RULE_NAME.name())));
        }

        return new ArrayList<>(accidents.values());
    }
}
