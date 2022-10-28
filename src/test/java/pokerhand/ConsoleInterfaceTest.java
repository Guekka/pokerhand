package pokerhand;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

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
            ConsoleInterface.main(new String[] {}); // call the method under test
            var expected =
                    String.format(
                            "Welcome to PokerHand console interface!%nPlease enter the first"
                                + " hand:%nPlease enter the second hand:%nThe winner is:%n[FOUR"
                                + " HEART, FOUR SPADE, FOUR DIAMOND, TWO HEART, FOUR CLUB]%nwith a "
                                + " : FOUR_OF_A_KIND%n");

            assertEquals(expected, byteOut.toString());
        }
    }
}
