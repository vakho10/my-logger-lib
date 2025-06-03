package ge.edu.sangu.logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LoggerBadTest {

    private Logger log;

    @BeforeEach
    void setUp() {
        System.out.println("LoggerTest.setUp");
        log = new Logger("Test");
    }

    @Disabled
    @Test
    void testPrintNullLevel() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            log.print(null, "asdf");
        });
        assertEquals("Logging level must not be null", e.getMessage());
    }

    @Disabled
    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"    ", ""})
    @DisplayName("Test when print gets null or blank message")
    void testPrintNullOrBlankMessage(String message) {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            log.print(Level.ERROR, message);
        });
        assertEquals("Log message must not be null or blank", e.getMessage());
    }
}
