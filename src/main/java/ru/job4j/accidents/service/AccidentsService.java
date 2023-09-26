package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.api.AccidentRepository;

import java.util.List;

/**
 * Сервисный слой для работы со списками автонарушений
 */
@ThreadSafe
@Service
@AllArgsConstructor
public class AccidentsService {

    private final AccidentRepository accidentRepository;

    /**
     * Получить список всех автонарушений.
     *
     * @return список автонарушений
     */
    public List<Accident> getAll() {
        return accidentRepository.findAll();
    }
}
