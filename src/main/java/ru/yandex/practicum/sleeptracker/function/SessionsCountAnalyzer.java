package ru.yandex.practicum.sleeptracker.function;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import ru.yandex.practicum.sleeptracker.model.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.model.SleepingSession;

/**
 * Анализ количества сессий сна.
 */
public class SessionsCountAnalyzer implements Function<List<SleepingSession>, Optional<SleepAnalysisResult>> {

    private static final String RESULT_MESSAGE_FORMAT = "Количество сессий сна - %d.%n";

    @Override
    public Optional<SleepAnalysisResult> apply(List<SleepingSession> sleepingSessions) {
        int count = sleepingSessions.size();
        return Optional.of(new SleepAnalysisResult(RESULT_MESSAGE_FORMAT, count, Integer.class));
    }
}
