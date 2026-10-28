package ru.yandex.practicum.sleeptracker.function;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import ru.yandex.practicum.sleeptracker.model.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.model.SleepingSession;

/**
 * Анализ средней продолжительности сессии.
 */
public class SessionsAvgDurationAnalyzer implements Function<List<SleepingSession>, Optional<SleepAnalysisResult>> {

    private static final String RESULT_MESSAGE_FORMAT = "Средняя продолжительность сессии - %f минут.%n";

    @Override
    public Optional<SleepAnalysisResult> apply(List<SleepingSession> sleepingSessions) {
        return sleepingSessions.stream()
            .mapToLong(sleepingSession -> Duration.between(
                    sleepingSession.getStartSession(), sleepingSession.getEndSession()).toMinutes())
            .average().stream()
            .boxed()
            .map(duration -> new SleepAnalysisResult(RESULT_MESSAGE_FORMAT, duration, Double.class))
            .findFirst();
    }
}
