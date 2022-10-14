package pokerhand;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import pokerhand.core.Card;
import pokerhand.core.CardValue;
import pokerhand.core.Hand;
import pokerhand.core.HandType;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleInterfaceTest {

    @Nested
    @DisplayName("Test card read from console")
    class TestReadCard {
        @Test
        void testReadValidCard() {
            assertEquals(new Hand(new Card(CardValue.ACE)), Hand.getHandFromString("A"));
            assertEquals(new Hand(new Card(CardValue.KING)), Hand.getHandFromString("K"));
            assertEquals(new Hand(new Card(CardValue.QUEEN)), Hand.getHandFromString("Q"));
            assertEquals(new Hand(new Card(CardValue.JACK)), Hand.getHandFromString("J"));
            assertEquals(new Hand(new Card(CardValue.TEN)), Hand.getHandFromString("10"));
            assertEquals(new Hand(new Card(CardValue.NINE)), Hand.getHandFromString("9"));
            assertEquals(new Hand(new Card(CardValue.EIGHT)), Hand.getHandFromString("8"));
            assertEquals(new Hand(new Card(CardValue.SEVEN)), Hand.getHandFromString("7"));
            assertEquals(new Hand(new Card(CardValue.SIX)), Hand.getHandFromString("6"));
            assertEquals(new Hand(new Card(CardValue.FIVE)), Hand.getHandFromString("5"));
            assertEquals(new Hand(new Card(CardValue.FOUR)), Hand.getHandFromString("4"));
            assertEquals(new Hand(new Card(CardValue.THREE)), Hand.getHandFromString("3"));
            assertEquals(new Hand(new Card(CardValue.TWO)), Hand.getHandFromString("2"));
        }
        @Test
        void testReadInvalidCard(){
            assertThrows(IllegalArgumentException.class, () -> Hand.getHandFromString("A21"));
            assertThrows(IllegalArgumentException.class, () -> Hand.getHandFromString("123dc"));
            assertThrows(IllegalArgumentException.class, () -> Hand.getHandFromString("11"));
            assertThrows(IllegalArgumentException.class, () -> Hand.getHandFromString("12"));
        }
    }
}