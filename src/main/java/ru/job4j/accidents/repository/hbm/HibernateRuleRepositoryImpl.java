package ru.job4j.accidents.repository.hbm;

import net.jcip.annotations.ThreadSafe;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.api.RuleRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * Реализация hibernate методов для работы с БД статей инцидентов
 */
@ThreadSafe
@Repository
public class HibernateRuleRepositoryImpl implements RuleRepository {

    private final CrudRepository crudRepository;

    public HibernateRuleRepositoryImpl(
            @Qualifier("crudRepository") CrudRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    @Override
    public Rule create(Rule rule) {
        crudRepository.run(session -> session.persist(rule));
        return rule;
    }

    @Override
    public boolean update(Rule rule) {
        crudRepository.run(session -> session.merge(rule));
        return true;
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.booleanQuery(
                "delete Rule where id = :fId",
                Map.of("fId", id)
        );
    }

    @Override
    public Optional<Rule> findById(int id) {
        return crudRepository.optional(
                "from Rule where id = :fId", Rule.class,
                Map.of("fId", id)
        );
    }

    @Override
    public List<Rule> findAll() {
        return crudRepository.query(
                "from Rule order by id asc",
                Rule.class);
    }

    @Override
    public Set<Rule> findByIdList(Set<Integer> ids) {
        return new HashSet<>(crudRepository.query("from Rule where id in :ids",
                Rule.class, Map.of("ids", ids)));
    }
}
