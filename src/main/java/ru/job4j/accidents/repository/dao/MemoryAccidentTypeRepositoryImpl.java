package ru.job4j.accidents.repository.dao;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.api.AccidentTypeRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.Objects.nonNull;

/**
 * Реализация методов для работы с БД типов инцидентов в памяти
 */
@ThreadSafe
@Repository
public class MemoryAccidentTypeRepositoryImpl implements AccidentTypeRepository {

    private final AtomicInteger id = new AtomicInteger();
    private final Map<Integer, AccidentType> types = new ConcurrentHashMap<>();

    public MemoryAccidentTypeRepositoryImpl() {
        create(new AccidentType(1, "Автомобили"));
        create(new AccidentType(2, "ПДД"));
        create(new AccidentType(3, "Инфраструктура"));
    }

    @Override
    public AccidentType create(AccidentType type) {
        type.setId(id.incrementAndGet());
        types.put(type.getId(), type);
        return type;
    }

    @Override
    public boolean update(AccidentType type) {
        return nonNull(types.computeIfPresent(
                type.getId(),
                (id, oldAType) -> new AccidentType(id, type.getName())));
    }

    @Override
    public boolean delete(int id) {
        return nonNull(types.remove(id));
    }

    @Override
    public Optional<AccidentType> findById(int id) {
        return Optional.ofNullable(types.get(id));
    }

    @Override
    public List<AccidentType> findAll() {
        return types.values().stream().toList();
    }
}
