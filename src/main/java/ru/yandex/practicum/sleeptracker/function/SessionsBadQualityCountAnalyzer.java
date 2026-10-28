package ru.yandex.practicum.sleeptracker.function;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import ru.yandex.practicum.sleeptracker.enums.SleepQuality;
import ru.yandex.practicum.sleeptracker.model.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.model.SleepingSession;

/**
 * Анализ количества сессий с плохим качеством сна.
 */
public class SessionsBadQualityCountAnalyzer implements Function<List<SleepingSession>, Optional<SleepAnalysisResult>> {

    private static final String RESULT_MESSAGE_FORMAT = "Количество сессий с плохим качеством сна - %d.%n";

    @Override
    public Optional<SleepAnalysisResult> apply(List<SleepingSession> sleepingSessions) {
        long count =  sleepingSessions.stream()
            .filter(sleepingSession -> SleepQuality.BAD == sleepingSession.getSleepQuality())
            .count();
        return Optional.of(new SleepAnalysisResult(RESULT_MESSAGE_FORMAT, count, Long.class));
    }
}
