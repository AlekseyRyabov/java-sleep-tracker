package ru.yandex.practicum.sleeptracker.model;

public record SleepAnalysisResult(String resultMessage, Object result, Class clazz) {

    public void printResult() {
        System.out.printf(resultMessage, clazz.cast(result));
    }

    public Object getResult() {
        return clazz.cast(result);
    }

}
