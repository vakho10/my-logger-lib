package ge.edu.sangu.logger;

import java.util.StringJoiner;

/**
 * Generic logger class, which may be used as a parent class for future loggers.
 *
 * @author Vakho10
 */
public class Logger {

    private final String name;

    public Logger(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * Generic print method that takes logging level along with message.
     *
     * @param level   message level
     * @param message log message
     * @throws IllegalArgumentException If level is null, or message is null or blank
     */
    public void print(Level level, String message) {
        if (level == null) {
            throw new IllegalArgumentException("Logging level must not be null");
        }
        if (message == null || message.isBlank()) {
            throw new IllegalArgumentException("Log message must not be null or blank");
        }
        // Check if our level is the same or "more important" than default level
        if (Configuration.getLoggingLevel().ordinal() <= level.ordinal()) {
            Configuration.getAppenders()
                    .forEach(appender -> appender.print(name, level, message));
        }
    }

    public void trace(String message) {
        print(Level.TRACE, message);
    }

    public void debug(String message) {
        print(Level.DEBUG, message);
    }

    public void info(String message) {
        print(Level.INFO, message);
    }

    public void warn(String message) {
        print(Level.WARN, message);
    }

    public void error(String message, StackTraceElement[] stackTraceElements) {
        StringJoiner stringJoiner = new StringJoiner("\n");
        if (stackTraceElements != null) {
            for (StackTraceElement stackTraceElement : stackTraceElements) {
                stringJoiner.add(stackTraceElement.toString());
            }
        }
        print(Level.ERROR, message.concat("\n").concat(stringJoiner.toString()));
    }

    public void error(String message) {
        error(message, null);
    }

    public void fatal(String message) {
        print(Level.FATAL, message);
    }
}
