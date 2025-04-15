package ge.edu.sangu;

import ge.edu.sangu.logger.Level;
import ge.edu.sangu.logger.Logger;
import ge.edu.sangu.logger.appender.ConsoleAppender;
import ge.edu.sangu.logger.appender.FileAppender;

public class Launcher {
    private static final Logger log = new Logger(Level.DEBUG);

    public static void main(String[] args) {
        Logger.addAppender(new ConsoleAppender());
        Logger.addAppender(new FileAppender("output.log"));
        log.info("Application started.");
        log.debug("Something is " + 12);
        try {
            throw new RuntimeException("Something bad happened! :(");
        } catch (RuntimeException e) {
            log.error(e.getMessage());
        }
        Person willSmith = new Person("Will Smith", 56);
        System.out.println(willSmith.getName());
        log.info("Application finished.");
    }
}
