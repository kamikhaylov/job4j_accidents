package ru.job4j.accidents.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.api.AccidentRepository;

import java.util.List;

/**
 * Сервисный слой для работы со списками инцидентов
 */
@ThreadSafe
@Service
public class AccidentsService {

    private final AccidentRepository accidentRepository;

    public AccidentsService(
            @Qualifier("hibernateAccidentRepositoryImpl") AccidentRepository accidentRepository) {
        this.accidentRepository = accidentRepository;
    }

    /**
     * Получить список всех инцидентов.
     *
     * @return список инцидентов
     */
    public List<Accident> getAll() {
        return accidentRepository.findAll();
    }
}
