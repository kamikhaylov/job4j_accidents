package ru.job4j.accidents.repository.data;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;

import java.util.List;
import java.util.Set;

/**
 * Реализация data методов для работы с БД статей инцидентов
 */
@Primary
@Repository
public interface DataRuleRepository extends CrudRepository<Rule, Integer> {

    @Query(Queries.FIND_RULE_ALL)
    List<Rule> findAll();

    @Query(Queries.FIND_RULES_BY_IDS)
    Set<Rule> findByIdList(@Param("ids") Set<Integer> ids);
}
