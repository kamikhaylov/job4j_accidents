package ru.job4j.accidents.common.logging;

/**
 * Информация о логируемом событие.
 */
public enum AccidentLogEvent implements LogEvent {

    ACC1000("Не найдена запись для обновления"),
    ACC1001("Не найдена запись для удаления");

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
        return getCode() + "." + getTitle();
    }
}
