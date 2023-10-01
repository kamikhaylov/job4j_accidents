package ru.job4j.accidents.repository.data;

import org.springframework.context.annotation.Primary;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Authority;

/**
 * Методы для работы с БД ролей пользователей
 */
@Primary
@Repository
public interface AuthorityRepository extends CrudRepository<Authority, Integer> {

    Authority findByAuthority(String authority);
}
