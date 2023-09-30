package ru.job4j.accidents.repository.data;

import org.springframework.context.annotation.Primary;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

@Primary
@Repository
public interface DataAccidentRepository extends CrudRepository<Accident, Integer> {
}
