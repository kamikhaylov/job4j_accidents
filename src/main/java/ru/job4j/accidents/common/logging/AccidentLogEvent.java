package ru.job4j.accidents.common.logging;

/**
 * Информация о логируемом событие.
 */
public enum AccidentLogEvent implements LogEvent {

    ACC0001("Не найден инцидент"),
    ACC0002("Редактирование завершено с ошибкой"),
    ACC0003("Некорректный логин или пароль"),
    ACC0004("Успешный выход из системы"),
    ACC0005("Введите другое имя пользователя. Пользователь существует: "),
    ACC0006("Не удалось добавить пользователя");

    private final String title;

    AccidentLogEvent(String title) {
        this.title = title;
    }

    @Override
    public String getCode() {
        return name();
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return getCode() + ". " + getTitle();
    }
}
