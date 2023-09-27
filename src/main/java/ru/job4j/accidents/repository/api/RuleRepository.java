package ru.job4j.accidents.repository.api;

import ru.job4j.accidents.model.Rule;

import java.util.Set;

/**
 * Методы для работы с БД статей инцидентов
 */
public interface RuleRepository extends BaseRepository<Rule> {

    /**
     * Получить список статей инцидентов по списку идентификаторов статей.
     *
     * @return список статей инцидента
     */
    Set<Rule> findByIdList(Set<Integer> ids);
}
