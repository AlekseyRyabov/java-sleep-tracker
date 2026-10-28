package ru.yandex.practicum.sleeptracker.function;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.TestUtils;
import ru.yandex.practicum.sleeptracker.enums.SleepQuality;
import ru.yandex.practicum.sleeptracker.model.SleepingSession;

class SessionsBadQualityCountAnalyzerTest {

    private final SessionsBadQualityCountAnalyzer analyzer = new SessionsBadQualityCountAnalyzer();

    @Test
    void apply() {
        List<SleepingSession> sessions = List.of(
            TestUtils.buildSession(SleepQuality.BAD),
            TestUtils.buildSession(SleepQuality.BAD),
            TestUtils.buildSession(SleepQuality.NORMAL),
            TestUtils.buildSession(SleepQuality.GOOD),
            TestUtils.buildSession(SleepQuality.GOOD),
            TestUtils.buildSession(SleepQuality.GOOD)
        );

        Assertions.assertEquals(
            2L,
            TestUtils.getResult(
                analyzer.apply(sessions)
                    .orElseThrow(() -> new RuntimeException("Ошибка в методе функции"))
            )
        );
    }

    @Test
    void applyEmpty() {
        Assertions.assertEquals(
            0L,
            TestUtils.getResult(
                analyzer.apply(Collections.emptyList())
                    .orElseThrow(() -> new RuntimeException("Ошибка в методе функции"))
            )
        );
    }
}