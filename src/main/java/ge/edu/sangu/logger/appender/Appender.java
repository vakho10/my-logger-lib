package ge.edu.sangu.logger.appender;

import ge.edu.sangu.logger.Level;

import java.io.OutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;

public abstract class Appender {
    public static final String DEFAULT_PATTERN_LAYOUT = "{date} - {name} [{level}]: {message}";

    private String patternLayout = DEFAULT_PATTERN_LAYOUT;

    public void print(String name, Level level, String message) {
        String result = patternLayout.replace("{date}", LocalDateTime.now().toString())
                .replace("{level}", level.toString())
                .replace("{message}", message)
                .replace("{name}", name);

        var printStream = new PrintStream(getOutputStream());
        printStream.println(result);
    }

    public abstract OutputStream getOutputStream();

    public String getPatternLayout() {
        return patternLayout;
    }

    public void setPatternLayout(String patternLayout) {
        this.patternLayout = patternLayout;
    }
}
