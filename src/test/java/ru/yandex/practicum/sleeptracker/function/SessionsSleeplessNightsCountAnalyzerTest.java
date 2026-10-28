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

class SessionsSleeplessNightsCountAnalyzerTest {

    private final SessionsSleeplessNightsCountAnalyzer analyzer = new SessionsSleeplessNightsCountAnalyzer();

    @Test
    void apply() {
        List<SleepingSession> sessions = List.of(
            TestUtils.buildSession(
                LocalDateTime.of(LocalDate.of(2026, 10, 1), LocalTime.of(11, 0)),
                LocalDateTime.of(LocalDate.of(2026, 10, 1), LocalTime.of(13, 15))
            ),
            TestUtils.buildSession(
                LocalDateTime.of(LocalDate.of(2026, 10, 15), LocalTime.of(23, 0)),
                LocalDateTime.of(LocalDate.of(2026, 10, 16), LocalTime.of(1, 15))
            ),
            TestUtils.buildSession(
                LocalDateTime.of(LocalDate.of(2026, 10, 31), LocalTime.of(23, 0)),
                LocalDateTime.of(LocalDate.of(2026, 11, 1), LocalTime.of(1, 15))
            )
        );

        Assertions.assertEquals(
            31L,
            TestUtils.getResult(
                analyzer.apply(sessions)
                    .orElseThrow(() -> new RuntimeException("Ошибка в методе функции"))
            )
        );
    }

    @Test
    void apply2() {
        List<SleepingSession> sessions = List.of(
            TestUtils.buildSession(
                LocalDateTime.of(LocalDate.of(2026, 10, 1), LocalTime.of(12, 0)),
                LocalDateTime.of(LocalDate.of(2026, 10, 1), LocalTime.of(13, 15))
            ),
            TestUtils.buildSession(
                LocalDateTime.of(LocalDate.of(2026, 10, 15), LocalTime.of(23, 0)),
                LocalDateTime.of(LocalDate.of(2026, 10, 16), LocalTime.of(1, 15))
            ),
            TestUtils.buildSession(
                LocalDateTime.of(LocalDate.of(2026, 10, 31), LocalTime.of(23, 0)),
                LocalDateTime.of(LocalDate.of(2026, 11, 1), LocalTime.of(1, 15))
            )
        );

        Assertions.assertEquals(
            30L,
            TestUtils.getResult(
                analyzer.apply(sessions)
                    .orElseThrow(() -> new RuntimeException("Ошибка в методе функции"))
            )
        );
    }

    @Test
    void apply3() {
        List<SleepingSession> sessions = List.of(
            TestUtils.buildSession(
                LocalDateTime.of(LocalDate.of(2026, 10, 1), LocalTime.of(2, 0)),
                LocalDateTime.of(LocalDate.of(2026, 10, 1), LocalTime.of(5, 15))
            ),
            TestUtils.buildSession(
                LocalDateTime.of(LocalDate.of(2026, 10, 1), LocalTime.of(23, 0)),
                LocalDateTime.of(LocalDate.of(2026, 10, 2), LocalTime.of(1, 15))
            ),
            TestUtils.buildSession(
                LocalDateTime.of(LocalDate.of(2026, 10, 2), LocalTime.of(11, 0)),
                LocalDateTime.of(LocalDate.of(2026, 10, 2), LocalTime.of(15, 15))
            )
        );

        Assertions.assertEquals(
            0L,
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