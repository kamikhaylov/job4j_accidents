package ru.job4j.accidents.repository.hbm;

import net.jcip.annotations.ThreadSafe;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.api.AccidentRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Реализация hibernate методов для работы с БД инцидентов
 */
@ThreadSafe
@Repository
public class HibernateAccidentRepositoryImpl implements AccidentRepository {

    private final CrudRepository crudRepository;

    public HibernateAccidentRepositoryImpl(
            @Qualifier("crudRepository") CrudRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    @Override
    public Accident create(Accident accident) {
        crudRepository.run(session -> session.persist(accident));
        return accident;
    }

    @Override
    public boolean update(Accident accident) {
        crudRepository.run(session -> session.merge(accident));
        return true;
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.booleanQuery(
                "delete Accident where id = :fId",
                Map.of("fId", id)
        );
    }

    @Override
    public Optional<Accident> findById(int id) {
        return crudRepository.optional(
                "from Accident accident "
                        + "join fetch accident.rules "
                        + "where accident.id = :fId",
                Accident.class,
                Map.of("fId", id)
        );
    }

    @Override
    public List<Accident> findAll() {
        return crudRepository.query(
                "select distinct accident "
                        + "from Accident accident "
                        + "join fetch accident.rules "
                        + "order by accident_id asc",
                Accident.class);
    }
}
