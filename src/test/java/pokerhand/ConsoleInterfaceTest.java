package pokerhand;

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
        @Test
        @DisplayName("Test the integration of the console interface")
        void test_main_WhenGivenTwoHandsWithOneCardBiggerThanTheOther_PrintsTheBiggerHand() {
            InputStream sysInBackup = System.in; // backup System.in to restore it later
            ByteArrayInputStream in = new ByteArrayInputStream("4Co\n5Co".getBytes());
            PrintStream sysOutBackup = System.out; // backup System.out to restore it later
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            PrintStream out = new PrintStream(byteOut);
            System.setIn(in);
            System.setOut(out);
            ConsoleInterface consoleInterface = new ConsoleInterface();
            ConsoleInterface.main(new String[]{}); // call the method under test
            var splits = byteOut.toString().split("\n");
            assertEquals("Welcome to PokerHand console interface!", splits[0].trim());
            assertEquals("Please enter the first hand:", splits[1].trim());
            assertEquals("Please enter the second hand:", splits[2].trim());
            assertEquals("The winner is:", splits[3].trim());
            assertEquals("[FIVE HEART]", splits[4].trim());


            System.setIn(sysInBackup);
            System.setOut(sysOutBackup);


        }
    }

}