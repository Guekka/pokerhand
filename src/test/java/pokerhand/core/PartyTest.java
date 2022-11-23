package pokerhand.core;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import pokerhand.ui.core.ConsoleUserInterface;

class PartyTest {
    @Nested
    @DisplayName("Tests the winner for a party with two hands")
    class TestWinner {
        @Test
        void TestEqualHandType() {
            var oneCardHand = new Hand(List.of(new Card(CardValue.ACE, CardColor.CLUB)));
            var otherCardHand = new Hand(List.of(new Card(CardValue.ACE, CardColor.DIAMOND)));
            var party1 = new Party(oneCardHand, otherCardHand);
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
            var otherFiveCardsHand =
                    new Hand(
                            List.of(
                                    new Card(CardValue.ACE, CardColor.DIAMOND),
                                    new Card(CardValue.KING, CardColor.DIAMOND),
                                    new Card(CardValue.QUEEN, CardColor.DIAMOND),
                                    new Card(CardValue.JACK, CardColor.DIAMOND),
                                    new Card(CardValue.TEN, CardColor.DIAMOND)));
            var party2 = new Party(fiveCardsHand, otherFiveCardsHand);
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

    @Nested
    @DisplayName("Tests the game logic for a party")
    class TestGameLogic {
        @Test
        void testAddHand() {
            var party = new Party();
            var hand = new Hand(List.of(new Card(CardValue.ACE, CardColor.CLUB)));
            party.addHand(hand);
            assertThat(party.getHands()).containsExactly(hand);
            assertThrows(IllegalArgumentException.class, () -> party.addHand(hand));
        }

        @Test
        void testAddTwoHands() {
            var party = new Party();
            var hand1 = new Hand(List.of(new Card(CardValue.ACE, CardColor.CLUB)));
            var hand2 = new Hand(List.of(new Card(CardValue.ACE, CardColor.DIAMOND)));
            party.addHand(hand1);
            party.addHand(hand2);
            assertThat(party.getHands()).containsExactly(hand1, hand2);
        }

        @Nested
        @DisplayName("Test the run method")
        class TestRun {

            @Nested
            @DisplayName("Test the run method with correct input")
            class TestRunWithCorrectInput {

                @Test
                void test_run_WithEqualHands() {
                    var input =
                            new ByteArrayInputStream(
                                    "2Pi 8Pi ACa VTr DCo\n2Co 8Ca ACo VPi DTr".getBytes());
                    var output = new ByteArrayOutputStream();
                    var error = new ByteArrayOutputStream();
                    var consoleInterface =
                            new ConsoleUserInterface(
                                    input, new PrintStream(output), new PrintStream(error));
                    var party = new Party(consoleInterface);
                    party.run(true);
                    assertThat(output.toString()).contains("It's a tie!");
                    // Check the reset method
                    assertThat(party.getHands()).isEmpty();
                    assertDoesNotThrow(
                            () ->
                                    party.addHand(
                                            new Hand(
                                                    List.of(
                                                            new Card(
                                                                    CardValue.TWO,
                                                                    CardColor.DIAMOND)))));
                    assertDoesNotThrow(
                            () ->
                                    party.addHand(
                                            new Hand(
                                                    List.of(
                                                            new Card(
                                                                    CardValue.TWO,
                                                                    CardColor.CLUB)))));
                }

                @Test
                void test_run_WithDifferentHands() {
                    var input =
                            new ByteArrayInputStream(
                                    "4Co 4Pi 7Ca 2Co 5Tr\n5Co 5Ca 4Ca 4Tr 6Ca".getBytes());
                    var output = new ByteArrayOutputStream();
                    var error = new ByteArrayOutputStream();
                    var consoleInterface =
                            new ConsoleUserInterface(
                                    input, new PrintStream(output), new PrintStream(error));
                    var party = new Party(consoleInterface);
                    party.run(true);
                    assertThat(output.toString()).contains("[5♥, 5♦, 4♦, 4♣, 6♦]");
                    assertThat(output.toString()).contains("Two Pair");
                    assertThat(output.toString()).contains("Player 2");
                    // Check the reset method
                    assertThat(party.getHands()).isEmpty();
                    assertDoesNotThrow(
                            () ->
                                    party.addHand(
                                            new Hand(
                                                    List.of(
                                                            new Card(
                                                                    CardValue.FOUR,
                                                                    CardColor.HEART),
                                                            new Card(
                                                                    CardValue.FOUR,
                                                                    CardColor.SPADE),
                                                            new Card(
                                                                    CardValue.SEVEN,
                                                                    CardColor.DIAMOND),
                                                            new Card(
                                                                    CardValue.TWO, CardColor.HEART),
                                                            new Card(
                                                                    CardValue.FIVE,
                                                                    CardColor.CLUB)))));
                    assertDoesNotThrow(
                            () ->
                                    party.addHand(
                                            new Hand(
                                                    List.of(
                                                            new Card(
                                                                    CardValue.FIVE,
                                                                    CardColor.HEART),
                                                            new Card(
                                                                    CardValue.FIVE,
                                                                    CardColor.DIAMOND),
                                                            new Card(
                                                                    CardValue.FOUR,
                                                                    CardColor.DIAMOND),
                                                            new Card(
                                                                    CardValue.FOUR, CardColor.CLUB),
                                                            new Card(
                                                                    CardValue.SIX,
                                                                    CardColor.DIAMOND)))));
                }
            }

            @Nested
            @DisplayName("Test the run method with incorrect input")
            class TestRunWithIncorrectInput {

                @Test
                @DisplayName("Test the run method with a wrong cards")
                void test_run_WithMalformedCard() {
                    var input =
                            new ByteArrayInputStream(
                                    "0Tr 0Tr 0Tr 0Tr 0Tr\n4Co 4Pi 7Ca 2Co 5Tr\n5Co 5Ca 4Ca 4Tr 6Ca"
                                            .getBytes());
                    var output = new ByteArrayOutputStream();
                    var error = new ByteArrayOutputStream();
                    var consoleInterface =
                            new ConsoleUserInterface(
                                    input, new PrintStream(output), new PrintStream(error));
                    var party = new Party(consoleInterface);
                    party.run(true);
                    assertThat(error.toString()).isNotEmpty();
                }

                @Test
                @DisplayName("Test the run method with a wrong number of cards")
                void test_run_WithWrongNumberOfCards() {
                    var input =
                            new ByteArrayInputStream(
                                    "4Co 4Pi 7Ca 2Co 5Tr\n5Co 5Ca 4Ca 4Tr 6Ca 6Ca\n5Co 5Ca 4Ca 4Tr 6Ca"
                                            .getBytes());
                    var output = new ByteArrayOutputStream();
                    var error = new ByteArrayOutputStream();
                    var consoleInterface =
                            new ConsoleUserInterface(
                                    input, new PrintStream(output), new PrintStream(error));
                    var party = new Party(consoleInterface);
                    party.run(true);
                    assertThat(error.toString()).isNotEmpty();
                }
            }
        }
    }
}
