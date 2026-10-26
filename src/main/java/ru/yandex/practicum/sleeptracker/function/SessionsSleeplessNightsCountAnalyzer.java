package ru.yandex.practicum.sleeptracker.function;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import ru.yandex.practicum.sleeptracker.model.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.model.SleepingSession;

/**
 * Анализ количества бессоных ночей.
 */
public class SessionsSleeplessNightsCountAnalyzer implements Function<List<SleepingSession>, Optional<SleepAnalysisResult>> {

    private static final String RESULT_MESSAGE_FORMAT = "Количество бессоных ночей - %d.%n";

    @Override
    public Optional<SleepAnalysisResult> apply(List<SleepingSession> sleepingSessions) {
        if (sleepingSessions.isEmpty()) {
            return Optional.empty();
        }
        long countNights =  sleepingSessions.stream()
            .filter(
                sleepingSession ->
                    !sleepingSession.getStartSession().toLocalDate().equals(sleepingSession.getEndSession().toLocalDate())
                        || !sleepingSession.getStartSession().toLocalTime().isAfter(LocalTime.of(6, 0))
            )
            .count();
        long count = ChronoUnit.DAYS.between(
            sleepingSessions.getFirst().getStartSession().toLocalDate(),
            sleepingSessions.getLast().getEndSession().toLocalDate().plusDays(1)
        ) - countNights;
        LocalTime firstSessionTime = sleepingSessions.getFirst().getStartSession().toLocalTime();
        if (firstSessionTime.isBefore(LocalTime.of(12, 0 ))
            && firstSessionTime.isAfter(LocalTime.of(6, 0))) {
            count++;
        }
        return Optional.of(new SleepAnalysisResult(RESULT_MESSAGE_FORMAT, count, Long.class));
    }
}
