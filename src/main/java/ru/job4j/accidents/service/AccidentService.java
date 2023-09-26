package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.api.AccidentRepository;

/**
 * Сервисный слой для работы с одним инцидентом
 */
@ThreadSafe
@Service
@AllArgsConstructor
public class AccidentService {

    private final AccidentRepository accidentRepository;

    /**
     * Создать инцидент.
     *
     * @param accident инцидент
     */
    public void create(Accident accident) {
        accidentRepository.create(accident);
    }

    /**
     * Обновить инцидент.
     *
     * @param accident инцидент
     */
    public void update(Accident accident) {
        accidentRepository.update(accident);
    }

    /**
     * Получить инцидент по id.
     *
     * @return инцидент
     */
    public Accident getById(int id) {
        return accidentRepository.findById(id).get();
    }
}
