package ru.yandex.practicum.sleeptracker.enums;

import java.time.LocalTime;
import ru.yandex.practicum.sleeptracker.utils.Constants;

/**
 * Хронотип.
 */
public enum ChronoType {

    OWL("Сова", Constants.OWL_START_TIME, Constants.OWL_END_TIME),
    LARK("Жаворонок", Constants.LARK_START_TIME, Constants.LARK_END_TIME),
    PIGEON("Голубь", null, null);

    private final String description;
    private final LocalTime startTime;
    private final LocalTime endTime;

    ChronoType(String description, LocalTime startTime, LocalTime endTime) {
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getDescription() {
        return description;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

}
