package ru.job4j.accidents.configuration;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;
import java.util.Properties;

import static ru.job4j.accidents.repository.data.SecurityQueries.SELECT_AUTHORITIES_BY_USERNAME;
import static ru.job4j.accidents.repository.data.SecurityQueries.SELECT_USERS_BY_USERNAME;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;
    private final Properties securityProperties;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery(securityProperties.getProperty(
                        SELECT_USERS_BY_USERNAME.getSqlKey()))
                .authoritiesByUsernameQuery(securityProperties.getProperty(
                        SELECT_AUTHORITIES_BY_USERNAME.getSqlKey()));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/user/login", "/user/registration")
                .permitAll()
                .antMatchers("/**")
                .hasAnyRole("ADMIN", "USER")
                .and()
                .formLogin()
                .loginPage("/user/login")
                .defaultSuccessUrl("/accidents/index")
                .failureUrl("/user/login?error=true")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/user/login?logout=true")
                .invalidateHttpSession(true)
                .permitAll()
                .and()
                .csrf()
                .disable();
    }
}
