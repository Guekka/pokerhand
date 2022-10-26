package pokerhand.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PartyTest {

    @Nested
    @DisplayName("Tests the winner for a party with two hands")
    class TestWinner {
        @Test
        void TestEqualHandType() {
            Party party1 = new Party(new Hand(List.of(new Card(CardValue.ACE, CardColor.CLUB))), new Hand(List.of(new Card(CardValue.ACE, CardColor.CLUB))));
            assertEquals(party1.getWinner(), party1.getWinner());

            //same test with 5 cards per hand
            Party party2 = new Party(new Hand(List.of(new Card(CardValue.ACE, CardColor.CLUB), new Card(CardValue.KING, CardColor.CLUB), new Card(CardValue.QUEEN, CardColor.CLUB), new Card(CardValue.JACK, CardColor.CLUB), new Card(CardValue.TEN, CardColor.CLUB))), new Hand(List.of(new Card(CardValue.ACE, CardColor.CLUB), new Card(CardValue.KING, CardColor.CLUB), new Card(CardValue.QUEEN, CardColor.CLUB), new Card(CardValue.JACK, CardColor.CLUB), new Card(CardValue.TEN, CardColor.CLUB))));


            assertEquals(party2.getWinner(), party2.getWinner());
        }

        @Nested
        @DisplayName("Tests the winner for a party with two hands")
        class TestDifferentHandType {
            @Test
            void testSmallerHandType() {
                Party party1 = new Party(new Hand(List.of(new Card(CardValue.ACE, CardColor.CLUB))), new Hand(List.of(new Card(CardValue.KING, CardColor.CLUB))));
                assertEquals(party1.getHands().get(0), party1.getWinner());

                Party party2 = new Party(new Hand(List.of(new Card(CardValue.ACE, CardColor.CLUB), new Card(CardValue.KING, CardColor.CLUB), new Card(CardValue.QUEEN, CardColor.CLUB), new Card(CardValue.JACK, CardColor.CLUB), new Card(CardValue.TEN, CardColor.CLUB))), new Hand(List.of(new Card(CardValue.ACE, CardColor.CLUB), new Card(CardValue.KING, CardColor.CLUB), new Card(CardValue.QUEEN, CardColor.CLUB), new Card(CardValue.JACK, CardColor.CLUB), new Card(CardValue.NINE, CardColor.CLUB))));
                assertEquals(party2.getHands().get(0), party2.getWinner());
            }

            @Test
            void testBiggerHandType() {
                Party party1 = new Party(new Hand(List.of(new Card(CardValue.KING, CardColor.CLUB))), new Hand(List.of(new Card(CardValue.ACE, CardColor.CLUB))));
                assertEquals(party1.getHands().get(1), party1.getWinner());

                Party party2 = new Party(new Hand(List.of(new Card(CardValue.ACE, CardColor.CLUB), new Card(CardValue.KING, CardColor.CLUB), new Card(CardValue.QUEEN, CardColor.CLUB), new Card(CardValue.JACK, CardColor.CLUB), new Card(CardValue.NINE, CardColor.CLUB))), new Hand(List.of(new Card(CardValue.ACE, CardColor.CLUB), new Card(CardValue.KING, CardColor.CLUB), new Card(CardValue.QUEEN, CardColor.CLUB), new Card(CardValue.JACK, CardColor.CLUB), new Card(CardValue.TEN, CardColor.CLUB))));
                assertEquals(party2.getHands().get(1), party2.getWinner());
            }

            @Test
            void testFlushHandType() {
                // flush, not straight
                var flushHand = new Hand(List.of(new Card(CardValue.TWO, CardColor.CLUB), new Card(CardValue.THREE, CardColor.CLUB), new Card(CardValue.FOUR, CardColor.CLUB), new Card(CardValue.FIVE, CardColor.CLUB), new Card(CardValue.SEVEN, CardColor.CLUB)));
                // best card
                var bestCardHand = new Hand(List.of(new Card(CardValue.ACE, CardColor.CLUB), new Card(CardValue.TWO, CardColor.SPADE), new Card(CardValue.THREE, CardColor.HEART), new Card(CardValue.JACK, CardColor.CLUB), new Card(CardValue.NINE, CardColor.CLUB)));

                var party = new Party(flushHand, bestCardHand);
                assertEquals(flushHand, party.getWinner());

            }

            @Test
            void testFlushSecondary() {
                // flush, not straight
                var flushHand = new Hand(List.of(new Card(CardValue.TWO, CardColor.CLUB), new Card(CardValue.FIVE, CardColor.CLUB), new Card(CardValue.SIX, CardColor.CLUB), new Card(CardValue.SEVEN, CardColor.CLUB), new Card(CardValue.NINE, CardColor.CLUB)));
                // better flush, not straight
                var betterFlushHand = new Hand(List.of(new Card(CardValue.TWO, CardColor.CLUB), new Card(CardValue.FOUR, CardColor.CLUB), new Card(CardValue.FIVE, CardColor.CLUB), new Card(CardValue.SIX, CardColor.CLUB), new Card(CardValue.ACE, CardColor.CLUB)));

                var party2 = new Party(flushHand, betterFlushHand);
                assertEquals(betterFlushHand, party2.getWinner());
            }
        }

    }

}