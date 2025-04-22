package ge.edu.sangu.logger;

import ge.edu.sangu.logger.appender.Appender;

import java.util.List;
import java.util.StringJoiner;

/**
 * Generic logger class, which may be used as a parent class for future loggers.
 *
 * @author Vakho10
 */
public class Logger {

    private final String name;
    private final List<Appender> appenders;

    public Logger(String name) {
        this(name, null);
    }

    public Logger(String name, List<Appender> appenders) {
        this.name = name;
        this.appenders = appenders;
    }

    public String getName() {
        return name;
    }

    public List<Appender> getAppenders() {
        return appenders;
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
            List<Appender> a = getAppenders();
            a.forEach(appender -> appender.print(name, level, message));
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
