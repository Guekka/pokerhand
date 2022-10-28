package pokerhand;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConsoleInterfaceTest {
    @Nested
    class IntegrationTests {
        InputStream sysInBackup = System.in;
        PrintStream sysOutBackup = System.out;

        @AfterEach
        void cleanup() {
            System.setIn(sysInBackup);
            System.setOut(sysOutBackup);
        }

        @Test
        @DisplayName("Test the integration of the console interface")
        void test_main_WhenGivenTwoHandsWithOneCardBiggerThanTheOther_PrintsTheBiggerHand() {
            ByteArrayInputStream in =
                    new ByteArrayInputStream("4Co 4Pi 4Ca 2Co 4Tr\n5Co 4Co 4Ca 4Tr 6Ca".getBytes());
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            PrintStream out = new PrintStream(byteOut);
            System.setIn(in);
            System.setOut(out);
            ConsoleInterface.main(new String[]{}); // call the method under test
            var expected =
                    String.format(
                            "Welcome to PokerHand console interface!%n"
                                    + "Please enter the first hand:%n"
                                    + "Please enter the second hand:%n"
                                    + "The winner is:%n"
                                    + "[4♥, 4♠, 4♦, 2♥, 4♣]%n"
                                    + "with a  : Four of a Kind%n");

            assertEquals(expected, byteOut.toString());
        }
    }
}
