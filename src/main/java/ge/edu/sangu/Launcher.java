package ge.edu.sangu;

import ge.edu.sangu.logger.Configuration;
import ge.edu.sangu.logger.Level;
import ge.edu.sangu.logger.Logger;
import ge.edu.sangu.logger.appender.ConsoleAppender;
import ge.edu.sangu.logger.appender.DatabaseAppender;
import ge.edu.sangu.logger.appender.FileAppender;

public class Launcher {
    private static final Logger log = new Logger(Launcher.class.getName());

    public static void main(String[] args) {
        Configuration.setLoggingLevel(Level.DEBUG);
        ConsoleAppender consoleAppender = new ConsoleAppender();
        consoleAppender.setPatternLayout("{name} - {level}: {message} - {date}{n}");
        Configuration.addAppender(consoleAppender);
        Configuration.addAppender(new FileAppender("output.log"));
        Configuration.addAppender(new DatabaseAppender("LOGS", "MESSAGE",
                "jdbc:h2:file:~/my-logs", "admin", "admin"));
        log.info("Application started.");
        log.debug("Something is " + 12);
        try {
            foo();
        } catch (RuntimeException e) {
            log.error(e.getMessage(), e.getStackTrace());
        }
        Person willSmith = new Person("Will Smith", 56);
        System.out.println(willSmith.getName());
        log.info("Application finished.");
    }

    private static void foo() {
        throw new RuntimeException("Something bad happened! :(");
    }
}
