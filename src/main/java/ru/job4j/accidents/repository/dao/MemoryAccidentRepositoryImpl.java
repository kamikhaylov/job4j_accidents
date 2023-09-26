package ru.job4j.accidents.repository.dao;

import net.jcip.annotations.ThreadSafe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.api.AccidentRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static ru.job4j.accidents.common.logging.AccidentLogEvent.ACC1000;
import static ru.job4j.accidents.common.logging.AccidentLogEvent.ACC1001;

/**
 * Реализация методов для работы с БД автонарушений в памяти
 */
@ThreadSafe
@Repository
public class MemoryAccidentRepositoryImpl implements AccidentRepository {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(MemoryAccidentRepositoryImpl.class.getName());

    private final AtomicInteger id = new AtomicInteger();
    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();

    public MemoryAccidentRepositoryImpl() {
        create(new Accident(0, "ДТП",
                "Столкновение двух автомобилей", "Самара, ул. Куйбышева 10"));
        create(new Accident(0, "Нарушение ПДД",
                "Выезд на встречную полосу движения", "Самара, Московское ш., 15"));
        create(new Accident(0, "Происшествие на дороге",
                "Открытый люк", "Самара, ул. Ленина, 23"));
    }

    @Override
    public Accident create(Accident accident) {
        accident.setId(id.incrementAndGet());
        accidents.put(accident.getId(), accident);
        return accident;
    }

    @Override
    public boolean update(Accident accident) {
        Optional<Accident> accidentFound = findById(accident.getId());
        if (accidentFound.isEmpty()) {
            LOGGER.error(ACC1000.toString(), accident);
            return false;
        } else {
            accidents.put(accident.getId(), accident);
            return true;
        }
    }

    @Override
    public boolean delete(int id) {
        Optional<Accident> accidentFound = findById(id);
        if (accidentFound.isEmpty()) {
            LOGGER.error(ACC1001.toString(), id);
            return false;
        } else {
            accidents.remove(id);
            return true;
        }
    }

    @Override
    public Optional<Accident> findById(int id) {
        return Optional.ofNullable(accidents.get(id));
    }

    @Override
    public List<Accident> findAll() {
        return accidents.values().stream().toList();
    }
}
