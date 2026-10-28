package ru.yandex.practicum.sleeptracker.function;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

    @Test
    void getChronoTypeBySessionTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
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
        Method getChronoTypeBySession =
            SessionsChronoTypeAnalyzer.class.getDeclaredMethod("getChronoTypeBySession", SleepingSession.class);
        getChronoTypeBySession.setAccessible(true);

        Assertions.assertSame(ChronoType.OWL, getChronoTypeBySession.invoke(analyzer, owl));
        Assertions.assertSame(ChronoType.LARK, getChronoTypeBySession.invoke(analyzer, lark));
        Assertions.assertSame(ChronoType.PIGEON, getChronoTypeBySession.invoke(analyzer, pigeon));
        Assertions.assertSame(ChronoType.PIGEON, getChronoTypeBySession.invoke(analyzer, pigeon2));
    }

}