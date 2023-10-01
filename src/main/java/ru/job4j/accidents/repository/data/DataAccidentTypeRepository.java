package ru.job4j.accidents.repository.data;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;

import java.util.List;

/**
 * Реализация data методов для работы с БД типов инцидентов
 */
@Primary
@Repository
public interface DataAccidentTypeRepository extends CrudRepository<AccidentType, Integer> {

    @Query(Queries.FIND_ACCIDENT_TYPE_ALL)
    List<AccidentType> findAll();
}
