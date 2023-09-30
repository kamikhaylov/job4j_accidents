package ru.job4j.accidents.configuration;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class SqlQueriesConfiguration {

    @Bean
    public PropertiesFactoryBean accidentSqlQueries() {
        PropertiesFactoryBean props = new PropertiesFactoryBean();
        props.setLocation(new ClassPathResource("sql/accidentSqlQueries.xml"));
        return props;
    }

    @Bean
    public PropertiesFactoryBean accidentTypeSqlQueries() {
        PropertiesFactoryBean props = new PropertiesFactoryBean();
        props.setLocation(new ClassPathResource("sql/accidentTypeSqlQueries.xml"));
        return props;
    }

    @Bean
    public PropertiesFactoryBean ruleSqlQueries() {
        PropertiesFactoryBean props = new PropertiesFactoryBean();
        props.setLocation(new ClassPathResource("sql/ruleSqlQueries.xml"));
        return props;
    }
}
