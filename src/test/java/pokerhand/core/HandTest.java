package pokerhand.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class HandTest {

    @Test
    void test_toString_WhenGivenDifferentHands_ReturnsCorrectStringRepresentationOfTheHand() {
        assertEquals("[ACE]", new Hand(List.of(new Card(CardValue.ACE, CardColor.CLUB))).toString());
        assertEquals("[KING]", new Hand(List.of(new Card(CardValue.KING, CardColor.CLUB))).toString());
        assertEquals("[QUEEN]", new Hand(List.of(new Card(CardValue.QUEEN, CardColor.CLUB))).toString());
        assertEquals("[JACK]", new Hand(List.of(new Card(CardValue.JACK, CardColor.CLUB))).toString());
        assertEquals("[TEN]", new Hand(List.of(new Card(CardValue.TEN, CardColor.CLUB))).toString());

        assertEquals("[ACE, KING, QUEEN, JACK, TEN]", new Hand(List.of(new Card(CardValue.ACE, CardColor.CLUB), new Card(CardValue.KING, CardColor.CLUB), new Card(CardValue.QUEEN, CardColor.CLUB), new Card(CardValue.JACK, CardColor.CLUB), new Card(CardValue.TEN, CardColor.CLUB))).toString());
        assertEquals("[TEN, NINE, EIGHT, SEVEN, SIX]", new Hand(List.of(new Card(CardValue.TEN, CardColor.CLUB), new Card(CardValue.NINE, CardColor.CLUB), new Card(CardValue.EIGHT, CardColor.CLUB), new Card(CardValue.SEVEN, CardColor.CLUB), new Card(CardValue.SIX, CardColor.CLUB))).toString());
        assertEquals("[SIX, FIVE, FOUR, THREE, TWO]", new Hand(List.of(new Card(CardValue.SIX, CardColor.CLUB), new Card(CardValue.FIVE, CardColor.CLUB), new Card(CardValue.FOUR, CardColor.CLUB), new Card(CardValue.THREE, CardColor.CLUB), new Card(CardValue.TWO, CardColor.CLUB))).toString());
    }

    @Nested
    @DisplayName("Tests if the power of a hand is calculated correctly")
    class TestPower {
        @Test
        void test_getPower_FromTheSameHand_ReturnsEqualPower() {
            Hand hand1 = new Hand(List.of(new Card(CardValue.ACE, CardColor.CLUB)));
            assertEquals(hand1.getPower(), hand1.getPower());

            Hand hand2 = new Hand(List.of(new Card(CardValue.ACE, CardColor.CLUB), new Card(CardValue.KING, CardColor.CLUB), new Card(CardValue.QUEEN, CardColor.CLUB), new Card(CardValue.JACK, CardColor.CLUB), new Card(CardValue.TEN, CardColor.CLUB)));
            assertEquals(hand2.getPower(), hand2.getPower());
        }

        @Test
        void test_getPower_WhenTheFirstHandIsMorePowerfulThanTheSecondHand_ReturnsAHigherPowerForTheFirstHandThanTheSecondHand() {
            Hand hand1 = new Hand(List.of(new Card(CardValue.ACE, CardColor.CLUB)));
            Hand hand2 = new Hand(List.of(new Card(CardValue.KING, CardColor.CLUB)));
            assertEquals(1, hand1.getPower().compareTo(hand2.getPower()));

            Hand hand3 = new Hand(List.of(new Card(CardValue.ACE, CardColor.CLUB), new Card(CardValue.KING, CardColor.CLUB), new Card(CardValue.QUEEN, CardColor.CLUB), new Card(CardValue.JACK, CardColor.CLUB), new Card(CardValue.TEN, CardColor.CLUB)));
            Hand hand4 = new Hand(List.of(new Card(CardValue.ACE, CardColor.CLUB), new Card(CardValue.KING, CardColor.CLUB), new Card(CardValue.QUEEN, CardColor.CLUB), new Card(CardValue.JACK, CardColor.CLUB), new Card(CardValue.NINE, CardColor.CLUB)));
            assertEquals(1, hand3.getPower().compareTo(hand4.getPower()));
        }

        @Test
        void test_getPower_WhenTheFirstHandIsLessPowerfulThanTheSecondHand_ReturnsALowerPowerForTheFirstHandThanTheSecondHand() {
            Hand hand = new Hand(List.of(new Card(CardValue.ACE, CardColor.CLUB)));
            Hand hand2 = new Hand(List.of(new Card(CardValue.KING, CardColor.CLUB)));
            assertEquals(-1, hand2.getPower().compareTo(hand.getPower()));

            Hand hand3 = new Hand(List.of(new Card(CardValue.ACE, CardColor.CLUB), new Card(CardValue.KING, CardColor.CLUB), new Card(CardValue.QUEEN, CardColor.CLUB), new Card(CardValue.JACK, CardColor.CLUB), new Card(CardValue.NINE, CardColor.CLUB)));
            Hand hand4 = new Hand(List.of(new Card(CardValue.ACE, CardColor.CLUB), new Card(CardValue.KING, CardColor.CLUB), new Card(CardValue.QUEEN, CardColor.CLUB), new Card(CardValue.JACK, CardColor.CLUB), new Card(CardValue.TEN, CardColor.CLUB)));
            assertEquals(-1, hand3.getPower().compareTo(hand4.getPower()));
        }


    }

    @Nested
    @DisplayName("Test from string method")
    class TestFromString {

        private static Stream<Arguments> provideValidHandStrings() {
            return Stream.of(
                    Arguments.of("A", new Hand(List.of(new Card(CardValue.ACE, CardColor.CLUB)))),
                    Arguments.of("K", new Hand(List.of(new Card(CardValue.KING, CardColor.CLUB)))),
                    Arguments.of("Q", new Hand(List.of(new Card(CardValue.QUEEN, CardColor.CLUB)))),
                    Arguments.of("J", new Hand(List.of(new Card(CardValue.JACK, CardColor.CLUB)))),
                    Arguments.of("10", new Hand(List.of(new Card(CardValue.TEN, CardColor.CLUB)))),
                    Arguments.of("9", new Hand(List.of(new Card(CardValue.NINE, CardColor.CLUB)))),
                    Arguments.of("8", new Hand(List.of(new Card(CardValue.EIGHT, CardColor.CLUB)))),
                    Arguments.of("7", new Hand(List.of(new Card(CardValue.SEVEN, CardColor.CLUB)))),
                    Arguments.of("6", new Hand(List.of(new Card(CardValue.SIX, CardColor.CLUB)))),
                    Arguments.of("5", new Hand(List.of(new Card(CardValue.FIVE, CardColor.CLUB)))),
                    Arguments.of("4", new Hand(List.of(new Card(CardValue.FOUR, CardColor.CLUB)))),
                    Arguments.of("3", new Hand(List.of(new Card(CardValue.THREE, CardColor.CLUB)))),
                    Arguments.of("2", new Hand(List.of(new Card(CardValue.TWO, CardColor.CLUB)))),

                    Arguments.arguments("A K Q J 10", new Hand(List.of(new Card(CardValue.ACE, CardColor.CLUB), new Card(CardValue.KING, CardColor.CLUB), new Card(CardValue.QUEEN, CardColor.CLUB), new Card(CardValue.JACK, CardColor.CLUB), new Card(CardValue.TEN, CardColor.CLUB)))),
                    Arguments.arguments("K Q J 10 9", new Hand(List.of(new Card(CardValue.KING, CardColor.CLUB), new Card(CardValue.QUEEN, CardColor.CLUB), new Card(CardValue.JACK, CardColor.CLUB), new Card(CardValue.TEN, CardColor.CLUB), new Card(CardValue.NINE, CardColor.CLUB)))),
                    Arguments.arguments("Q J 10 9 8", new Hand(List.of(new Card(CardValue.QUEEN, CardColor.CLUB), new Card(CardValue.JACK, CardColor.CLUB), new Card(CardValue.TEN, CardColor.CLUB), new Card(CardValue.NINE, CardColor.CLUB), new Card(CardValue.EIGHT, CardColor.CLUB)))),
                    Arguments.arguments("J 10 9 8 7", new Hand(List.of(new Card(CardValue.JACK, CardColor.CLUB), new Card(CardValue.TEN, CardColor.CLUB), new Card(CardValue.NINE, CardColor.CLUB), new Card(CardValue.EIGHT, CardColor.CLUB), new Card(CardValue.SEVEN, CardColor.CLUB)))),
                    Arguments.arguments("10 9 8 7 6", new Hand(List.of(new Card(CardValue.TEN, CardColor.CLUB), new Card(CardValue.NINE, CardColor.CLUB), new Card(CardValue.EIGHT, CardColor.CLUB), new Card(CardValue.SEVEN, CardColor.CLUB), new Card(CardValue.SIX, CardColor.CLUB)))),
                    Arguments.arguments("9 8 7 6 5", new Hand(List.of(new Card(CardValue.NINE, CardColor.CLUB), new Card(CardValue.EIGHT, CardColor.CLUB), new Card(CardValue.SEVEN, CardColor.CLUB), new Card(CardValue.SIX, CardColor.CLUB), new Card(CardValue.FIVE, CardColor.CLUB)))),
                    Arguments.arguments("8 7 6 5 4", new Hand(List.of(new Card(CardValue.EIGHT, CardColor.CLUB), new Card(CardValue.SEVEN, CardColor.CLUB), new Card(CardValue.SIX, CardColor.CLUB), new Card(CardValue.FIVE, CardColor.CLUB), new Card(CardValue.FOUR, CardColor.CLUB)))),
                    Arguments.arguments("7 6 5 4 3", new Hand(List.of(new Card(CardValue.SEVEN, CardColor.CLUB), new Card(CardValue.SIX, CardColor.CLUB), new Card(CardValue.FIVE, CardColor.CLUB), new Card(CardValue.FOUR, CardColor.CLUB), new Card(CardValue.THREE, CardColor.CLUB)))),
                    Arguments.arguments("6 5 4 3 2", new Hand(List.of(new Card(CardValue.SIX, CardColor.CLUB), new Card(CardValue.FIVE, CardColor.CLUB), new Card(CardValue.FOUR, CardColor.CLUB), new Card(CardValue.THREE, CardColor.CLUB), new Card(CardValue.TWO, CardColor.CLUB))))

            );
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
                    Arguments.of("8 2 3 1 9")
            );
        }

        @ParameterizedTest
        @DisplayName("Test valid hand strings")
        @MethodSource("provideValidHandStrings")
        void test_fromString_WhenGivenValidString_ReturnsCorrectHand(String handString, Hand expectedHand) {
            assertEquals(expectedHand, Hand.fromString(handString));
        }

        @ParameterizedTest
        @DisplayName("Test invalid hand strings")
        @MethodSource("provideInvalidHandStrings")
        void test_fromString_WhenGivenInvalidString_ThrowsIllegalArgumentException(String handString) {
            assertThrows(IllegalArgumentException.class, () -> Hand.fromString(handString));
        }
    }

    @Nested
    @DisplayName("Test comparaison between two hands")
    class TestComparison {
        @Nested
        @DisplayName("Test the equals method")
        class TestEquals {
            @Test
            void test_equals_WhenGivenEqualHands_ReturnsTrue() {
                Hand hand1 = new Hand(List.of(new Card(CardValue.ACE, CardColor.CLUB)));
                Hand hand2 = new Hand(List.of(new Card(CardValue.ACE, CardColor.CLUB)));
                assertEquals(hand1, hand2);

                Hand hand3 = new Hand(List.of(new Card(CardValue.ACE, CardColor.CLUB), new Card(CardValue.KING, CardColor.CLUB), new Card(CardValue.QUEEN, CardColor.CLUB), new Card(CardValue.JACK, CardColor.CLUB), new Card(CardValue.TEN, CardColor.CLUB)));
                Hand hand4 = new Hand(List.of(new Card(CardValue.ACE, CardColor.CLUB), new Card(CardValue.KING, CardColor.CLUB), new Card(CardValue.QUEEN, CardColor.CLUB), new Card(CardValue.JACK, CardColor.CLUB), new Card(CardValue.TEN, CardColor.CLUB)));
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

                Hand hand3 = new Hand(List.of(new Card(CardValue.ACE, CardColor.CLUB), new Card(CardValue.KING, CardColor.CLUB), new Card(CardValue.QUEEN, CardColor.CLUB), new Card(CardValue.JACK, CardColor.CLUB), new Card(CardValue.TEN, CardColor.CLUB)));
                Hand hand4 = new Hand(List.of(new Card(CardValue.ACE, CardColor.CLUB), new Card(CardValue.KING, CardColor.CLUB), new Card(CardValue.QUEEN, CardColor.CLUB), new Card(CardValue.JACK, CardColor.CLUB), new Card(CardValue.NINE, CardColor.CLUB)));
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

                Hand hand3 = new Hand(List.of(new Card(CardValue.ACE, CardColor.CLUB), new Card(CardValue.KING, CardColor.CLUB), new Card(CardValue.QUEEN, CardColor.CLUB), new Card(CardValue.JACK, CardColor.CLUB), new Card(CardValue.TEN, CardColor.CLUB)));
                Hand hand4 = new Hand(List.of(new Card(CardValue.ACE, CardColor.CLUB), new Card(CardValue.KING, CardColor.CLUB), new Card(CardValue.QUEEN, CardColor.CLUB), new Card(CardValue.JACK, CardColor.CLUB), new Card(CardValue.TEN, CardColor.CLUB)));
                assertEquals(hand3.hashCode(), hand4.hashCode());
            }

            @Test
            void test_hashcode_WhenGivenDifferentHandTypes_ReturnsDifferentHashcode() {
                Hand hand1 = new Hand(List.of(new Card(CardValue.ACE, CardColor.CLUB)));
                Hand hand2 = new Hand(List.of(new Card(CardValue.KING, CardColor.CLUB)));
                assertNotEquals(hand1.hashCode(), hand2.hashCode());

                Hand hand3 = new Hand(List.of(new Card(CardValue.ACE, CardColor.CLUB), new Card(CardValue.KING, CardColor.CLUB), new Card(CardValue.QUEEN, CardColor.CLUB), new Card(CardValue.JACK, CardColor.CLUB), new Card(CardValue.TEN, CardColor.CLUB)));
                Hand hand4 = new Hand(List.of(new Card(CardValue.ACE, CardColor.CLUB), new Card(CardValue.KING, CardColor.CLUB), new Card(CardValue.QUEEN, CardColor.CLUB), new Card(CardValue.JACK, CardColor.CLUB), new Card(CardValue.NINE, CardColor.CLUB)));
                assertNotEquals(hand3.hashCode(), hand4.hashCode());
            }
        }

    }
}