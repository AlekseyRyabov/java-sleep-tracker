package ru.yandex.practicum.sleeptracker.function;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.TestUtils;
import ru.yandex.practicum.sleeptracker.enums.ChronoType;
import ru.yandex.practicum.sleeptracker.model.SleepingSession;

class SessionsChronoTypeAnalyzerTest {

    private final SessionsChronoTypeAnalyzer analyzer = new SessionsChronoTypeAnalyzer();

    @Test
    void apply() {
        List<SleepingSession> sessions = List.of(
            TestUtils.buildSession(
                LocalDateTime.of(LocalDate.now(), LocalTime.of(11, 0)),
                LocalDateTime.of(LocalDate.now(), LocalTime.of(13, 15))
            ),
            TestUtils.buildSession(
                LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 1)),
                LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(9, 1))
            ),
            TestUtils.buildSession(
                LocalDateTime.of(LocalDate.now().plusDays(2), LocalTime.of(0, 0)),
                LocalDateTime.of(LocalDate.now().plusDays(2), LocalTime.of(9, 1))
            ),
            TestUtils.buildSession(
                LocalDateTime.of(LocalDate.now().plusDays(3), LocalTime.of(0, 0)),
                LocalDateTime.of(LocalDate.now().plusDays(3), LocalTime.of(9, 0))
            ),
            TestUtils.buildSession(
                LocalDateTime.of(LocalDate.now().plusDays(4), LocalTime.of(2, 0)),
                LocalDateTime.of(LocalDate.now().plusDays(4), LocalTime.of(11, 0))
            )
        );

        Assertions.assertEquals(
            ChronoType.OWL.getDescription(),
            TestUtils.getResult(
                analyzer.apply(sessions)
                    .orElseThrow(() -> new RuntimeException("Ошибка в методе функции"))
            )
        );
    }

    @Test
    void applyEquals() {
        List<SleepingSession> sessions = List.of(
            TestUtils.buildSession(
                LocalDateTime.of(LocalDate.now(), LocalTime.of(11, 0)),
                LocalDateTime.of(LocalDate.now(), LocalTime.of(13, 15))
            ),
            TestUtils.buildSession(
                LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 1)),
                LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(9, 1))
            ),
            TestUtils.buildSession(
                LocalDateTime.of(LocalDate.now().plusDays(2), LocalTime.of(0, 0)),
                LocalDateTime.of(LocalDate.now().plusDays(2), LocalTime.of(9, 1))
            ),
            TestUtils.buildSession(
                LocalDateTime.of(LocalDate.now().plusDays(3), LocalTime.of(0, 0)),
                LocalDateTime.of(LocalDate.now().plusDays(3), LocalTime.of(9, 0))
            ),
            TestUtils.buildSession(
                LocalDateTime.of(LocalDate.now().plusDays(4), LocalTime.of(2, 0)),
                LocalDateTime.of(LocalDate.now().plusDays(4), LocalTime.of(9, 0))
            )
        );

        Assertions.assertEquals(
            ChronoType.PIGEON.getDescription(),
            TestUtils.getResult(
                analyzer.apply(sessions)
                    .orElseThrow(() -> new RuntimeException("Ошибка в методе функции"))
            )
        );
    }

    @Test
    void applyEmpty() {
        Assertions.assertEquals(
            ChronoType.PIGEON.getDescription(),
            TestUtils.getResult(
                analyzer.apply(Collections.emptyList())
                    .orElseThrow(() -> new RuntimeException("Ошибка в методе функции"))
            )
        );
    }

}