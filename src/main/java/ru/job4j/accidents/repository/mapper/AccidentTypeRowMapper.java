package ru.job4j.accidents.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.model.AccidentTypeModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccidentTypeRowMapper implements RowMapper<AccidentType> {

    @Override
    public AccidentType mapRow(ResultSet rs, int rowNum) throws SQLException {
        AccidentType type = new AccidentType();
        type.setId(rs.getInt(AccidentTypeModel.ID.name()));
        type.setName(rs.getString(AccidentTypeModel.NAME.name()));
        return type;
    }
}
