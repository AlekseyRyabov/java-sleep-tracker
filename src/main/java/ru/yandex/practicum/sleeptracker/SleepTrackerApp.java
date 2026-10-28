package ru.yandex.practicum.sleeptracker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import ru.yandex.practicum.sleeptracker.function.SessionsAvgDurationAnalyzer;
import ru.yandex.practicum.sleeptracker.function.SessionsBadQualityCountAnalyzer;
import ru.yandex.practicum.sleeptracker.function.SessionsChronoTypeAnalyzer;
import ru.yandex.practicum.sleeptracker.function.SessionsCountAnalyzer;
import ru.yandex.practicum.sleeptracker.function.SessionsMaxDurationAnalyzer;
import ru.yandex.practicum.sleeptracker.function.SessionsMinDurationAnalyzer;
import ru.yandex.practicum.sleeptracker.function.SessionsSleeplessNightsCountAnalyzer;
import ru.yandex.practicum.sleeptracker.model.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.model.SleepingSession;

public class SleepTrackerApp {

    private static final List<Function<List<SleepingSession>, Optional<SleepAnalysisResult>>> ANALYZERS =
        new ArrayList<>();

    static {
        ANALYZERS.add(new SessionsCountAnalyzer());
        ANALYZERS.add(new SessionsMinDurationAnalyzer());
        ANALYZERS.add(new SessionsMaxDurationAnalyzer());
        ANALYZERS.add(new SessionsAvgDurationAnalyzer());
        ANALYZERS.add(new SessionsBadQualityCountAnalyzer());
        ANALYZERS.add(new SessionsSleeplessNightsCountAnalyzer());
        ANALYZERS.add(new SessionsChronoTypeAnalyzer());
    }

    private static final List<SleepingSession> sessions = new ArrayList<>();
    private static final String PERIOD_MESSAGE_FORMAT = "За период с %s по %s:%n";

    public static void main(String[] args) {
        String fileName = args[0];
        try (
            BufferedReader br = new BufferedReader(
                new InputStreamReader(
                    Objects.requireNonNull(SleepTrackerApp.class.getClassLoader().getResourceAsStream(fileName)),
                    StandardCharsets.UTF_8)
            )
        ) {
            String session;
            while (Objects.nonNull(session = br.readLine())) {
                sessions.add(new SleepingSession(session));
            }
        } catch (IOException ignored) {

        }
        if (sessions.isEmpty()) {
            return;
        }
        String start = sessions.getFirst().getStartSessionForPrint();
        String end = sessions.getLast().getEndSessionForPrint();
        System.out.printf(PERIOD_MESSAGE_FORMAT, start, end);
        ANALYZERS.stream()
            .map(function -> function.apply(sessions))
            .filter(Optional::isPresent)
            .forEach(sleepAnalysisResult -> sleepAnalysisResult.get().printResult());
    }
}