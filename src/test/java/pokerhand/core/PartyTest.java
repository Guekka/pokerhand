package pokerhand.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PartyTest {

    @Nested
    @DisplayName("Tests the winner for a party with two hands" )
    class TestWinner {
        @Test
        void TestEqualHandType() {
            Party party1 = new Party(new Hand(List.of(new Card(CardValue.ACE))), new Hand(List.of(new Card(CardValue.ACE))));
            assertEquals(party1.getWinner(), party1.getWinner());

            //same test with 5 cards per hand
            Party party2 = new Party(new Hand(List.of(new Card(CardValue.ACE), new Card(CardValue.KING), new Card(CardValue.QUEEN), new Card(CardValue.JACK), new Card(CardValue.TEN))), new Hand(List.of(new Card(CardValue.ACE), new Card(CardValue.KING), new Card(CardValue.QUEEN), new Card(CardValue.JACK), new Card(CardValue.TEN))));
            assertEquals(party2.getWinner(), party2.getWinner());
        }

        @Nested
        @DisplayName("Tests the winner for a party with two hands" )
        class TestDifferentHandType {
            @Test
            void testSmallerHandType() {
                Party party1 = new Party(new Hand(List.of(new Card(CardValue.ACE))), new Hand(List.of(new Card(CardValue.KING))));
                assertEquals(party1.getHands().get(0), party1.getWinner());

                Party party2 = new Party(new Hand(List.of(new Card(CardValue.ACE), new Card(CardValue.KING), new Card(CardValue.QUEEN), new Card(CardValue.JACK), new Card(CardValue.TEN))), new Hand(List.of(new Card(CardValue.ACE), new Card(CardValue.KING), new Card(CardValue.QUEEN), new Card(CardValue.JACK), new Card(CardValue.NINE))));
                assertEquals(party2.getHands().get(0), party2.getWinner());
            }

            @Test
            void testBiggerHandType() {
                Party party1 = new Party(new Hand(List.of(new Card(CardValue.KING))), new Hand(List.of(new Card(CardValue.ACE))));
                assertEquals(party1.getHands().get(1), party1.getWinner());

                Party party2 = new Party(new Hand(List.of(new Card(CardValue.ACE), new Card(CardValue.KING), new Card(CardValue.QUEEN), new Card(CardValue.JACK), new Card(CardValue.NINE))), new Hand(List.of(new Card(CardValue.ACE), new Card(CardValue.KING), new Card(CardValue.QUEEN), new Card(CardValue.JACK), new Card(CardValue.TEN))));
                assertEquals(party2.getHands().get(1), party2.getWinner());
            }
        }

    }

}