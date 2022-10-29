package pokerhand.core;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class HandTest {
    @Test
    void test_toString_WhenGivenDifferentHands_ReturnsCorrectStringRepresentationOfTheHand() {
        assertEquals(
                "[ACE CLUB]",
                new Hand(List.of(new Card(CardValue.ACE, CardColor.CLUB))).toString());
        assertEquals(
                "[KING CLUB]",
                new Hand(List.of(new Card(CardValue.KING, CardColor.CLUB))).toString());
        assertEquals(
                "[QUEEN CLUB]",
                new Hand(List.of(new Card(CardValue.QUEEN, CardColor.CLUB))).toString());
        assertEquals(
                "[JACK CLUB]",
                new Hand(List.of(new Card(CardValue.JACK, CardColor.CLUB))).toString());
        assertEquals(
                "[TEN HEART]",
                new Hand(List.of(new Card(CardValue.TEN, CardColor.HEART))).toString());

        assertEquals(
                "[ACE CLUB, KING CLUB, QUEEN CLUB, JACK CLUB, TEN CLUB]",
                new Hand(
                                List.of(
                                        new Card(CardValue.ACE, CardColor.CLUB),
                                        new Card(CardValue.KING, CardColor.CLUB),
                                        new Card(CardValue.QUEEN, CardColor.CLUB),
                                        new Card(CardValue.JACK, CardColor.CLUB),
                                        new Card(CardValue.TEN, CardColor.CLUB)))
                        .toString());
        assertEquals(
                "[TEN HEART, NINE DIAMOND, EIGHT HEART, SEVEN DIAMOND, SIX SPADE]",
                new Hand(
                                List.of(
                                        new Card(CardValue.TEN, CardColor.HEART),
                                        new Card(CardValue.NINE, CardColor.DIAMOND),
                                        new Card(CardValue.EIGHT, CardColor.HEART),
                                        new Card(CardValue.SEVEN, CardColor.DIAMOND),
                                        new Card(CardValue.SIX, CardColor.SPADE)))
                        .toString());
        assertEquals(
                "[SIX HEART, FIVE SPADE, FOUR CLUB, THREE CLUB, TWO CLUB]",
                new Hand(
                                List.of(
                                        new Card(CardValue.SIX, CardColor.HEART),
                                        new Card(CardValue.FIVE, CardColor.SPADE),
                                        new Card(CardValue.FOUR, CardColor.CLUB),
                                        new Card(CardValue.THREE, CardColor.CLUB),
                                        new Card(CardValue.TWO, CardColor.CLUB)))
                        .toString());
    }

    @Nested
    @DisplayName("Test if the power of a hand is calculated correctly")
    class TestPower {

        @Test
        void testHighCard() {
            var highCard =
                    new Hand(
                            List.of(
                                    new Card(CardValue.ACE, CardColor.SPADE),
                                    new Card(CardValue.NINE, CardColor.CLUB),
                                    new Card(CardValue.THREE, CardColor.DIAMOND),
                                    new Card(CardValue.JACK, CardColor.CLUB),
                                    new Card(CardValue.TEN, CardColor.CLUB)));

            var expectedHandType = HandType.HIGH_CARD;
            var expectedSecondary =
                    List.of(
                            CardValue.ACE,
                            CardValue.JACK,
                            CardValue.TEN,
                            CardValue.NINE,
                            CardValue.THREE);

            assertEquals(new Power(expectedHandType, expectedSecondary), highCard.getPower());
        }

        @Test
        void testFlush() {
            var flush =
                    new Hand(
                            List.of(
                                    new Card(CardValue.ACE, CardColor.CLUB),
                                    new Card(CardValue.NINE, CardColor.CLUB),
                                    new Card(CardValue.THREE, CardColor.CLUB),
                                    new Card(CardValue.JACK, CardColor.CLUB),
                                    new Card(CardValue.TEN, CardColor.CLUB)));

            var expectedHandType = HandType.FLUSH;
            var expectedSecondary =
                    List.of(
                            CardValue.ACE,
                            CardValue.JACK,
                            CardValue.TEN,
                            CardValue.NINE,
                            CardValue.THREE);

            assertEquals(new Power(expectedHandType, expectedSecondary), flush.getPower());
        }

        @Test
        void testPair() {
            var pair =
                    new Hand(
                            List.of(
                                    new Card(CardValue.JACK, CardColor.CLUB),
                                    new Card(CardValue.JACK, CardColor.SPADE),
                                    new Card(CardValue.QUEEN, CardColor.CLUB),
                                    new Card(CardValue.KING, CardColor.CLUB),
                                    new Card(CardValue.ACE, CardColor.DIAMOND)));
            var expectedHandType = HandType.PAIR;
            var expectedSecondary =
                    List.of(CardValue.JACK, CardValue.ACE, CardValue.KING, CardValue.QUEEN);

            assertEquals(new Power(expectedHandType, expectedSecondary), pair.getPower());
        }

        @Test
        void testTwoPair() {
            var twopair =
                    new Hand(
                            List.of(
                                    new Card(CardValue.JACK, CardColor.CLUB),
                                    new Card(CardValue.JACK, CardColor.SPADE),
                                    new Card(CardValue.QUEEN, CardColor.CLUB),
                                    new Card(CardValue.QUEEN, CardColor.CLUB),
                                    new Card(CardValue.ACE, CardColor.DIAMOND)));
            var expectedHandType = HandType.TWO_PAIR;
            var expectedSecondary = List.of(CardValue.QUEEN, CardValue.JACK, CardValue.ACE);

            assertEquals(new Power(expectedHandType, expectedSecondary), twopair.getPower());
        }

        @Test
        void testTOAK() {
            var toak =
                    new Hand(
                            List.of(
                                    new Card(CardValue.JACK, CardColor.CLUB),
                                    new Card(CardValue.JACK, CardColor.CLUB),
                                    new Card(CardValue.JACK, CardColor.SPADE),
                                    new Card(CardValue.KING, CardColor.HEART),
                                    new Card(CardValue.ACE, CardColor.SPADE)));

            var expectedHandType = HandType.THREE_OF_A_KIND;
            var expectedSecondary = List.of(CardValue.JACK, CardValue.ACE, CardValue.KING);

            assertEquals(new Power(expectedHandType, expectedSecondary), toak.getPower());
        }

        @Test
        void testFOAK() {
            var foak =
                    new Hand(
                            List.of(
                                    new Card(CardValue.JACK, CardColor.CLUB),
                                    new Card(CardValue.JACK, CardColor.CLUB),
                                    new Card(CardValue.JACK, CardColor.CLUB),
                                    new Card(CardValue.JACK, CardColor.HEART),
                                    new Card(CardValue.ACE, CardColor.SPADE)));

            var expectedHandType = HandType.FOUR_OF_A_KIND;
            var expectedSecondary = List.of(CardValue.JACK, CardValue.ACE);

            assertEquals(new Power(expectedHandType, expectedSecondary), foak.getPower());
        }

        @Test
        void testStraight() {
            var straight =
                    new Hand(
                            List.of(
                                    new Card(CardValue.TEN, CardColor.CLUB),
                                    new Card(CardValue.NINE, CardColor.CLUB),
                                    new Card(CardValue.EIGHT, CardColor.DIAMOND),
                                    new Card(CardValue.SEVEN, CardColor.SPADE),
                                    new Card(CardValue.SIX, CardColor.HEART)));
            var expectedHandType = HandType.STRAIGHT;
            var expectedSecondary = List.of(CardValue.TEN);
            assertEquals(new Power(expectedHandType, expectedSecondary), straight.getPower());
        }
    }

    @Nested
    @DisplayName("Test from string method")
    class TestFromString {

        private static Stream<Arguments> provideValidHandStrings() {
            return Stream.of(
                    Arguments.of("ATr", new Hand(List.of(new Card(CardValue.ACE, CardColor.CLUB)))),
                    Arguments.of(
                            "KTr", new Hand(List.of(new Card(CardValue.KING, CardColor.CLUB)))),
                    Arguments.of(
                            "QTr", new Hand(List.of(new Card(CardValue.QUEEN, CardColor.CLUB)))),
                    Arguments.of(
                            "JPi", new Hand(List.of(new Card(CardValue.JACK, CardColor.SPADE)))),
                    Arguments.of(
                            "10Pi", new Hand(List.of(new Card(CardValue.TEN, CardColor.SPADE)))),
                    Arguments.of(
                            "9Pi", new Hand(List.of(new Card(CardValue.NINE, CardColor.SPADE)))),
                    Arguments.of(
                            "8Co", new Hand(List.of(new Card(CardValue.EIGHT, CardColor.HEART)))),
                    Arguments.of(
                            "7Co", new Hand(List.of(new Card(CardValue.SEVEN, CardColor.HEART)))),
                    Arguments.of(
                            "6Co", new Hand(List.of(new Card(CardValue.SIX, CardColor.HEART)))),
                    Arguments.of(
                            "5Ca", new Hand(List.of(new Card(CardValue.FIVE, CardColor.DIAMOND)))),
                    Arguments.of(
                            "4Ca", new Hand(List.of(new Card(CardValue.FOUR, CardColor.DIAMOND)))),
                    Arguments.of(
                            "3Ca", new Hand(List.of(new Card(CardValue.THREE, CardColor.DIAMOND)))),
                    Arguments.of(
                            "2Ca", new Hand(List.of(new Card(CardValue.TWO, CardColor.DIAMOND)))),
                    Arguments.arguments(
                            "ATr KCa QPi JCo 10Tr",
                            new Hand(
                                    List.of(
                                            new Card(CardValue.ACE, CardColor.CLUB),
                                            new Card(CardValue.KING, CardColor.DIAMOND),
                                            new Card(CardValue.QUEEN, CardColor.SPADE),
                                            new Card(CardValue.JACK, CardColor.HEART),
                                            new Card(CardValue.TEN, CardColor.CLUB)))),
                    Arguments.arguments(
                            "KTr QCa JPi 10Co 9Tr",
                            new Hand(
                                    List.of(
                                            new Card(CardValue.KING, CardColor.CLUB),
                                            new Card(CardValue.QUEEN, CardColor.DIAMOND),
                                            new Card(CardValue.JACK, CardColor.SPADE),
                                            new Card(CardValue.TEN, CardColor.HEART),
                                            new Card(CardValue.NINE, CardColor.CLUB)))),
                    Arguments.arguments(
                            "QTr JCa 10Pi 9Co 8Tr",
                            new Hand(
                                    List.of(
                                            new Card(CardValue.QUEEN, CardColor.CLUB),
                                            new Card(CardValue.JACK, CardColor.DIAMOND),
                                            new Card(CardValue.TEN, CardColor.SPADE),
                                            new Card(CardValue.NINE, CardColor.HEART),
                                            new Card(CardValue.EIGHT, CardColor.CLUB)))),
                    Arguments.arguments(
                            "JTr 10Ca 9Pi 8Co 7Tr",
                            new Hand(
                                    List.of(
                                            new Card(CardValue.JACK, CardColor.CLUB),
                                            new Card(CardValue.TEN, CardColor.DIAMOND),
                                            new Card(CardValue.NINE, CardColor.SPADE),
                                            new Card(CardValue.EIGHT, CardColor.HEART),
                                            new Card(CardValue.SEVEN, CardColor.CLUB)))),
                    Arguments.arguments(
                            "10Tr 9Ca 8Pi 7Co 6Tr",
                            new Hand(
                                    List.of(
                                            new Card(CardValue.TEN, CardColor.CLUB),
                                            new Card(CardValue.NINE, CardColor.DIAMOND),
                                            new Card(CardValue.EIGHT, CardColor.SPADE),
                                            new Card(CardValue.SEVEN, CardColor.HEART),
                                            new Card(CardValue.SIX, CardColor.CLUB)))),
                    Arguments.arguments(
                            "9Tr 8Ca 7Pi 6Co 5Tr",
                            new Hand(
                                    List.of(
                                            new Card(CardValue.NINE, CardColor.CLUB),
                                            new Card(CardValue.EIGHT, CardColor.DIAMOND),
                                            new Card(CardValue.SEVEN, CardColor.SPADE),
                                            new Card(CardValue.SIX, CardColor.HEART),
                                            new Card(CardValue.FIVE, CardColor.CLUB)))),
                    Arguments.arguments(
                            "8Tr 7Ca 6Pi 5Co 4Tr",
                            new Hand(
                                    List.of(
                                            new Card(CardValue.EIGHT, CardColor.CLUB),
                                            new Card(CardValue.SEVEN, CardColor.DIAMOND),
                                            new Card(CardValue.SIX, CardColor.SPADE),
                                            new Card(CardValue.FIVE, CardColor.HEART),
                                            new Card(CardValue.FOUR, CardColor.CLUB)))),
                    Arguments.arguments(
                            "7Tr 6Ca 5Pi 4Co 3Tr",
                            new Hand(
                                    List.of(
                                            new Card(CardValue.SEVEN, CardColor.CLUB),
                                            new Card(CardValue.SIX, CardColor.DIAMOND),
                                            new Card(CardValue.FIVE, CardColor.SPADE),
                                            new Card(CardValue.FOUR, CardColor.HEART),
                                            new Card(CardValue.THREE, CardColor.CLUB)))),
                    Arguments.arguments(
                            "6Tr 5Ca 4Pi 3Co 2Tr",
                            new Hand(
                                    List.of(
                                            new Card(CardValue.SIX, CardColor.CLUB),
                                            new Card(CardValue.FIVE, CardColor.DIAMOND),
                                            new Card(CardValue.FOUR, CardColor.SPADE),
                                            new Card(CardValue.THREE, CardColor.HEART),
                                            new Card(CardValue.TWO, CardColor.CLUB)))));
        }

        private static Stream<Arguments> provideInvalidHandStrings() {
            return Stream.of(
                    Arguments.of("A21"),
                    Arguments.of("123dc"),
                    Arguments.of("11"),
                    Arguments.of("12"),
                    Arguments.of(""),
                    Arguments.of(" "),
                    Arguments.of("  "),
                    Arguments.of("\n"),
                    Arguments.of("\t"),
                    Arguments.of("1"),
                    Arguments.of("0"),
                    Arguments.of("13"),
                    Arguments.of("Z"),
                    Arguments.of("A K Q J 99"),
                    Arguments.of("A K aj J 10"),
                    Arguments.of("9i K Q J 10"),
                    Arguments.of("8 2 3 1 9"));
        }

        @ParameterizedTest
        @DisplayName("Test valid hand strings")
        @MethodSource("provideValidHandStrings")
        void test_fromString_WhenGivenValidString_ReturnsCorrectHand(
                String handString, Hand expectedHand) {
            assertEquals(expectedHand, Hand.fromString(handString));
        }

        @ParameterizedTest
        @DisplayName("Test invalid hand strings")
        @MethodSource("provideInvalidHandStrings")
        void test_fromString_WhenGivenInvalidString_ThrowsIllegalArgumentException(
                String handString) {
            assertThrows(IllegalArgumentException.class, () -> Hand.fromString(handString));
        }
    }

    @Nested
    @DisplayName("Test comparison between two hands")
    class TestComparison {
        @Nested
        @DisplayName("Test the equals method")
        class TestEquals {
            @Test
            void test_equals_WhenGivenEqualHands_ReturnsTrue() {
                Hand hand1 = new Hand(List.of(new Card(CardValue.ACE, CardColor.CLUB)));
                Hand hand2 = new Hand(List.of(new Card(CardValue.ACE, CardColor.CLUB)));
                assertEquals(hand1, hand2);

                Hand hand3 =
                        new Hand(
                                List.of(
                                        new Card(CardValue.ACE, CardColor.CLUB),
                                        new Card(CardValue.KING, CardColor.CLUB),
                                        new Card(CardValue.QUEEN, CardColor.CLUB),
                                        new Card(CardValue.JACK, CardColor.CLUB),
                                        new Card(CardValue.TEN, CardColor.CLUB)));
                Hand hand4 =
                        new Hand(
                                List.of(
                                        new Card(CardValue.ACE, CardColor.CLUB),
                                        new Card(CardValue.KING, CardColor.CLUB),
                                        new Card(CardValue.QUEEN, CardColor.CLUB),
                                        new Card(CardValue.JACK, CardColor.CLUB),
                                        new Card(CardValue.TEN, CardColor.CLUB)));
                assertEquals(hand3, hand4);
            }

            @Test
            void test_equals_WhenGivenDifferentObjects_ReturnsFalse() {
                Hand hand1 = new Hand(List.of(new Card(CardValue.ACE, CardColor.CLUB)));
                assertNotEquals(hand1, new Object());
            }

            @Test
            void test_equals_WhenGivenDifferentHandTypes_ReturnsFalse() {
                Hand hand1 = new Hand(List.of(new Card(CardValue.ACE, CardColor.CLUB)));
                Hand hand2 = new Hand(List.of(new Card(CardValue.KING, CardColor.CLUB)));
                assertNotEquals(hand1, hand2);

                Hand hand3 =
                        new Hand(
                                List.of(
                                        new Card(CardValue.ACE, CardColor.CLUB),
                                        new Card(CardValue.KING, CardColor.CLUB),
                                        new Card(CardValue.QUEEN, CardColor.CLUB),
                                        new Card(CardValue.JACK, CardColor.CLUB),
                                        new Card(CardValue.TEN, CardColor.CLUB)));
                Hand hand4 =
                        new Hand(
                                List.of(
                                        new Card(CardValue.ACE, CardColor.CLUB),
                                        new Card(CardValue.KING, CardColor.CLUB),
                                        new Card(CardValue.QUEEN, CardColor.CLUB),
                                        new Card(CardValue.JACK, CardColor.CLUB),
                                        new Card(CardValue.NINE, CardColor.CLUB)));
                assertNotEquals(hand3, hand4);
            }
        }

        @Nested
        @DisplayName("Test the hashcode method")
        class TestHashCode {
            @Test
            void test_hashcode_WhenGivenEqualHands_ReturnsTheSameHashCode() {
                Hand hand1 = new Hand(List.of(new Card(CardValue.ACE, CardColor.CLUB)));
                Hand hand2 = new Hand(List.of(new Card(CardValue.ACE, CardColor.CLUB)));
                assertEquals(hand1.hashCode(), hand2.hashCode());

                Hand hand3 =
                        new Hand(
                                List.of(
                                        new Card(CardValue.ACE, CardColor.CLUB),
                                        new Card(CardValue.KING, CardColor.CLUB),
                                        new Card(CardValue.QUEEN, CardColor.CLUB),
                                        new Card(CardValue.JACK, CardColor.CLUB),
                                        new Card(CardValue.TEN, CardColor.CLUB)));
                Hand hand4 =
                        new Hand(
                                List.of(
                                        new Card(CardValue.ACE, CardColor.CLUB),
                                        new Card(CardValue.KING, CardColor.CLUB),
                                        new Card(CardValue.QUEEN, CardColor.CLUB),
                                        new Card(CardValue.JACK, CardColor.CLUB),
                                        new Card(CardValue.TEN, CardColor.CLUB)));
                assertEquals(hand3.hashCode(), hand4.hashCode());
            }

            @Test
            void test_hashcode_WhenGivenDifferentHandTypes_ReturnsDifferentHashcode() {
                Hand hand1 = new Hand(List.of(new Card(CardValue.ACE, CardColor.CLUB)));
                Hand hand2 = new Hand(List.of(new Card(CardValue.KING, CardColor.CLUB)));
                assertNotEquals(hand1.hashCode(), hand2.hashCode());

                Hand hand3 =
                        new Hand(
                                List.of(
                                        new Card(CardValue.ACE, CardColor.CLUB),
                                        new Card(CardValue.KING, CardColor.CLUB),
                                        new Card(CardValue.QUEEN, CardColor.CLUB),
                                        new Card(CardValue.JACK, CardColor.CLUB),
                                        new Card(CardValue.TEN, CardColor.CLUB)));
                Hand hand4 =
                        new Hand(
                                List.of(
                                        new Card(CardValue.ACE, CardColor.CLUB),
                                        new Card(CardValue.KING, CardColor.CLUB),
                                        new Card(CardValue.QUEEN, CardColor.CLUB),
                                        new Card(CardValue.JACK, CardColor.CLUB),
                                        new Card(CardValue.NINE, CardColor.CLUB)));
                assertNotEquals(hand3.hashCode(), hand4.hashCode());
            }
        }
    }
}
