package ge.edu.sangu;

public class ConsoleLogger {

    private static LoggingLevel DEFAULT_LEVEL = LoggingLevel.INFO;

    public static LoggingLevel getDefaultLevel() {
        return DEFAULT_LEVEL;
    }

    public static void setDefaultLevel(LoggingLevel defaultLevel) {
        if (defaultLevel == null) {
            throw new IllegalArgumentException("Default logging level must not be null");
        }
        DEFAULT_LEVEL = defaultLevel;
    }

    public void print(LoggingLevel level, String message) {
        if (message == null || message.isBlank()) {
            throw new IllegalArgumentException("Log message must not be null or blank");
        }
        // Check if our level is the same or "more important" than default level
        if (DEFAULT_LEVEL.ordinal() <= level.ordinal()) {
            System.out.println(message);
        }
    }

    public void debug(String message) {
        print(LoggingLevel.DEBUG, message);
    }

    public void info(String message) {
        print(LoggingLevel.INFO, message);
    }

    public void error(String message) {
        print(LoggingLevel.ERROR, message);
    }
}
