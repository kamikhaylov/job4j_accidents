package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.api.AccidentTypeRepository;

import java.util.List;
import java.util.Optional;

/**
 * Сервисный слой для работы c типами инцидентов
 */
@ThreadSafe
@Service
@AllArgsConstructor
public class AccidentTypeService {

    private final AccidentTypeRepository accidentTypeRepository;

    /**
     * Получить список всех типов инцидентов.
     *
     * @return список типов инцидентов
     */
    public List<AccidentType> getAll() {
        return accidentTypeRepository.findAll();
    }

    /**
     * Получить тип инцидента по id.
     *
     * @return тип инцидента
     */
    public Optional<AccidentType> getById(int id) {
        return accidentTypeRepository.findById(id);
    }
}
