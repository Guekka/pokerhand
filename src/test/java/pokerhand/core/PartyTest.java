package pokerhand.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PartyTest {
    @Nested
    @DisplayName("Tests the winner for a party with two hands")
    class TestWinner {
        @Test
        void TestEqualHandType() {
            var oneCardHand = new Hand(List.of(new Card(CardValue.ACE, CardColor.CLUB)));
            var party1 = new Party(oneCardHand, oneCardHand);
            assertEquals(Optional.empty(), party1.getWinner());

            // same test with 5 cards per hand
            var fiveCardsHand =
                    new Hand(
                            List.of(
                                    new Card(CardValue.ACE, CardColor.CLUB),
                                    new Card(CardValue.KING, CardColor.CLUB),
                                    new Card(CardValue.QUEEN, CardColor.CLUB),
                                    new Card(CardValue.JACK, CardColor.CLUB),
                                    new Card(CardValue.TEN, CardColor.CLUB)));
            var party2 = new Party(fiveCardsHand, fiveCardsHand);
            assertEquals(Optional.empty(), party2.getWinner());
        }

        @Nested
        @DisplayName("Tests the winner for a party with two hands")
        class TestDifferentHandType {
            @Test
            void testSmallerHandType() {
                var oneCardHandWinner = new Hand(List.of(new Card(CardValue.ACE, CardColor.CLUB)));
                var oneCardHandLoser = new Hand(List.of(new Card(CardValue.KING, CardColor.CLUB)));

                Party party1 = new Party(oneCardHandLoser, oneCardHandWinner);
                assertThat(party1.getWinner()).hasValue(oneCardHandWinner);

                var fiveCardsHandWinner =
                        new Hand(
                                List.of(
                                        new Card(CardValue.ACE, CardColor.CLUB),
                                        new Card(CardValue.KING, CardColor.CLUB),
                                        new Card(CardValue.QUEEN, CardColor.CLUB),
                                        new Card(CardValue.JACK, CardColor.CLUB),
                                        new Card(CardValue.TEN, CardColor.CLUB)));
                var fiveCardsHandLoser =
                        new Hand(
                                List.of(
                                        new Card(CardValue.ACE, CardColor.DIAMOND),
                                        new Card(CardValue.KING, CardColor.DIAMOND),
                                        new Card(CardValue.QUEEN, CardColor.DIAMOND),
                                        new Card(CardValue.JACK, CardColor.DIAMOND),
                                        new Card(CardValue.NINE, CardColor.DIAMOND)));

                Party party2 = new Party(fiveCardsHandWinner, fiveCardsHandLoser);
                assertThat(party2.getWinner()).hasValue(fiveCardsHandWinner);
            }

            @Test
            void testBiggerHandType() {
                var oneCardHandWinner = new Hand(List.of(new Card(CardValue.ACE, CardColor.CLUB)));
                var oneCardHandLoser = new Hand(List.of(new Card(CardValue.KING, CardColor.CLUB)));

                var party1 = new Party(oneCardHandLoser, oneCardHandWinner);
                assertThat(party1.getWinner()).hasValue(oneCardHandWinner);

                var fiveCardsHandWinner =
                        new Hand(
                                List.of(
                                        new Card(CardValue.ACE, CardColor.CLUB),
                                        new Card(CardValue.KING, CardColor.CLUB),
                                        new Card(CardValue.QUEEN, CardColor.CLUB),
                                        new Card(CardValue.JACK, CardColor.CLUB),
                                        new Card(CardValue.TEN, CardColor.CLUB)));
                var fiveCardsHandLoser =
                        new Hand(
                                List.of(
                                        new Card(CardValue.ACE, CardColor.DIAMOND),
                                        new Card(CardValue.KING, CardColor.DIAMOND),
                                        new Card(CardValue.QUEEN, CardColor.DIAMOND),
                                        new Card(CardValue.JACK, CardColor.DIAMOND),
                                        new Card(CardValue.NINE, CardColor.DIAMOND)));

                Party party2 = new Party(fiveCardsHandWinner, fiveCardsHandLoser);
                assertThat(party2.getWinner()).hasValue(fiveCardsHandWinner);
            }
        }
    }
}
