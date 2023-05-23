package at.fhtw.utils;

public class DateHandler {
    public static String formatSecondsToHHMMSS(long seconds) {
        java.time.Duration duration = java.time.Duration.ofSeconds(seconds);
        long hours = duration.toHoursPart();
        long minutes = duration.toMinutesPart();
        long sec = duration.toSecondsPart();

        return String.format("%02d:%02d:%02d", hours, minutes, sec);
    }
}
