package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.api.AccidentRepository;

import java.util.Optional;
import java.util.Set;

/**
 * Сервисный слой для работы с одним инцидентом
 */
@ThreadSafe
@Service
@AllArgsConstructor
public class AccidentService {

    private final AccidentRepository accidentRepository;
    private final AccidentTypeService accidentTypeService;
    private final RuleService ruleService;

    /**
     * Создать инцидент.
     *
     * @param accident инцидент
     * @param typeId   идентификатор типа инцидента
     * @param ruleIds  список идентификаторов статей инцидента
     */
    public void create(Accident accident, int typeId, Set<Integer> ruleIds) {
        accident.setType(accidentTypeService.getById(typeId).get());
        accident.setRules(ruleService.findByIdList(ruleIds));
        accidentRepository.create(accident);
    }

    /**
     * Обновить инцидент.
     *
     * @param accident инцидент
     * @param typeId   идентификатор типа инцидента
     * @param ruleIds  список идентификаторов статей инцидента
     * @return результат обновления, true - запись обновлена, false - не обновлена
     */
    public boolean update(Accident accident, int typeId, Set<Integer> ruleIds) {
        Optional<AccidentType> types = accidentTypeService.getById(typeId);
        if (types.isEmpty()) {
            return false;
        }
        accident.setType(types.get());
        accident.setRules(ruleService.findByIdList(ruleIds));
        return accidentRepository.update(accident);
    }

    /**
     * Получить инцидент по id.
     *
     * @param id идентификатор инцидента
     * @return инцидент
     */
    public Optional<Accident> getById(int id) {
        return accidentRepository.findById(id);
    }
}
