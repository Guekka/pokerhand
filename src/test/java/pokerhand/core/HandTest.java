package pokerhand.core;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class HandTest {

    @Nested
    @DisplayName("Tests if the power of a hand with a single card is calculated correctly")
    class TestPowerWithOneCard {
        @Test
        void test_getPower_FromTheSameHand_ReturnsEqualPower() {
            Hand hand = new Hand(List.of(new Card(CardValue.ACE)));
            assertEquals(hand.getPower(), hand.getPower());
        }

        @Test
        void test_getPower_WhenTheFirstHandIsMorePowerfulThanTheSecondHand_ReturnsAHigherPowerForTheFirstHandThanTheSecondHand() {
            Hand hand1 = new Hand(List.of(new Card(CardValue.ACE)));
            Hand hand2 = new Hand(List.of(new Card(CardValue.KING)));
            assertEquals(1, hand1.getPower().compareTo(hand2.getPower()));
        }

        @Test
        void test_getPower_WhenTheFirstHandIsLessPowerfulThanTheSecondHand_ReturnsALowerPowerForTheFirstHandThanTheSecondHand() {
            Hand hand = new Hand(List.of(new Card(CardValue.ACE)));
            Hand hand2 = new Hand(List.of(new Card(CardValue.KING)));
            assertEquals(-1, hand2.getPower().compareTo(hand.getPower()));
        }


    }

    @Nested
    @DisplayName("Test from string method")
    class TestFromString {

        private static Stream<Arguments> provideValidHandStrings() {
            return Stream.of(
                    Arguments.of("A", new Hand(List.of(new Card(CardValue.ACE)))),
                    Arguments.of("K", new Hand(List.of(new Card(CardValue.KING)))),
                    Arguments.of("Q", new Hand(List.of(new Card(CardValue.QUEEN)))),
                    Arguments.of("J", new Hand(List.of(new Card(CardValue.JACK)))),
                    Arguments.of("10", new Hand(List.of(new Card(CardValue.TEN)))),
                    Arguments.of("9", new Hand(List.of(new Card(CardValue.NINE)))),
                    Arguments.of("8", new Hand(List.of(new Card(CardValue.EIGHT)))),
                    Arguments.of("7", new Hand(List.of(new Card(CardValue.SEVEN)))),
                    Arguments.of("6", new Hand(List.of(new Card(CardValue.SIX)))),
                    Arguments.of("5", new Hand(List.of(new Card(CardValue.FIVE)))),
                    Arguments.of("4", new Hand(List.of(new Card(CardValue.FOUR)))),
                    Arguments.of("3", new Hand(List.of(new Card(CardValue.THREE)))),
                    Arguments.of("2", new Hand(List.of(new Card(CardValue.TWO))))
            );
        }

        @ParameterizedTest
        @DisplayName("Test valid hand strings")
        @MethodSource("provideValidHandStrings")
        void test_fromString_WhenGivenValidString_ReturnsCorrectHand(String handString, Hand expectedHand) {
            assertEquals(expectedHand, Hand.fromString(handString));
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
                    Arguments.of("Z")
            );
        }

        @ParameterizedTest
        @DisplayName("Test invalid hand strings")
        @MethodSource("provideInvalidHandStrings")
        void test_fromString_WhenGivenInvalidString_ThrowsIllegalArgumentException(String handString) {
            assertThrows(IllegalArgumentException.class, () -> Hand.fromString(handString));
        }
    }

    @Nested
    @DisplayName("Test comparaison between two hands with one card")
    class TestComparisonOneCard {
        @Nested
        @DisplayName("Test the equals method")
        class TestEquals {
            @Test
            void test_equals_WhenGivenEqualHands_ReturnsTrue() {
                Hand hand1 = new Hand(List.of(new Card(CardValue.ACE)));
                Hand hand2 = new Hand(List.of(new Card(CardValue.ACE)));
                assertEquals(hand1, hand2);
            }

            @Test
            void test_equals_WhenGivenDifferentObjects_ReturnsFalse() {
                Hand hand1 = new Hand(List.of(new Card(CardValue.ACE)));
                assertNotEquals(hand1, new Object());
            }

            @Test
            void test_equals_WhenGivenDifferentHandTypes_ReturnsFalse() {
                Hand hand1 = new Hand(List.of(new Card(CardValue.ACE)));
                Hand hand2 = new Hand(List.of(new Card(CardValue.KING)));
                assertNotEquals(hand1, hand2);
            }
        }
        

        @Nested
        @DisplayName("Test the hashcode method")
        class TestHashCode {
            @Test
            void test_hashcode_WhenGivenEqualHands_ReturnsTheSameHashCode() {
                Hand hand1 = new Hand(List.of(new Card(CardValue.ACE)));
                Hand hand2 = new Hand(List.of(new Card(CardValue.ACE)));
                assertEquals(hand1.hashCode(), hand2.hashCode());
            }

            @Test
            void test_hashcode_WhenGivenDifferentHandTypes_ReturnsDifferentHashcode() {
                Hand hand1 = new Hand(List.of(new Card(CardValue.ACE)));
                Hand hand2 = new Hand(List.of(new Card(CardValue.KING)));
                assertNotEquals(hand1.hashCode(), hand2.hashCode());
            }
        }

    }

    @Test
    void test_toString_WhenGivenDifferentHands_ReturnsCorrectStringRepresentationOfTheHand() {
        assertEquals("[ACE]", new Hand(List.of(new Card(CardValue.ACE))).toString());
        assertEquals("[KING]", new Hand(List.of(new Card(CardValue.KING))).toString());
        assertEquals("[QUEEN]", new Hand(List.of(new Card(CardValue.QUEEN))).toString());
        assertEquals("[JACK]", new Hand(List.of(new Card(CardValue.JACK))).toString());
        assertEquals("[TEN]", new Hand(List.of(new Card(CardValue.TEN))).toString());
    }
}