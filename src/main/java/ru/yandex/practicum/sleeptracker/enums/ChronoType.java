package ru.yandex.practicum.sleeptracker.enums;

import java.time.LocalDateTime;
import java.time.LocalTime;
import ru.yandex.practicum.sleeptracker.model.SleepingSession;

/**
 * Хронотип.
 */
public enum ChronoType {

    OWL("Сова", LocalTime.of(23, 0), LocalTime.of(9, 0)),
    LARK("Жаворонок", LocalTime.of(22, 0), LocalTime.of(7, 0)),
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

    /**
     * «Сова» — если время засыпания было после 23:00, а время пробуждения — после 9:00.
     * «Жаворонок» — если время засыпания было до 22:00, а время пробуждения до — 7:00.
     * «Голубь» — во всех остальных случаях.
     * Бессонные ночи и дневные сессии сна в подсчёте должны игнорироваться.
     * @param session сессия сна
     * @return хронотип
     */
    public static ChronoType getChronoTypeBySession(SleepingSession session) {
        LocalDateTime start = session.getStartSession();
        LocalDateTime end = session.getEndSession();
        boolean isDayChanged = !start.toLocalDate().equals(end.toLocalDate());
        if (start.isAfter(LocalDateTime.of(start.toLocalDate(), OWL.startTime))) {
            if (isDayChanged && end.isAfter(LocalDateTime.of(end.toLocalDate(), OWL.endTime))) {
                return OWL;
            }
        } else if (start.isAfter(LocalDateTime.of(start.toLocalDate().minusDays(1), OWL.startTime))) {
            if (!isDayChanged && end.isAfter(LocalDateTime.of(end.toLocalDate(), OWL.endTime))) {
                return OWL;
            }
        }
        if (start.isBefore(LocalDateTime.of(start.toLocalDate(), LARK.startTime))
            && end.isBefore(LocalDateTime.of(end.toLocalDate(), LARK.endTime))) {
                return LARK;
            }
        return PIGEON;
    }

}
