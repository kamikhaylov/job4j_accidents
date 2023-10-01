package ru.job4j.accidents.repository.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SecurityQueries {

    SELECT_USERS_BY_USERNAME("selectUsersByUsername"),
    SELECT_AUTHORITIES_BY_USERNAME("selectAuthoritiesByUsername");

    private final String sqlKey;
}
