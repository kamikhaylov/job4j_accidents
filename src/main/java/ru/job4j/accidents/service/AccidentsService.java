package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.data.DataAccidentRepository;

import java.util.List;

/**
 * Сервисный слой для работы со списками инцидентов
 */
@ThreadSafe
@Service
@AllArgsConstructor
public class AccidentsService {

    private final DataAccidentRepository accidentRepository;

    /**
     * Получить список всех инцидентов.
     *
     * @return список инцидентов
     */
    public List<Accident> getAll() {
        return accidentRepository.findAll();
    }
}

