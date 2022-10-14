package pokerhand.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @Test
    void value() {
        var card = new Card(CardValue.ACE, CardColor.CLUB);
        assertEquals(CardValue.ACE, card.value());
    }

    @Test
    void testToString() {
        var card = new Card(CardValue.ACE, CardColor.DIAMOND);
        assertEquals("ACE", card.toString());
    }

    @Nested
    @DisplayName("Test from string method")
    class TestFromString {

        private static Stream<Arguments> provideValidCardStrings() {
            return Stream.of(
                    Arguments.of("A", new Card(CardValue.ACE, CardColor.DIAMOND)),
                    Arguments.of("K", new Card(CardValue.KING, CardColor.DIAMOND)),
                    Arguments.of("Q", new Card(CardValue.QUEEN, CardColor.DIAMOND)),
                    Arguments.of("J", new Card(CardValue.JACK, CardColor.DIAMOND)),
                    Arguments.of("10", new Card(CardValue.TEN, CardColor.DIAMOND)),
                    Arguments.of("9", new Card(CardValue.NINE, CardColor.DIAMOND)),
                    Arguments.of("8", new Card(CardValue.EIGHT, CardColor.CLUB)),
                    Arguments.of("7", new Card(CardValue.SEVEN, CardColor.CLUB)),
                    Arguments.of("6", new Card(CardValue.SIX, CardColor.CLUB)),
                    Arguments.of("5", new Card(CardValue.FIVE, CardColor.CLUB)),
                    Arguments.of("4", new Card(CardValue.FOUR, CardColor.CLUB)),
                    Arguments.of("3", new Card(CardValue.THREE, CardColor.CLUB)),
                    Arguments.of("2", new Card(CardValue.TWO, CardColor.CLUB))
            );
        }

        private static Stream<Arguments> provideInvalidCardStrings() {
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
        @DisplayName("Test valid card strings")
        @MethodSource("provideValidCardStrings")
        void testValidCard(String cardString, Card expectedCard) {
            assertEquals(expectedCard, Card.fromString(cardString));
        }

        @Test
        @DisplayName("Test of Card comparison")
        void testCompareTo() {
            var card1 = new Card(CardValue.ACE, CardColor.CLUB);
            var card2 = new Card(CardValue.KING, CardColor.CLUB);
            var card3 = new Card(CardValue.ACE, CardColor.CLUB);
            assertTrue(card1.compareTo(card2) > 0);
            assertTrue(card2.compareTo(card1) < 0);
            assertEquals(0, card1.compareTo(card3));
        }

        @ParameterizedTest
        @DisplayName("Test invalid card strings")
        @MethodSource("provideInvalidCardStrings")
        void testInvalidCard(String cardString) {
            assertThrows(IllegalArgumentException.class, () -> Card.fromString(cardString));
        }
    }

}