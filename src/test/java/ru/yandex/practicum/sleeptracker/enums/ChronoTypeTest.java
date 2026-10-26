package ru.yandex.practicum.sleeptracker.enums;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.TestUtils;
import ru.yandex.practicum.sleeptracker.model.SleepingSession;

class ChronoTypeTest {

    @Test
    void getChronoTypeBySession() {
        SleepingSession owl = TestUtils.buildSession(
            LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 1)),
            LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(9, 1))
        );
        SleepingSession lark = TestUtils.buildSession(
            LocalDateTime.of(LocalDate.now(), LocalTime.of(21, 59)),
            LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(6, 59))
        );
        SleepingSession pigeon = TestUtils.buildSession(
            LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 0)),
            LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(9, 0))
        );
        SleepingSession pigeon2 = TestUtils.buildSession(
            LocalDateTime.of(LocalDate.now(), LocalTime.of(22, 0)),
            LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(7, 0))
        );

        Assertions.assertSame(ChronoType.OWL, ChronoType.getChronoTypeBySession(owl));
        Assertions.assertSame(ChronoType.LARK, ChronoType.getChronoTypeBySession(lark));
        Assertions.assertSame(ChronoType.PIGEON, ChronoType.getChronoTypeBySession(pigeon));
        Assertions.assertSame(ChronoType.PIGEON, ChronoType.getChronoTypeBySession(pigeon2));
    }

}