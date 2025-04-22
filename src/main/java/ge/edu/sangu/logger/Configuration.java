package ge.edu.sangu.logger;

import ge.edu.sangu.logger.appender.Appender;

import java.util.ArrayList;
import java.util.List;

public class Configuration {

    private static Level LOGGING_LEVEL = Level.INFO;
    private static List<Appender> APPENDERS = new ArrayList<>();

    public static void setLoggingLevel(Level loggingLevel) {
        LOGGING_LEVEL = loggingLevel;
    }

    public static void addAppender(Appender appender) {
        APPENDERS.add(appender);
    }

    public static Level getLoggingLevel() {
        return LOGGING_LEVEL;
    }

    public static List<Appender> getAppenders() {
        return APPENDERS;
    }
}
