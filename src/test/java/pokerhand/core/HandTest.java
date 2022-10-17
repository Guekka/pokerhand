package pokerhand.core;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class HandTest {

    @Nested
    @DisplayName("Tests if the power of a hand with a single card is calculated correctly")
    class TestPowerWithOneCard {
        @Test
        void testEqualHandType() {
            Hand hand = new Hand(new Card(CardValue.ACE));
            assertEquals(hand.getPower(), hand.getPower());
        }

        @Test
        void testSmallerHandType() {
            Hand hand1 = new Hand(new Card(CardValue.ACE));
            Hand hand2 = new Hand(new Card(CardValue.KING));
            assertEquals(1, hand1.getPower().compareTo(hand2.getPower()));
        }

        @Test
        void testBiggerHandType() {
            Hand hand = new Hand(new Card(CardValue.ACE));
            Hand hand2 = new Hand(new Card(CardValue.KING));
            assertEquals(-1, hand2.getPower().compareTo(hand.getPower()));
        }


    }

    @Nested
    @DisplayName("Test from string method")
    class TestFromString {
        @Test
        void testValidCard() {
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
        void testInvalidCard() {
            assertThrows(IllegalArgumentException.class, () -> Hand.fromString("A21"));
            assertThrows(IllegalArgumentException.class, () -> Hand.fromString("123dc"));
            assertThrows(IllegalArgumentException.class, () -> Hand.fromString("11"));
            assertThrows(IllegalArgumentException.class, () -> Hand.fromString("12"));
        }
    }


}