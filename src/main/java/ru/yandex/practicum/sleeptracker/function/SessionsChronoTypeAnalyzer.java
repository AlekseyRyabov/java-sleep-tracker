package ru.yandex.practicum.sleeptracker.function;

import java.time.LocalDateTime;
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
import ru.yandex.practicum.sleeptracker.utils.Constants;

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
                        || !sleepingSession.getStartSession().toLocalTime().isAfter(Constants.MORNING_SIX)
            )
            .collect(Collectors.groupingBy(
                this::getChronoTypeBySession, Collectors.counting()
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

    /**
     * «Сова» — если время засыпания было после 23:00, а время пробуждения — после 9:00.
     * «Жаворонок» — если время засыпания было до 22:00, а время пробуждения до — 7:00.
     * «Голубь» — во всех остальных случаях.
     * Бессонные ночи и дневные сессии сна в подсчёте должны игнорироваться.
     * @param session сессия сна
     * @return хронотип
     */
    public ChronoType getChronoTypeBySession(SleepingSession session) {
        LocalDateTime start = session.getStartSession();
        LocalDateTime end = session.getEndSession();
        boolean isDayChanged = !start.toLocalDate().equals(end.toLocalDate());
        if (start.isAfter(LocalDateTime.of(start.toLocalDate(), ChronoType.OWL.getStartTime()))) {
            if (isDayChanged && end.isAfter(LocalDateTime.of(end.toLocalDate(), ChronoType.OWL.getEndTime()))) {
                return ChronoType.OWL;
            }
        } else if (start.isAfter(LocalDateTime.of(start.toLocalDate().minusDays(1), ChronoType.OWL.getStartTime()))) {
            if (!isDayChanged && end.isAfter(LocalDateTime.of(end.toLocalDate(), ChronoType.OWL.getEndTime()))) {
                return ChronoType.OWL;
            }
        }
        if (start.isBefore(LocalDateTime.of(start.toLocalDate(), ChronoType.LARK.getStartTime()))
            && end.isBefore(LocalDateTime.of(end.toLocalDate(), ChronoType.LARK.getEndTime()))) {
            return ChronoType.LARK;
        }
        return ChronoType.PIGEON;
    }
}
