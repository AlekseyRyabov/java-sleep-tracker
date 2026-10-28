package ru.yandex.practicum.sleeptracker.utils;

import java.time.LocalTime;

public class Constants {

    private Constants() {

    }

    public static final LocalTime MORNING_SIX = LocalTime.of(6, 0);
    public static final LocalTime MIDDAY = LocalTime.of(12, 0);
    public static final LocalTime OWL_START_TIME = LocalTime.of(23, 0);
    public static final LocalTime OWL_END_TIME = LocalTime.of(9, 0);
    public static final LocalTime LARK_START_TIME = LocalTime.of(22, 0);
    public static final LocalTime LARK_END_TIME = LocalTime.of(7, 0);
    public static final long DAY = 1;

}
