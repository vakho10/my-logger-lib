package ge.edu.sangu;

import java.util.Scanner;

public class Launcher {

    private static void inputDefaultLoggingLevel() {
        do {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Please enter default logging level. Choose from [DEBUG, INFO, ERROR]: ");
                Level defaultLoggingLevel = Level.valueOf(scanner.nextLine());
                Logger.setDefaultLevel(defaultLoggingLevel);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Please enter valid logging level!");
            }
        } while (true);
    }

    public static void main(String[] args) {
        inputDefaultLoggingLevel();
        Logger log = new Logger(System.out);
        log.info("Application started.");
        log.debug("Something is " + 12);
        try {
            throw new RuntimeException("Something bad happened! :(");
        } catch (RuntimeException e) {
            log.error(e.getMessage());
        }
        log.info("Application finished.");
    }
}
