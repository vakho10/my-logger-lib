package ge.edu.sangu;

public class Launcher {
    private static final Logger log = new Logger(Level.DEBUG, System.out);

    public static void main(String[] args) {
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
