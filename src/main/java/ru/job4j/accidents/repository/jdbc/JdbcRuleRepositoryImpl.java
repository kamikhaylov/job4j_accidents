package ru.job4j.accidents.repository.jdbc;

import net.jcip.annotations.ThreadSafe;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.api.RuleRepository;
import ru.job4j.accidents.repository.model.AccidentModel;
import ru.job4j.accidents.repository.model.AccidentTypeModel;

import java.sql.Types;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;

import static java.util.Objects.nonNull;

/**
 * Реализация jdbc методов для работы с БД статей инцидентов
 */
@ThreadSafe
@Repository
public class JdbcRuleRepositoryImpl implements RuleRepository {

    private final Properties properties;
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final RowMapper<Rule> ruleRowMapper;

    public JdbcRuleRepositoryImpl(
            @Qualifier("ruleSqlQueries") Properties properties,
            @Qualifier("accidentJdbcTemplate") NamedParameterJdbcTemplate jdbcTemplate,
            @Qualifier("ruleRowMapper") RowMapper<Rule> ruleRowMapper) {
        this.properties = properties;
        this.jdbcTemplate = jdbcTemplate;
        this.ruleRowMapper = ruleRowMapper;
    }

    @Override
    public Rule create(Rule rule) {
        jdbcTemplate.update(
                properties.getProperty(Queries.CREATE_RULE.getSqlKey()),
                createParameterSource(rule));
        return rule;
    }

    @Override
    public boolean update(Rule rule) {
        MapSqlParameterSource parameterSource = createParameterSource(rule)
                .addValue(AccidentTypeModel.ID.getParameterName(), rule.getId(), Types.INTEGER);

        int updatedCount = jdbcTemplate.update(
                properties.getProperty(Queries.UPDATE_RULE.getSqlKey()),
                parameterSource);

        return updatedCount == 1;
    }

    @Override
    public boolean delete(int id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue(AccidentTypeModel.ID.getParameterName(), id, Types.INTEGER);

        int deletedCount = jdbcTemplate.update(
                properties.getProperty(Queries.DELETE_RULE.getSqlKey()),
                parameterSource);

        return deletedCount == 1;
    }

    @Override
    public Optional<Rule> findById(int id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue(AccidentModel.ID.getParameterName(), id, Types.INTEGER);

        Rule rule = DataAccessUtils.singleResult(
                jdbcTemplate.query(
                        properties.getProperty(Queries.FIND_RULE_BY_ID.getSqlKey()),
                        parameterSource,
                        ruleRowMapper));

        return nonNull(rule) ? Optional.of(rule) : Optional.empty();
    }

    @Override
    public List<Rule> findAll() {
        return jdbcTemplate.query(
                properties.getProperty(Queries.FIND_RULE_ALL.getSqlKey()),
                ruleRowMapper);
    }

    @Override
    public Set<Rule> findByIdList(Set<Integer> ids) {
        Set<Rule> ruleSet = new HashSet<>();
        for (Integer id : ids) {
            ruleSet.add(findById(id).get());
        }
        return ruleSet;
    }

    private MapSqlParameterSource createParameterSource(Rule rule) {
        return new MapSqlParameterSource()
                .addValue(AccidentTypeModel.NAME.getParameterName(), rule.getName(), Types.VARCHAR);
    }
}
