package ge.edu.sangu;

import java.io.OutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;

public class Logger {
    private static Level DEFAULT_LEVEL = Level.INFO;

    private final PrintStream out;

    public Logger(OutputStream out) {
        this.out = new PrintStream(out);
    }

    public static Level getDefaultLevel() {
        return DEFAULT_LEVEL;
    }

    public static void setDefaultLevel(Level defaultLevel) {
        if (defaultLevel == null) {
            throw new IllegalArgumentException("Default logging level must not be null");
        }
        DEFAULT_LEVEL = defaultLevel;
    }

    public void print(Level level, String message) {
        if (message == null || message.isBlank()) {
            throw new IllegalArgumentException("Log message must not be null or blank");
        }
        // Check if our level is the same or "more important" than default level
        if (DEFAULT_LEVEL.ordinal() <= level.ordinal()) {
            out.printf("%s [%s]: %s\n", LocalDateTime.now(), level, message);
        }
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
}
