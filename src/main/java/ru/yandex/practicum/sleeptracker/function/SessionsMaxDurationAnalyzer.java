package ru.yandex.practicum.sleeptracker.function;

import java.time.Duration;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import ru.yandex.practicum.sleeptracker.model.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.model.SleepingSession;

/**
 * Анализ максимальной продолжительности сессии.
 */
public class SessionsMaxDurationAnalyzer implements Function<List<SleepingSession>, Optional<SleepAnalysisResult>> {

    private static final String RESULT_MESSAGE_FORMAT = "Максимальная продолжительность сессии - %d минут.%n";

    @Override
    public Optional<SleepAnalysisResult> apply(List<SleepingSession> sleepingSessions) {
        return sleepingSessions.stream()
            .map(sleepingSession -> Duration.between(
                    sleepingSession.getStartSession(), sleepingSession.getEndSession()))
            .max(Comparator.comparingLong(Duration::getSeconds))
            .map(duration -> new SleepAnalysisResult(RESULT_MESSAGE_FORMAT, duration.toMinutes(), Long.class));
    }
}
