package ru.job4j.accidents.repository.jdbc;

import net.jcip.annotations.ThreadSafe;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.api.AccidentRepository;
import ru.job4j.accidents.repository.model.AccidentModel;

import java.sql.Types;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import static java.util.Objects.nonNull;

/**
 * Реализация jdbc методов для работы с БД инцидентов
 * @ThreadSafe
 * @Repository
 */
public class JdbcAccidentRepositoryImpl implements AccidentRepository {

    private final Properties properties;
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final ResultSetExtractor<List<Accident>> accidentResultSetExtractor;

    public JdbcAccidentRepositoryImpl(
            @Qualifier("accidentSqlQueries") Properties properties,
            @Qualifier("accidentJdbcTemplate") NamedParameterJdbcTemplate jdbcTemplate,
            @Qualifier("accidentResultSetExtractor") ResultSetExtractor<List<Accident>> accidentResultSetExtractor) {
        this.properties = properties;
        this.jdbcTemplate = jdbcTemplate;
        this.accidentResultSetExtractor = accidentResultSetExtractor;
    }

    @Override
    public Accident create(Accident accident) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                properties.getProperty(Queries.CREATE_ACCIDENT.getSqlKey()),
                createParameterSource(accident),
                keyHolder,
                new String[] {"id"});
        int id = keyHolder.getKey().intValue();
        accident.setId(id);
        createAccidentRules(accident);
        return accident;
    }

    @Override
    public boolean update(Accident accident) {
        MapSqlParameterSource parameterSource = createParameterSource(accident)
                .addValue(AccidentModel.ID.getParameterName(), accident.getId(), Types.INTEGER);

        deleteAccidentRules(accident);
        int updatedCount = jdbcTemplate.update(
                properties.getProperty(Queries.UPDATE_ACCIDENT.getSqlKey()),
                parameterSource);
        createAccidentRules(accident);

        return updatedCount == 1;
    }

    @Override
    public boolean delete(int id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue(AccidentModel.ID.getParameterName(), id, Types.INTEGER);

        deleteAccidentRules(findById(id).get());
        int deletedCount = jdbcTemplate.update(
                properties.getProperty(Queries.DELETE_ACCIDENT.getSqlKey()),
                parameterSource);

        return deletedCount == 1;
    }

    @Override
    public Optional<Accident> findById(int id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue(AccidentModel.ID.getParameterName(), id, Types.INTEGER);

        Accident accident = DataAccessUtils.singleResult(
                jdbcTemplate.query(
                        properties.getProperty(Queries.FIND_ACCIDENT_BY_ID.getSqlKey()),
                        parameterSource,
                        accidentResultSetExtractor));

        return nonNull(accident) ? Optional.of(accident) : Optional.empty();
    }

    @Override
    public List<Accident> findAll() {
        return jdbcTemplate.query(
                properties.getProperty(Queries.FIND_ACCIDENT_ALL.getSqlKey()),
                accidentResultSetExtractor);
    }

    private MapSqlParameterSource createParameterSource(Accident accident) {
        return new MapSqlParameterSource()
                .addValue(AccidentModel.NAME.getParameterName(), accident.getName(), Types.VARCHAR)
                .addValue(AccidentModel.TEXT.getParameterName(), accident.getText(), Types.VARCHAR)
                .addValue(AccidentModel.ADDRESS.getParameterName(), accident.getAddress(), Types.VARCHAR)
                .addValue(AccidentModel.TYPE_ID.getParameterName(), accident.getType().getId(), Types.INTEGER);
    }

    private void createAccidentRules(Accident accident) {
        for (Rule rule : accident.getRules()) {
            MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                    .addValue("accidentId", accident.getId(), Types.INTEGER)
                    .addValue("ruleId", rule.getId(), Types.INTEGER);
            jdbcTemplate.update(
                    properties.getProperty(Queries.CREATE_ACCIDENT_RULES.getSqlKey()),
                    parameterSource);
        }
    }

    private void deleteAccidentRules(Accident accident) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("accidentId", accident.getId(), Types.INTEGER);
        jdbcTemplate.update(
                properties.getProperty(Queries.DELETE_ACCIDENT_RULES.getSqlKey()),
                parameterSource);
    }
}
