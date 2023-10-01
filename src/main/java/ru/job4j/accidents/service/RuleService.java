package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.data.DataRuleRepository;

import java.util.List;
import java.util.Set;

/**
 * Сервисный слой для работы cо статьями инцидентов
 */
@ThreadSafe
@Service
@AllArgsConstructor
public class RuleService {

    private final DataRuleRepository ruleRepository;

    /**
     * Получить список всех статей инцидентов.
     *
     * @return список статей инцидента
     */
    public List<Rule> getAll() {
        return ruleRepository.findAll();
    }

    /**
     * Получить список статей инцидентов по списку идентификаторов статей.
     *
     * @param ids список идентификаторов статей
     * @return список статей инцидента
     */
    public Set<Rule> findByIdList(Set<Integer> ids) {
        return ruleRepository.findByIdList(ids);
    }
}
