package pokerhand;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import pokerhand.core.Card;
import pokerhand.core.CardValue;
import pokerhand.core.Hand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ConsoleInterfaceTest {

    @Nested
    @DisplayName("Test card read from console")
    class TestReadCard {
        @Test
        void testReadValidCard() {
            assertEquals(new Hand(new Card(CardValue.ACE)), Hand.fromString("A"));
            assertEquals(new Hand(new Card(CardValue.KING)), Hand.fromString("K"));
            assertEquals(new Hand(new Card(CardValue.QUEEN)), Hand.fromString("Q"));
            assertEquals(new Hand(new Card(CardValue.JACK)), Hand.fromString("J"));
            assertEquals(new Hand(new Card(CardValue.TEN)), Hand.fromString("10"));
            assertEquals(new Hand(new Card(CardValue.NINE)), Hand.fromString("9"));
            assertEquals(new Hand(new Card(CardValue.EIGHT)), Hand.fromString("8"));
            assertEquals(new Hand(new Card(CardValue.SEVEN)), Hand.fromString("7"));
            assertEquals(new Hand(new Card(CardValue.SIX)), Hand.fromString("6"));
            assertEquals(new Hand(new Card(CardValue.FIVE)), Hand.fromString("5"));
            assertEquals(new Hand(new Card(CardValue.FOUR)), Hand.fromString("4"));
            assertEquals(new Hand(new Card(CardValue.THREE)), Hand.fromString("3"));
            assertEquals(new Hand(new Card(CardValue.TWO)), Hand.fromString("2"));
        }

        @Test
        void testReadInvalidCard() {
            assertThrows(IllegalArgumentException.class, () -> Hand.fromString("A21"));
            assertThrows(IllegalArgumentException.class, () -> Hand.fromString("123dc"));
            assertThrows(IllegalArgumentException.class, () -> Hand.fromString("11"));
            assertThrows(IllegalArgumentException.class, () -> Hand.fromString("12"));
        }
    }
}