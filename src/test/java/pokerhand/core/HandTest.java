package pokerhand.core;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}