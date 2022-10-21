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
        var card = new Card(CardValue.ACE);
        assertEquals(CardValue.ACE, card.value());
    }

    @Test
    void testToString() {
        var card = new Card(CardValue.ACE);
        assertEquals("ACE", card.toString());
    }

    @Nested
    @DisplayName("Test from string method")
    class TestFromString {

        private static Stream<Arguments> provideValidCardStrings() {
            return Stream.of(
                    Arguments.of("A", new Card(CardValue.ACE)),
                    Arguments.of("K", new Card(CardValue.KING)),
                    Arguments.of("Q", new Card(CardValue.QUEEN)),
                    Arguments.of("J", new Card(CardValue.JACK)),
                    Arguments.of("10", new Card(CardValue.TEN)),
                    Arguments.of("9", new Card(CardValue.NINE)),
                    Arguments.of("8", new Card(CardValue.EIGHT)),
                    Arguments.of("7", new Card(CardValue.SEVEN)),
                    Arguments.of("6", new Card(CardValue.SIX)),
                    Arguments.of("5", new Card(CardValue.FIVE)),
                    Arguments.of("4", new Card(CardValue.FOUR)),
                    Arguments.of("3", new Card(CardValue.THREE)),
                    Arguments.of("2", new Card(CardValue.TWO))
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
            var card1 = new Card(CardValue.ACE);
            var card2 = new Card(CardValue.KING);
            var card3 = new Card(CardValue.ACE);
            assertTrue(card1.compareTo(card2) > 0);
            assertTrue(card2.compareTo(card1) < 0);
            assertTrue(card1.compareTo(card3) == 0);
        }

        @ParameterizedTest
        @DisplayName("Test invalid card strings")
        @MethodSource("provideInvalidCardStrings")
        void testInvalidCard(String cardString) {
            assertThrows(IllegalArgumentException.class, () -> Card.fromString(cardString));
        }
    }

}