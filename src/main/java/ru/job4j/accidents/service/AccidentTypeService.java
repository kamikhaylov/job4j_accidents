package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.data.DataAccidentTypeRepository;

import java.util.List;
import java.util.Optional;

/**
 * Сервисный слой для работы c типами инцидентов
 */
@ThreadSafe
@Service
@AllArgsConstructor
public class AccidentTypeService {

    private final DataAccidentTypeRepository accidentTypeRepository;

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
     * @param id идентификатор типа инцидента
     * @return тип инцидента
     */
    public Optional<AccidentType> getById(int id) {
        return accidentTypeRepository.findById(id);
    }
}
