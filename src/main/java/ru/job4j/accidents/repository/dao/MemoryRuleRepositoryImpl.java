package ru.job4j.accidents.repository.dao;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.api.RuleRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.Objects.nonNull;

/**
 * Реализация методов для работы с БД статей инцидентов в памяти
 */
@ThreadSafe
@Repository
public class MemoryRuleRepositoryImpl implements RuleRepository {

    private final AtomicInteger id = new AtomicInteger();
    private final Map<Integer, Rule> rules = new ConcurrentHashMap<>();

    public MemoryRuleRepositoryImpl() {
        create(new Rule(1, "Статья. 1"));
        create(new Rule(2, "Статья. 2"));
        create(new Rule(3, "Статья. 3"));
    }

    @Override
    public Rule create(Rule rule) {
        rule.setId(id.incrementAndGet());
        rules.put(rule.getId(), rule);
        return rule;
    }

    @Override
    public boolean update(Rule rule) {
        return nonNull(rules.computeIfPresent(
                rule.getId(),
                (id, oldAType) -> new Rule(id, rule.getName())));
    }

    @Override
    public boolean delete(int id) {
        return nonNull(rules.remove(id));
    }

    @Override
    public Optional<Rule> findById(int id) {
        return Optional.ofNullable(rules.get(id));
    }

    @Override
    public List<Rule> findAll() {
        return rules.values().stream().toList();
    }

    @Override
    public Set<Rule> findByIdList(Set<Integer> ids) {
        Set<Rule> result = new HashSet<>();
        for (Integer integer : ids) {
            result.add(findById(integer).get());
        }
        return result;
    }
}
