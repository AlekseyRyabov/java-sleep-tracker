package ru.yandex.practicum.sleeptracker.function;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.TestUtils;
import ru.yandex.practicum.sleeptracker.model.SleepingSession;

class SessionsMinDurationAnalyzerTest {

    private final SessionsMinDurationAnalyzer analyzer = new SessionsMinDurationAnalyzer();

    @Test
    void apply() {
        List<SleepingSession> sessions = List.of(
            TestUtils.buildSession(
                LocalDateTime.of(LocalDate.now(), LocalTime.of(11, 0)),
                LocalDateTime.of(LocalDate.now(), LocalTime.of(13, 15))
            ),
            TestUtils.buildSession(
                LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 0)),
                LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(0, 15))
            ),
            TestUtils.buildSession(
                LocalDateTime.of(LocalDate.of(2026, 10, 31), LocalTime.of(23, 0)),
                LocalDateTime.of(LocalDate.of(2026, 11, 1), LocalTime.of(2, 15))
            )
        );

        Assertions.assertEquals(
            75L,
            TestUtils.getResult(
                analyzer.apply(sessions)
                    .orElseThrow(() -> new RuntimeException("Ошибка в методе функции"))
            )
        );
    }

    @Test
    void applyEmpty() {
        Assertions.assertTrue(analyzer.apply(Collections.emptyList()).isEmpty());
    }

}