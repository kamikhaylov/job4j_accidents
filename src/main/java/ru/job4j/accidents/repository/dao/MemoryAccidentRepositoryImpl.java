package ru.job4j.accidents.repository.dao;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.api.AccidentRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.Objects.nonNull;

/**
 * Реализация методов для работы с БД инцидентов в памяти
 */
@ThreadSafe
@Repository
public class MemoryAccidentRepositoryImpl implements AccidentRepository {

    private final AtomicInteger id = new AtomicInteger();
    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();

    public MemoryAccidentRepositoryImpl() {
        create(new Accident(0, "ДТП",
                "Столкновение двух автомобилей",
                "Самара, ул. Куйбышева 10",
                new AccidentType(1, "Автомобили")));
        create(new Accident(0, "Нарушение ПДД",
                "Выезд на встречную полосу движения",
                "Самара, Московское ш., 15",
                new AccidentType(2, "ПДД")));
        create(new Accident(0, "Происшествие на дороге",
                "Открытый люк",
                "Самара, ул. Ленина, 23",
                new AccidentType(3, "Инфраструктура")));
    }

    @Override
    public Accident create(Accident accident) {
        accident.setId(id.incrementAndGet());
        accidents.put(accident.getId(), accident);
        return accident;
    }

    @Override
    public boolean update(Accident accident) {
        return nonNull(accidents.computeIfPresent(
                accident.getId(),
                (id, oldAccident) -> new Accident(
                        id,
                        accident.getName(),
                        accident.getText(),
                        accident.getAddress(),
                        accident.getType())));
    }

    @Override
    public boolean delete(int id) {
        return nonNull(accidents.remove(id));
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
