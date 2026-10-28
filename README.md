# 🌙 Java Sleep Tracker

Приложение для глубокого анализа сессий сна, мониторинга его качества и автоматического определения хронотипа пользователя. Инструмент помогает преобразовывать сырые данные о времени засыпания и пробуждения в понятную и полезную статистику.

---

## 🚀 Текущий функционал

Приложение агрегирует список сессий сна и рассчитывает ключевые метрики здоровья:
* **Средняя продолжительность:** вычисление точного среднего времени сна пользователя.
* **Качество сна:** подсчет количества сессий с низкими показателями качества.
* **Хронотип пользователя:** автоматическое определение паттерна (например, *Голубь*, *Жаворонок*, *Сова*) на основе времени начала и окончания сессий.
* **Статистика сессий:** общее количество зафиксированных периодов отдыха.
* **Рекорды длительности:** определение максимальной и минимальной продолжительности сна.
* **Мониторинг бессонницы:** точный подсчет количества полностью бессонных ночей.

---

## 🛠 Расширение функционала (Инструкция)

Чтобы добавить новую аналитическую функцию, выполните два простых шага:

### Шаг 1: Создайте функцию-анализатор
Реализуйте интерфейс `Function<List<SleepingSession>, Optional<SleepAnalysisResult>>`.

### Шаг 2: Зарегистрируйте её в приложении
Добавьте экземпляр созданной функции в список `ANALYZERS` в главном классе `SleepTrackerApp`:

```java
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
        //Добавьте экземпляр созданной функции здесь
    }
    
}
```
