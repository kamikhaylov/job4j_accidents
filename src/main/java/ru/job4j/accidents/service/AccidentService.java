package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.api.AccidentRepository;

import java.util.Optional;

/**
 * Сервисный слой для работы с одним инцидентом
 */
@ThreadSafe
@Service
@AllArgsConstructor
public class AccidentService {

    private final AccidentRepository accidentRepository;
    private final AccidentTypeService accidentTypeService;

    /**
     * Создать инцидент.
     *
     * @param accident инцидент
     * @param typeId идентификатор типа инцидента
     */
    public void create(Accident accident, int typeId) {
        accident.setType(accidentTypeService.getById(typeId).get());
        accidentRepository.create(accident);
    }

    /**
     * Обновить инцидент.
     *
     * @param accident инцидент
     * @param typeId идентификатор типа инцидента
     */
    public void update(Accident accident, int typeId) {
        accident.setType(accidentTypeService.getById(typeId).get());
        accidentRepository.update(accident);
    }

    /**
     * Получить инцидент по id.
     *
     * @return инцидент
     */
    public Optional<Accident> getById(int id) {
        return accidentRepository.findById(id);
    }
}
