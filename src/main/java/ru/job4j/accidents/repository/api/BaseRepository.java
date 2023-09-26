package ru.job4j.accidents.repository.api;

import java.util.List;
import java.util.Optional;

/**
 * Базовые методы для работы с БД
 */
public interface BaseRepository<T> {

    /**
     * Сохранить запись.
     *
     * @param record запись
     * @return запись
     */
    T create(T record);

    /**
     * Обновить запись.
     *
     * @param record запись
     * @return результат обновления, true - запись обновлена, false - не обновлена
     */
    boolean update(T record);

    /**
     * Удалить запись по идентификатору записи.
     *
     * @param id идентификатор записи
     * @return результат удаления, true - запись удалена, false - не удалена
     */
    boolean delete(int id);

    /**
     * Поиск записи по идентификатору записи.
     *
     * @param id идентификатор записи
     * @return запись типа T
     */
    Optional<T> findById(int id);

    /**
     * Поиск всех записей.
     *
     * @return список записей типа T
     */
    List<T> findAll();
}