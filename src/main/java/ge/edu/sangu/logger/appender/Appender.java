package ge.edu.sangu.logger.appender;

import ge.edu.sangu.logger.Level;

import java.io.OutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;

public abstract class Appender {
    public static final String DEFAULT_PATTERN_LAYOUT = "{date} - {name} [{level}]: {message}{n}";

    private String patternLayout = DEFAULT_PATTERN_LAYOUT;

    public void print(String name, Level level, String message) {
        String p = getPatternLayout();
        String result = p
                .replace("{date}", LocalDateTime.now().toString())
                .replace("{level}", level.toString())
                .replace("{message}", message)
                .replace("{name}", name)
                .replace("{n}", System.lineSeparator());

        var printStream = new PrintStream(getOutputStream());
        printStream.print(result);
    }

    public abstract OutputStream getOutputStream();

    public String getPatternLayout() {
        return patternLayout;
    }

    public void setPatternLayout(String patternLayout) {
        this.patternLayout = patternLayout;
    }
}
