package ru.yandex.practicum.sleeptracker.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import ru.yandex.practicum.sleeptracker.enums.SleepQuality;

/**
 * Сессия сна.
 */
public class SleepingSession {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");
    private static final String SESSION_DATA_SPLITTER = ";";

    /**
     * Время засыпания.
     */
    private final LocalDateTime startSession;

    /**
     * Время пробуждения.
     */
    private final LocalDateTime endSession;

    /**
     * Качество сна.
     */
    private final SleepQuality sleepQuality;

    public SleepingSession(LocalDateTime startSession, LocalDateTime endSession, SleepQuality sleepQuality) {
        this.startSession = startSession;
        this.endSession = endSession;
        this.sleepQuality = sleepQuality;
    }

    public SleepingSession(String session) {
        String[] parts = session.split(SESSION_DATA_SPLITTER);
        this.startSession = LocalDateTime.parse(parts[0], DATE_TIME_FORMATTER);
        this.endSession = LocalDateTime.parse(parts[1], DATE_TIME_FORMATTER);
        this.sleepQuality = SleepQuality.valueOf(parts[2]);
    }


    public LocalDateTime getStartSession() {
        return startSession;
    }

    public String getStartSessionForPrint() {
        return startSession.format(DATE_TIME_FORMATTER);
    }

    public LocalDateTime getEndSession() {
        return endSession;
    }

    public String getEndSessionForPrint() {
        return endSession.format(DATE_TIME_FORMATTER);
    }

    public SleepQuality getSleepQuality() {
        return sleepQuality;
    }
}
