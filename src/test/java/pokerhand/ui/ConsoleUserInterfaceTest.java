package pokerhand.ui;

import jdk.jfr.Description;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import pokerhand.core.Card;
import pokerhand.core.CardColor;
import pokerhand.core.CardValue;
import pokerhand.core.Hand;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ConsoleUserInterfaceTest {
    ConsoleUserInterface consoleUserInterface;
    InputStream sysInBackup = System.in;
    PrintStream sysOutBackup = System.out;
    PrintStream sysErrBackup = System.err;

    ByteArrayOutputStream testOut;
    ByteArrayOutputStream testErr;

    @BeforeEach
    void setUp() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
        testErr = new ByteArrayOutputStream();
        System.setErr(new PrintStream(testErr));
        consoleUserInterface = new ConsoleUserInterface();
    }

    @AfterEach
    void tearDown() {
        System.setIn(sysInBackup);
        System.setOut(sysOutBackup);
        System.setErr(sysErrBackup);
    }

    @Test
    void display() {
        consoleUserInterface.display("test");
        assertEquals("test" + System.lineSeparator(), testOut.toString());
    }

    @Test
    void displayError() {
        consoleUserInterface.displayError("test");
        assertEquals("test" + System.lineSeparator(), testErr.toString());
    }

    @Test
    void displayHand() {
        Hand hand =
                new Hand(
                        List.of(
                                new Card(CardValue.ACE, CardColor.CLUB),
                                new Card(CardValue.KING, CardColor.CLUB),
                                new Card(CardValue.QUEEN, CardColor.CLUB),
                                new Card(CardValue.JACK, CardColor.CLUB),
                                new Card(CardValue.TEN, CardColor.CLUB)));
        consoleUserInterface.displayHand(hand);
        assertEquals(hand + System.lineSeparator(), testOut.toString());
    }

    @Test
    void getHand() {
        ByteArrayInputStream in =
                new ByteArrayInputStream(
                        ("5Co 5Ca 4Ca 4Tr 6Ca" + System.lineSeparator()).getBytes());
        System.setIn(in);
        consoleUserInterface = new ConsoleUserInterface();
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
        Hand hand = consoleUserInterface.getHand();
        assertEquals(Hand.fromString("5Co 5Ca 4Ca 4Tr 6Ca"), hand);
    }

    @Nested
    @Description("Test getChoice")
    class GetChoice {
        static final List<String> testChoices = List.of("choice1", "choice2", "choice3");

        @Nested
        @Description("Test getChoice with valid input")
        class ValidInput {
            ByteArrayOutputStream testOut = new ByteArrayOutputStream();
            ByteArrayOutputStream testErr = new ByteArrayOutputStream();

            @Test
            void getChoice() {
                ByteArrayInputStream in =
                        new ByteArrayInputStream(("1\n2\n3" + System.lineSeparator()).getBytes());
                consoleUserInterface =
                        new ConsoleUserInterface(
                                in, new PrintStream(testOut), new PrintStream(testErr));
                System.setOut(new PrintStream(testOut));
                String choice =
                        consoleUserInterface.getChoice(
                                "test", testChoices);
                assertEquals("choice1", choice);

                choice =
                        consoleUserInterface.getChoice(
                                "test", testChoices);
                assertEquals("choice2", choice);

                choice =
                        consoleUserInterface.getChoice(
                                "test", testChoices);

                assertEquals("choice3", choice);
            }
        }

        @Nested
        @Description("Test getChoice with invalid input")
        class InvalidInput {
            @Test
            void getChoice() {
                ByteArrayInputStream in =
                        new ByteArrayInputStream(
                                ("abc \n12\n-10\n2" + System.lineSeparator()).getBytes());
                consoleUserInterface =
                        new ConsoleUserInterface(
                                in, new PrintStream(testOut), new PrintStream(testErr));


                // check for thrown exception
                assertThrows(
                        IllegalArgumentException.class,
                        () ->
                                consoleUserInterface.getChoice(
                                        "test", testChoices));

                assertThrows(
                        IllegalArgumentException.class,
                        () ->
                                consoleUserInterface.getChoice(
                                        "test", testChoices));

                assertThrows(
                        IllegalArgumentException.class,
                        () ->
                                consoleUserInterface.getChoice(
                                        "test", testChoices));
                String choice =
                        consoleUserInterface.getChoice(
                                "test", testChoices);
                assertEquals("choice2", choice);
            }
        }
    }
}
