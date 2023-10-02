package ru.job4j.accidents.repository.jdbc;

import net.jcip.annotations.ThreadSafe;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.api.AccidentTypeRepository;
import ru.job4j.accidents.repository.model.AccidentModel;
import ru.job4j.accidents.repository.model.AccidentTypeModel;

import java.sql.Types;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import static java.util.Objects.nonNull;

/**
 * Реализация jdbc методов для работы с БД типов инцидентов
 * @ThreadSafe
 * @Repository
 */
public class JdbcAccidentTypeRepositoryImpl implements AccidentTypeRepository {

    private final Properties properties;
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final RowMapper<AccidentType> accidentTypeRowMapper;

    public JdbcAccidentTypeRepositoryImpl(
            @Qualifier("accidentTypeSqlQueries") Properties properties,
            @Qualifier("accidentJdbcTemplate") NamedParameterJdbcTemplate jdbcTemplate,
            @Qualifier("accidentTypeRowMapper") RowMapper<AccidentType> accidentTypeRowMapper) {
        this.properties = properties;
        this.jdbcTemplate = jdbcTemplate;
        this.accidentTypeRowMapper = accidentTypeRowMapper;
    }

    @Override
    public AccidentType create(AccidentType type) {
        jdbcTemplate.update(
                properties.getProperty(Queries.CREATE_ACCIDENT_TYPE.getSqlKey()),
                createParameterSource(type));
        return type;
    }

    @Override
    public boolean update(AccidentType type) {
        MapSqlParameterSource parameterSource = createParameterSource(type)
                .addValue(AccidentTypeModel.ID.getParameterName(), type.getId(), Types.INTEGER);

        int updatedCount = jdbcTemplate.update(
                properties.getProperty(Queries.UPDATE_ACCIDENT_TYPE.getSqlKey()),
                parameterSource);

        return updatedCount == 1;
    }

    @Override
    public boolean delete(int id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue(AccidentTypeModel.ID.getParameterName(), id, Types.INTEGER);

        int deletedCount = jdbcTemplate.update(
                properties.getProperty(Queries.DELETE_ACCIDENT_TYPE.getSqlKey()),
                parameterSource);

        return deletedCount == 1;
    }

    @Override
    public Optional<AccidentType> findById(int id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue(AccidentModel.ID.getParameterName(), id, Types.INTEGER);

        AccidentType type = DataAccessUtils.singleResult(
                jdbcTemplate.query(
                        properties.getProperty(Queries.FIND_ACCIDENT_TYPE_BY_ID.getSqlKey()),
                        parameterSource,
                        accidentTypeRowMapper));

        return nonNull(type) ? Optional.of(type) : Optional.empty();
    }

    @Override
    public List<AccidentType> findAll() {
        return jdbcTemplate.query(
                properties.getProperty(Queries.FIND_ACCIDENT_TYPE_ALL.getSqlKey()),
                accidentTypeRowMapper);
    }

    private MapSqlParameterSource createParameterSource(AccidentType type) {
        return new MapSqlParameterSource()
                .addValue(AccidentTypeModel.NAME.getParameterName(), type.getName(), Types.VARCHAR);
    }
}
