package ru.job4j.accidents.configuration;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.mapper.AccidentResultSetExtractor;
import ru.job4j.accidents.repository.mapper.AccidentTypeRowMapper;
import ru.job4j.accidents.repository.mapper.RuleRowMapper;

import javax.sql.DataSource;
import java.util.List;

/**
 * @Configuration
 * @PropertySource("classpath:db.properties")
 * @EnableTransactionManagement
 */
public class JdbcConfiguration {

    /** @Bean */
    public DataSource dataSource(
            @Value("${jdbc.driver}") String driver,
            @Value("${jdbc.url}") String url,
            @Value("${jdbc.username}") String username,
            @Value("${jdbc.password}") String password) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    /** @Bean */
    public NamedParameterJdbcTemplate accidentJdbcTemplate(
            @Qualifier("dataSource") DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    /** @Bean */
    public ResultSetExtractor<List<Accident>> accidentResultSetExtractor() {
        return new AccidentResultSetExtractor();
    }

    /** @Bean */
    public RowMapper<AccidentType> accidentTypeRowMapper() {
        return new AccidentTypeRowMapper();
    }

    /** @Bean */
    public RowMapper<Rule> ruleRowMapper() {
        return new RuleRowMapper();
    }
}
