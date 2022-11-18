package pokerhand.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class HandComparisonsTest {
    private final Card sevenOfClubs = new Card(CardValue.SEVEN, CardColor.CLUB);
    private final Card sevenOfHearts = new Card(CardValue.SEVEN, CardColor.HEART);
    private final Card sevenOfSpades = new Card(CardValue.SEVEN, CardColor.SPADE);
    private final Card sevenOfDiamonds = new Card(CardValue.SEVEN, CardColor.DIAMOND);
    private final Card eightOfHearts = new Card(CardValue.EIGHT, CardColor.HEART);
    private final Card eightOfClubs = new Card(CardValue.EIGHT, CardColor.CLUB);
    private final Card eightOfSpades = new Card(CardValue.EIGHT, CardColor.SPADE);
    private final Card eightOfDiamonds = new Card(CardValue.EIGHT, CardColor.DIAMOND);
    private final Card nineOfSpades = new Card(CardValue.NINE, CardColor.SPADE);
    private final Card tenOfDiamonds = new Card(CardValue.TEN, CardColor.DIAMOND);
    private final Card jackOfClubs = new Card(CardValue.JACK, CardColor.CLUB);
    private final Card queenOfHearts = new Card(CardValue.QUEEN, CardColor.HEART);
    private final Card kingOfSpades = new Card(CardValue.KING, CardColor.SPADE);
    private final Card aceOfDiamonds = new Card(CardValue.ACE, CardColor.DIAMOND);
    private final Card twoOfDiamonds = new Card(CardValue.TWO, CardColor.DIAMOND);
    private final Card nineOfDiamonds = new Card(CardValue.NINE, CardColor.DIAMOND);
    private final Card fiveOfDiamonds = new Card(CardValue.FIVE, CardColor.DIAMOND);
    private final Card threeOfHearts = new Card(CardValue.THREE, CardColor.HEART);
    private final Card aceOfHearts = new Card(CardValue.ACE, CardColor.HEART);
    private final Card nineOfHearts = new Card(CardValue.NINE, CardColor.HEART);
    private final Card twoOfSpades = new Card(CardValue.TWO, CardColor.SPADE);
    private final Card nineOfClubs = new Card(CardValue.NINE, CardColor.CLUB);
    private final Card tenOfClubs = new Card(CardValue.TEN, CardColor.CLUB);
    private final Card tenOfSpades = new Card(CardValue.TEN, CardColor.SPADE);
    private final Card jackOfSpades = new Card(CardValue.JACK, CardColor.SPADE);
    private final Card queenOfClubs = new Card(CardValue.QUEEN, CardColor.CLUB);
    private final Card twoOfHearts = new Card(CardValue.TWO, CardColor.HEART);

    private final Card aceOfSpades = new Card(CardValue.ACE, CardColor.SPADE);

    private final Card fiveOfSpades = new Card(CardValue.FIVE, CardColor.SPADE);
    private final Card queenOfSpades = new Card(CardValue.QUEEN, CardColor.SPADE);
    private final Card tenOfHearts = new Card(CardValue.TEN, CardColor.HEART);
    private final Card jackOfHearts = new Card(CardValue.JACK, CardColor.HEART);

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
                                    aceOfHearts,
                                    jackOfSpades,
                                    queenOfClubs));
            Game newGame = new Game(highCardLoser, highCardWinner);
            assertEquals(Optional.of(highCardWinner), newGame.getWinner());
        }

        @Test
        void testHighCardWith4SameCards() {
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
                                    sevenOfClubs,
                                    threeOfHearts,
                                    aceOfHearts,
                                    jackOfSpades,
                                    queenOfClubs));
            Game newGame = new Game(highCardLoser, highCardWinner);
            assertEquals(Optional.of(highCardWinner), newGame.getWinner());
        }

        @Test
        void testPair() {

            Hand pairLoser =
                    new Hand(
                            List.of(
                                    sevenOfDiamonds,
                                    sevenOfClubs,
                                    aceOfDiamonds,
                                    jackOfClubs,
                                    queenOfHearts));
            Hand pairWinner =
                    new Hand(
                            List.of(
                                    eightOfClubs,
                                    eightOfSpades,
                                    aceOfHearts,
                                    jackOfSpades,
                                    queenOfClubs));
            Game newGame = new Game(pairWinner, pairLoser);
            assertEquals(Optional.of(pairWinner), newGame.getWinner());
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
                                    sevenOfHearts,
                                    aceOfDiamonds,
                                    jackOfSpades,
                                    queenOfClubs));
            Game newGame = new Game(pairLoser, pairWinner);
            assertEquals(Optional.of(pairWinner), newGame.getWinner());
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
                                    queenOfHearts));
            Hand toakWinner =
                    new Hand(
                            List.of(
                                    eightOfClubs,
                                    eightOfSpades,
                                    eightOfDiamonds,
                                    jackOfClubs,
                                    queenOfClubs));
            Game newGame = new Game(toakLoser, toakWinner);
            assertEquals(Optional.of(toakWinner), newGame.getWinner());
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
                                    jackOfClubs));
            Hand foakWinner =
                    new Hand(
                            List.of(
                                    eightOfClubs,
                                    eightOfSpades,
                                    eightOfDiamonds,
                                    eightOfHearts,
                                    aceOfDiamonds));
            Game newGame = new Game(foakLoser, foakWinner);
            assertEquals(Optional.of(foakWinner), newGame.getWinner());
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
                                    aceOfDiamonds));
            Hand twoPairWinner =
                    new Hand(
                            List.of(
                                    eightOfHearts,
                                    eightOfSpades,
                                    nineOfSpades,
                                    nineOfHearts,
                                    aceOfHearts));
            Game newGame = new Game(twoPairLoser, twoPairWinner);
            assertEquals(Optional.of(twoPairWinner), newGame.getWinner());
        }

        @Test
        void testTwoPairWithSameHighestPair() {
            Hand twoPairLoser =
                    new Hand(
                            List.of(
                                    sevenOfDiamonds,
                                    sevenOfClubs,
                                    nineOfSpades,
                                    nineOfClubs,
                                    aceOfDiamonds));
            Hand twoPairWinner =
                    new Hand(
                            List.of(
                                    eightOfClubs,
                                    eightOfSpades,
                                    nineOfHearts,
                                    nineOfDiamonds,
                                    aceOfHearts));
            Game newGame = new Game(twoPairLoser, twoPairWinner);
            assertEquals(Optional.of(twoPairWinner), newGame.getWinner());
        }

        @Test
        void testTwoPairWithSameTwoPair() {
            Hand twoPairLoser =
                    new Hand(
                            List.of(
                                    eightOfClubs,
                                    eightOfSpades,
                                    nineOfSpades,
                                    nineOfClubs,
                                    kingOfSpades));
            Hand twoPairWinner =
                    new Hand(
                            List.of(
                                    eightOfHearts,
                                    eightOfDiamonds,
                                    nineOfHearts,
                                    nineOfDiamonds,
                                    aceOfDiamonds));
            Game newGame = new Game(twoPairLoser, twoPairWinner);
            assertEquals(Optional.of(twoPairWinner), newGame.getWinner());
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
                                    jackOfClubs));
            Hand straightWinner =
                    new Hand(
                            List.of(
                                    eightOfDiamonds,
                                    nineOfDiamonds,
                                    tenOfClubs,
                                    jackOfSpades,
                                    queenOfHearts));
            Game newGame = new Game(straightLoser, straightWinner);
            assertEquals(Optional.of(straightWinner), newGame.getWinner());
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
            Game newGame = new Game(flushLoser, flushWinner);
            assertEquals(Optional.of(flushWinner), newGame.getWinner());
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
            Game newGame = new Game(straightFlushLoser, straightFlushWinner);
            assertEquals(Optional.of(straightFlushWinner), newGame.getWinner());
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
            Game newGame = new Game(fullHouseLoser, fullHouseWinner);
            assertEquals(Optional.of(fullHouseWinner), newGame.getWinner());
        }
    }

    @Nested
    class TestGameWithDifferentHandType {
        @Test
        void testHighCardVsPair() {
            Hand highCard =
                    new Hand(
                            List.of(
                                    twoOfSpades,
                                    eightOfSpades,
                                    aceOfDiamonds,
                                    jackOfClubs,
                                    queenOfHearts));
            Hand pair =
                    new Hand(
                            List.of(
                                    eightOfClubs,
                                    eightOfDiamonds,
                                    aceOfHearts,
                                    jackOfSpades,
                                    queenOfClubs));
            Game newGame = new Game(highCard, pair);
            assertEquals(Optional.of(pair), newGame.getWinner());
        }

        @Test
        void testPairVsTwoPair() {
            Hand pair =
                    new Hand(
                            List.of(
                                    eightOfClubs,
                                    eightOfSpades,
                                    aceOfDiamonds,
                                    jackOfClubs,
                                    queenOfHearts));
            Hand twoPair =
                    new Hand(
                            List.of(
                                    twoOfDiamonds,
                                    twoOfHearts,
                                    nineOfSpades,
                                    nineOfSpades,
                                    aceOfHearts));
            Game newGame = new Game(pair, twoPair);
            assertEquals(Optional.of(twoPair), newGame.getWinner());
        }

        @Test
        void testTwoPairVsTOAK() {
            Hand toak =
                    new Hand(
                            List.of(
                                    sevenOfDiamonds,
                                    sevenOfClubs,
                                    sevenOfHearts,
                                    aceOfDiamonds,
                                    jackOfClubs));
            Hand twoPair =
                    new Hand(
                            List.of(
                                    eightOfClubs,
                                    eightOfSpades,
                                    nineOfSpades,
                                    nineOfClubs,
                                    aceOfHearts));
            Game newGame = new Game(toak, twoPair);
            assertEquals(Optional.of(toak), newGame.getWinner());
        }

        @Test
        void testTOAKVsStraight() {
            Hand toak =
                    new Hand(
                            List.of(
                                    eightOfDiamonds,
                                    eightOfClubs,
                                    eightOfHearts,
                                    aceOfDiamonds,
                                    queenOfHearts));
            Hand straight =
                    new Hand(
                            List.of(
                                    sevenOfDiamonds,
                                    nineOfSpades,
                                    eightOfSpades,
                                    tenOfDiamonds,
                                    jackOfClubs));
            Game newGame = new Game(toak, straight);
            assertEquals(Optional.of(straight), newGame.getWinner());
        }

        @Test
        void testStraightVsFlush() {
            Hand flush =
                    new Hand(
                            List.of(
                                    tenOfDiamonds,
                                    eightOfDiamonds,
                                    fiveOfDiamonds,
                                    sevenOfDiamonds,
                                    twoOfDiamonds));
            Hand straight =
                    new Hand(
                            List.of(
                                    sevenOfClubs,
                                    eightOfSpades,
                                    nineOfSpades,
                                    tenOfSpades,
                                    jackOfSpades));
            Game newGame = new Game(flush, straight);
            assertEquals(Optional.of(flush), newGame.getWinner());
        }

        @Test
        void testFlushVsFullHouse() {
            Hand flush =
                    new Hand(
                            List.of(
                                    tenOfDiamonds,
                                    eightOfDiamonds,
                                    fiveOfDiamonds,
                                    sevenOfDiamonds,
                                    twoOfDiamonds));
            Hand fullHouse =
                    new Hand(
                            List.of(
                                    nineOfClubs,
                                    nineOfHearts,
                                    nineOfSpades,
                                    twoOfSpades,
                                    twoOfHearts));
            Game newGame = new Game(flush, fullHouse);
            assertEquals(Optional.of(fullHouse), newGame.getWinner());
        }

        @Test
        void testFullHouseVsFOAK() {
            Hand fullHouse =
                    new Hand(
                            List.of(
                                    nineOfClubs,
                                    nineOfHearts,
                                    nineOfSpades,
                                    twoOfSpades,
                                    twoOfDiamonds));
            Hand foak =
                    new Hand(
                            List.of(
                                    sevenOfDiamonds,
                                    sevenOfClubs,
                                    sevenOfHearts,
                                    sevenOfSpades,
                                    jackOfClubs));
            Game newGame = new Game(fullHouse, foak);
            assertEquals(Optional.of(foak), newGame.getWinner());
        }

        @Test
        void testFOAKVsStraightFlush() {
            Hand foak =
                    new Hand(
                            List.of(
                                    sevenOfDiamonds,
                                    sevenOfClubs,
                                    sevenOfHearts,
                                    sevenOfSpades,
                                    jackOfClubs));
            Hand straightFlush =
                    new Hand(
                            List.of(
                                    eightOfSpades,
                                    nineOfSpades,
                                    tenOfSpades,
                                    jackOfSpades,
                                    queenOfSpades));
            Game newGame = new Game(foak, straightFlush);
            assertEquals(Optional.of(straightFlush), newGame.getWinner());
        }

        @Test
        void testTOAKVsFOAK() {
            Hand toak =
                    new Hand(
                            List.of(
                                    sevenOfDiamonds,
                                    sevenOfClubs,
                                    sevenOfHearts,
                                    aceOfDiamonds,
                                    jackOfClubs));
            Hand foak =
                    new Hand(
                            List.of(
                                    eightOfDiamonds,
                                    eightOfClubs,
                                    eightOfHearts,
                                    eightOfSpades,
                                    aceOfHearts));
            Game newGame = new Game(toak, foak);
            assertEquals(Optional.of(foak), newGame.getWinner());
        }
    }

    @Nested
    class TestGameWithoutWinner {

        @Test
        void testHighCard() {
            Hand highCard =
                    new Hand(
                            List.of(
                                    twoOfSpades,
                                    eightOfSpades,
                                    aceOfDiamonds,
                                    jackOfClubs,
                                    queenOfHearts));
            Hand otherHighCard =
                    new Hand(
                            List.of(
                                    twoOfHearts,
                                    eightOfDiamonds,
                                    aceOfHearts,
                                    jackOfSpades,
                                    queenOfClubs));
            Game newGame = new Game(highCard, otherHighCard);
            assertEquals(Optional.empty(), newGame.getWinner());
        }

        @Test
        void testPair() {
            Hand pair =
                    new Hand(
                            List.of(
                                    eightOfClubs,
                                    eightOfSpades,
                                    aceOfDiamonds,
                                    jackOfClubs,
                                    queenOfHearts));

            Hand otherPair =
                    new Hand(
                            List.of(
                                    eightOfDiamonds,
                                    eightOfHearts,
                                    aceOfSpades,
                                    jackOfSpades,
                                    queenOfSpades));
            Game newGame = new Game(pair, otherPair);
            assertEquals(Optional.empty(), newGame.getWinner());
        }

        @Test
        void testTwoPair() {
            Hand twoPair =
                    new Hand(
                            List.of(
                                    eightOfClubs,
                                    eightOfSpades,
                                    nineOfSpades,
                                    nineOfDiamonds,
                                    aceOfDiamonds));
            Hand otherTwoPair =
                    new Hand(
                            List.of(
                                    eightOfHearts,
                                    eightOfDiamonds,
                                    nineOfClubs,
                                    nineOfHearts,
                                    aceOfHearts));

            Game newGame = new Game(twoPair, otherTwoPair);
            assertEquals(Optional.empty(), newGame.getWinner());
        }

        @Test
        void testStraight() {
            Hand straight =
                    new Hand(
                            List.of(
                                    sevenOfDiamonds,
                                    nineOfSpades,
                                    eightOfClubs,
                                    tenOfDiamonds,
                                    jackOfClubs));
            Hand otherStraight =
                    new Hand(
                            List.of(
                                    sevenOfSpades,
                                    nineOfClubs,
                                    eightOfHearts,
                                    tenOfSpades,
                                    jackOfSpades));

            Game newGame = new Game(straight, otherStraight);
            assertEquals(Optional.empty(), newGame.getWinner());
        }

        @Test
        void testFlush() {
            Hand flush =
                    new Hand(
                            List.of(
                                    tenOfDiamonds,
                                    eightOfDiamonds,
                                    fiveOfDiamonds,
                                    sevenOfDiamonds,
                                    twoOfDiamonds));
            Hand otherFlush =
                    new Hand(
                            List.of(
                                    tenOfSpades,
                                    eightOfSpades,
                                    fiveOfSpades,
                                    sevenOfSpades,
                                    twoOfSpades));
            Game newGame = new Game(flush, otherFlush);
            assertEquals(Optional.empty(), newGame.getWinner());
        }

        @Test
        void testStraightFlush() {
            Hand straightFlush =
                    new Hand(
                            List.of(
                                    sevenOfSpades,
                                    eightOfSpades,
                                    nineOfSpades,
                                    tenOfSpades,
                                    jackOfSpades));

            Hand otherStraightFlush =
                    new Hand(
                            List.of(
                                    sevenOfHearts,
                                    eightOfHearts,
                                    nineOfHearts,
                                    tenOfHearts,
                                    jackOfHearts));
            Game newGame = new Game(straightFlush, otherStraightFlush);
            assertEquals(Optional.empty(), newGame.getWinner());
        }
    }
}
