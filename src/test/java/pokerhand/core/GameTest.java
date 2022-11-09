package pokerhand.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class GameTest {
    private Card sevenOfClubs,
            sevenOfHearts,
            twoOfDiamonds,
            fiveOfDiamonds,
            threeOfHearts,
            aceOfHearts,
            nineOfHearts,
            twoOfSpades,
            sevenOfSpades,
            sevenOfDiamonds,
            eightOfHearts,
            eightOfClubs,
            eightOfSpades,
            eightOfDiamonds,
            nineOfSpades,
            tenOfDiamonds,
            jackOfClubs,
            queenOfHearts,
            nineOfClubs,
            tenOfClubs,
            tenOfSpades,
            jackOfSpades,
            kingOfSpades,
            aceOfDiamonds,
            queenOfClubs;

    @BeforeEach
    public void setUp() {
        sevenOfClubs = new Card(CardValue.SEVEN, CardColor.CLUB);
        sevenOfHearts = new Card(CardValue.SEVEN, CardColor.HEART);
        sevenOfSpades = new Card(CardValue.SEVEN, CardColor.SPADE);
        sevenOfDiamonds = new Card(CardValue.SEVEN, CardColor.DIAMOND);
        eightOfHearts = new Card(CardValue.EIGHT, CardColor.HEART);
        eightOfClubs = new Card(CardValue.EIGHT, CardColor.CLUB);
        eightOfSpades = new Card(CardValue.EIGHT, CardColor.SPADE);
        eightOfDiamonds = new Card(CardValue.EIGHT, CardColor.DIAMOND);
        nineOfSpades = new Card(CardValue.NINE, CardColor.SPADE);
        tenOfDiamonds = new Card(CardValue.TEN, CardColor.DIAMOND);
        jackOfClubs = new Card(CardValue.JACK, CardColor.CLUB);
        queenOfHearts = new Card(CardValue.QUEEN, CardColor.HEART);
        kingOfSpades = new Card(CardValue.KING, CardColor.SPADE);
        aceOfDiamonds = new Card(CardValue.ACE, CardColor.DIAMOND);
        twoOfDiamonds = new Card(CardValue.TWO, CardColor.DIAMOND);
        fiveOfDiamonds = new Card(CardValue.FIVE, CardColor.DIAMOND);
        threeOfHearts = new Card(CardValue.THREE, CardColor.HEART);
        aceOfHearts = new Card(CardValue.ACE, CardColor.HEART);
        nineOfHearts = new Card(CardValue.NINE, CardColor.HEART);
        twoOfSpades = new Card(CardValue.TWO, CardColor.SPADE);
        nineOfClubs = new Card(CardValue.NINE, CardColor.CLUB);
        tenOfClubs = new Card(CardValue.TEN, CardColor.CLUB);
        tenOfSpades = new Card(CardValue.TEN, CardColor.SPADE);
        jackOfSpades = new Card(CardValue.JACK, CardColor.SPADE);
        queenOfClubs = new Card(CardValue.QUEEN, CardColor.CLUB);
    }

    @Nested
    class TestGameWithSameHandType {

        @Test
        void testHighCard() {
            Hand highCardLoser =
                    new Hand(
                            List.of(
                                    sevenOfDiamonds,
                                    twoOfDiamonds,
                                    aceOfDiamonds,
                                    jackOfClubs,
                                    queenOfHearts));
            Hand highCardWinner =
                    new Hand(
                            List.of(
                                    twoOfSpades,
                                    eightOfSpades,
                                    aceOfDiamonds,
                                    jackOfClubs,
                                    queenOfHearts));
            Party newParty = new Party(highCardLoser, highCardWinner);
            assertEquals(Optional.of(highCardWinner), newParty.getWinner());
        }

        @Test
        void testPair() {

            Hand pairWinner =
                    new Hand(
                            List.of(
                                    sevenOfDiamonds,
                                    sevenOfClubs,
                                    aceOfDiamonds,
                                    jackOfClubs,
                                    queenOfHearts));
            Hand pairLoser =
                    new Hand(
                            List.of(
                                    eightOfClubs,
                                    eightOfSpades,
                                    aceOfDiamonds,
                                    jackOfClubs,
                                    queenOfHearts));
            Party newParty = new Party(pairWinner, pairLoser);
            assertEquals(Optional.of(pairWinner), newParty.getWinner());
        }

        @Test
        void testPairWithSamePairValue() {

            Hand pairLoser =
                    new Hand(
                            List.of(
                                    sevenOfDiamonds,
                                    sevenOfClubs,
                                    twoOfDiamonds,
                                    jackOfClubs,
                                    queenOfHearts));
            Hand pairWinner =
                    new Hand(
                            List.of(
                                    sevenOfSpades,
                                    sevenOfClubs,
                                    aceOfDiamonds,
                                    jackOfClubs,
                                    queenOfHearts));
            Party newParty = new Party(pairLoser, pairWinner);
            assertEquals(Optional.of(pairWinner), newParty.getWinner());
        }

        @Test
        void testTOAK() {
            Hand toakLoser =
                    new Hand(
                            List.of(
                                    sevenOfDiamonds,
                                    sevenOfClubs,
                                    sevenOfHearts,
                                    aceOfDiamonds,
                                    jackOfClubs,
                                    queenOfHearts));
            Hand toakWinner =
                    new Hand(
                            List.of(
                                    eightOfClubs,
                                    eightOfSpades,
                                    eightOfDiamonds,
                                    aceOfDiamonds,
                                    jackOfClubs,
                                    queenOfHearts));
            Party newParty = new Party(toakLoser, toakWinner);
            assertEquals(Optional.of(toakWinner), newParty.getWinner());
        }

        @Test
        void testFOAK() {
            Hand foakLoser =
                    new Hand(
                            List.of(
                                    sevenOfDiamonds,
                                    sevenOfClubs,
                                    sevenOfHearts,
                                    sevenOfSpades,
                                    aceOfDiamonds,
                                    jackOfClubs,
                                    queenOfHearts));
            Hand foakWinner =
                    new Hand(
                            List.of(
                                    eightOfClubs,
                                    eightOfSpades,
                                    eightOfDiamonds,
                                    eightOfHearts,
                                    aceOfDiamonds,
                                    jackOfClubs,
                                    queenOfHearts));
            Party newParty = new Party(foakLoser, foakWinner);
            assertEquals(Optional.of(foakWinner), newParty.getWinner());
        }

        @Test
        void testTwoPair() {
            Hand twoPairLoser =
                    new Hand(
                            List.of(
                                    sevenOfDiamonds,
                                    sevenOfClubs,
                                    eightOfClubs,
                                    eightOfDiamonds,
                                    aceOfDiamonds,
                                    jackOfClubs,
                                    queenOfHearts));
            Hand twoPairWinner =
                    new Hand(
                            List.of(
                                    eightOfClubs,
                                    eightOfSpades,
                                    nineOfSpades,
                                    nineOfSpades,
                                    aceOfDiamonds,
                                    jackOfClubs,
                                    queenOfHearts));
            Party newParty = new Party(twoPairLoser, twoPairWinner);
            assertEquals(Optional.of(twoPairWinner), newParty.getWinner());
        }

        @Test
        void testStraight() {
            Hand straightLoser =
                    new Hand(
                            List.of(
                                    sevenOfDiamonds,
                                    nineOfSpades,
                                    eightOfClubs,
                                    tenOfDiamonds,
                                    kingOfSpades,
                                    jackOfClubs,
                                    queenOfHearts));
            Hand straightWinner =
                    new Hand(
                            List.of(
                                    eightOfClubs,
                                    aceOfDiamonds,
                                    nineOfSpades,
                                    tenOfDiamonds,
                                    kingOfSpades,
                                    jackOfClubs,
                                    queenOfHearts));
            Party newParty = new Party(straightLoser, straightWinner);
            assertEquals(Optional.of(straightWinner), newParty.getWinner());
        }

        @Test
        void testFlush() {
            Hand flushLoser =
                    new Hand(
                            List.of(
                                    tenOfDiamonds,
                                    eightOfDiamonds,
                                    fiveOfDiamonds,
                                    sevenOfDiamonds,
                                    twoOfDiamonds));
            Hand flushWinner =
                    new Hand(
                            List.of(
                                    aceOfHearts,
                                    threeOfHearts,
                                    eightOfHearts,
                                    queenOfHearts,
                                    sevenOfHearts));
            Party newParty = new Party(flushLoser, flushWinner);
            assertEquals(Optional.of(flushWinner), newParty.getWinner());
        }

        @Test
        void testStraightFlush() {
            Hand straightFlushLoser =
                    new Hand(
                            List.of(
                                    sevenOfSpades,
                                    eightOfSpades,
                                    nineOfSpades,
                                    tenOfSpades,
                                    jackOfSpades));
            Hand straightFlushWinner =
                    new Hand(
                            List.of(
                                    eightOfClubs,
                                    nineOfClubs,
                                    tenOfClubs,
                                    jackOfClubs,
                                    queenOfClubs));
            Party newParty = new Party(straightFlushLoser, straightFlushWinner);
            assertEquals(Optional.of(straightFlushWinner), newParty.getWinner());
        }

        @Test
        void testFullHouse() {
            Hand fullHouseLoser =
                    new Hand(
                            List.of(
                                    nineOfClubs,
                                    nineOfHearts,
                                    nineOfSpades,
                                    twoOfSpades,
                                    twoOfDiamonds));
            Hand fullHouseWinner =
                    new Hand(
                            List.of(
                                    tenOfClubs,
                                    tenOfSpades,
                                    tenOfClubs,
                                    jackOfClubs,
                                    jackOfSpades));
            Party newParty = new Party(fullHouseLoser, fullHouseWinner);
            assertEquals(Optional.of(fullHouseWinner), newParty.getWinner());
        }
    }

    @Nested
    class TestGameWithDifferentHandType {}
}
