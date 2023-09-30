package ru.job4j.accidents.repository.hbm;

import net.jcip.annotations.ThreadSafe;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.api.AccidentTypeRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Реализация hibernate методов для работы с БД типов инцидентов
 */
@ThreadSafe
@Repository
public class HibernateAccidentTypeRepositoryImpl implements AccidentTypeRepository {

    private final CrudRepository crudRepository;

    public HibernateAccidentTypeRepositoryImpl(
            @Qualifier("crudRepository") CrudRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    @Override
    public AccidentType create(AccidentType type) {
        crudRepository.run(session -> session.persist(type));
        return type;
    }

    @Override
    public boolean update(AccidentType type) {
        crudRepository.run(session -> session.merge(type));
        return true;
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.booleanQuery(
                "delete AccidentType where id = :fId",
                Map.of("fId", id)
        );
    }

    @Override
    public Optional<AccidentType> findById(int id) {
        return crudRepository.optional(
                "from AccidentType where id = :fId", AccidentType.class,
                Map.of("fId", id)
        );
    }

    @Override
    public List<AccidentType> findAll() {
        return crudRepository.query(
                "from AccidentType order by id asc",
                AccidentType.class);
    }
}
