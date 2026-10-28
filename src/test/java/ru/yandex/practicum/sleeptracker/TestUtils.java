package ru.yandex.practicum.sleeptracker;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;
import ru.yandex.practicum.sleeptracker.enums.SleepQuality;
import ru.yandex.practicum.sleeptracker.model.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.model.SleepingSession;

public class TestUtils {

    private TestUtils() {

    }

    public static SleepingSession buildSession(LocalDateTime start, LocalDateTime end) {
        return new SleepingSession(
            start,
            end,
            SleepQuality.values()[ThreadLocalRandom.current().nextInt(SleepQuality.values().length)]
        );
    }

    public static SleepingSession buildSession(SleepQuality quality) {
        return new SleepingSession(
            LocalDateTime.now(),
            LocalDateTime.now(),
            quality
        );
    }

    public static Object getResult(SleepAnalysisResult result) {
        return result.getResult();
    }

}
