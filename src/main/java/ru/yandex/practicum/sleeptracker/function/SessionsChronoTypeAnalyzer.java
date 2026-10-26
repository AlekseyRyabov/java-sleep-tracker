package ru.yandex.practicum.sleeptracker.function;

import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import ru.yandex.practicum.sleeptracker.enums.ChronoType;
import ru.yandex.practicum.sleeptracker.model.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.model.SleepingSession;

/**
 * Анализ хронотип пользователя.
 */
public class SessionsChronoTypeAnalyzer implements Function<List<SleepingSession>, Optional<SleepAnalysisResult>> {

    private static final String RESULT_MESSAGE_FORMAT = "Хронотип пользователя - %s.%n";

    @Override
    public Optional<SleepAnalysisResult> apply(List<SleepingSession> sleepingSessions) {
        Map<ChronoType, Long> chronoTypeLongMap = sleepingSessions.stream()
            .filter(
                sleepingSession ->
                    !sleepingSession.getStartSession().toLocalDate().equals(sleepingSession.getEndSession().toLocalDate())
                        || !sleepingSession.getStartSession().toLocalTime().isAfter(LocalTime.of(6, 0))
            )
            .collect(Collectors.groupingBy(
                ChronoType::getChronoTypeBySession, Collectors.counting()
            ));
        long max = chronoTypeLongMap.values().stream()
            .max(Comparator.naturalOrder())
            .orElse(0L);
        List<ChronoType> chronoTypes = chronoTypeLongMap.entrySet().stream()
            .filter(chronoTypeLongEntry -> chronoTypeLongEntry.getValue().equals(max))
            .map(Entry::getKey)
            .toList();
        ChronoType chronoType = chronoTypes.size() == 1 ? chronoTypes.getFirst() : ChronoType.PIGEON;
        return Optional.of(new SleepAnalysisResult(RESULT_MESSAGE_FORMAT, chronoType.getDescription(), String.class));
    }
}
