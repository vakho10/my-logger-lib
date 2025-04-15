package ge.edu.sangu.logger;

import ge.edu.sangu.logger.appender.Appender;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Generic logger class, which may be used as a parent class for future loggers.
 *
 * @author Vakho10
 */
public class Logger {

    private static List<Appender> APPENDERS = new ArrayList<>();
    private static String PATTERN_LAYOUT = "{date} [{level}]: {message}";

    public static void addAppender(Appender appender) {
        APPENDERS.add(appender);
    }

    public static void setPatternLayout(String newPatternLayout) {
        PATTERN_LAYOUT = newPatternLayout;
    }

    /**
     * Logging level (INFO by default).
     */
    private Level level = Level.INFO;

    /**
     * Constructor that takes logger's logging level with output stream.
     *
     * @param level logger's current logging level
     * @param out   output stream where messages will go
     */
    public Logger(Level level) {
        this.level = level;
    }

    public Level getLevel() {
        return level;
    }

    /**
     * Sets current logger's logging level.
     *
     * @param level
     * @throws IllegalArgumentException If logging level input is null
     */
    public void setLevel(Level level) {
        if (level == null) {
            throw new IllegalArgumentException("Default logging level must not be null");
        }
        this.level = level;
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
        if (this.level.ordinal() <= level.ordinal()) {
            APPENDERS.forEach(appender -> appender.print(constructMessage(level, message)));
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

    public void error(String message) {
        print(Level.ERROR, message);
    }

    public void fatal(String message) {
        print(Level.FATAL, message);
    }

    private String constructMessage(Level level, String message) {
        String result = PATTERN_LAYOUT;
        result = result.replace("{date}", LocalDateTime.now().toString());
        result = result.replace("{level}", level.toString());
        result = result.replace("{message}", message);
        return result;
    }
}
