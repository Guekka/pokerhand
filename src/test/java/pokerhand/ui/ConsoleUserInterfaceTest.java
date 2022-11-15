package pokerhand.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pokerhand.core.Card;
import pokerhand.core.CardColor;
import pokerhand.core.CardValue;
import pokerhand.core.Hand;

class ConsoleUserInterfaceTest {
    ConsoleUserInterface consoleUserInterface;
    InputStream sysInBackup = System.in;
    PrintStream sysOutBackup = System.out;
    PrintStream sysErrBackup = System.err;

    ByteArrayOutputStream testOut;
    ByteArrayOutputStream testErr;

    @BeforeEach
    void setUp() {
        consoleUserInterface = new ConsoleUserInterface();
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
        testErr = new ByteArrayOutputStream();
        System.setErr(new PrintStream(testErr));
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
        assertEquals(hand.toString() + System.lineSeparator(), testOut.toString());
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
}
