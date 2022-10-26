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
    void testEquals() {
        var card = new Card(CardValue.ACE, CardColor.DIAMOND);
        var expected = new Card(CardValue.ACE, CardColor.DIAMOND);
        assertEquals(card, expected);
        var notExpectedVal = new Card(CardValue.JACK, CardColor.DIAMOND);
        var notExpectedColor = new Card(CardValue.ACE, CardColor.HEART);
        assertNotEquals(notExpectedVal, card);
        assertNotEquals(notExpectedColor, card);

        assertNotEquals(card, new Object());
    }

    @Test
    void testToString() {
        var card = new Card(CardValue.ACE, CardColor.DIAMOND);
        assertEquals("ACE DIAMOND", card.toString());
    }

    @Nested
    @DisplayName("Test fromString")
    class TestFromString {
        private static Stream<Arguments> provideValidCardStrings() {
            return Stream.of(
                    Arguments.of("ATr", new Card(CardValue.ACE, CardColor.CLUB)),
                    Arguments.of("KTr", new Card(CardValue.KING, CardColor.CLUB)),
                    Arguments.of("QTr", new Card(CardValue.QUEEN, CardColor.CLUB)),
                    Arguments.of("JCo", new Card(CardValue.JACK, CardColor.HEART)),
                    Arguments.of("10Co", new Card(CardValue.TEN, CardColor.HEART)),
                    Arguments.of("9Co", new Card(CardValue.NINE, CardColor.HEART)),
                    Arguments.of("8Ca", new Card(CardValue.EIGHT, CardColor.DIAMOND)),
                    Arguments.of("7Ca", new Card(CardValue.SEVEN, CardColor.DIAMOND)),
                    Arguments.of("6Ca", new Card(CardValue.SIX, CardColor.DIAMOND)),
                    Arguments.of("5Pi", new Card(CardValue.FIVE, CardColor.SPADE)),
                    Arguments.of("4Pi", new Card(CardValue.FOUR, CardColor.SPADE)),
                    Arguments.of("3Pi", new Card(CardValue.THREE, CardColor.SPADE)),
                    Arguments.of("2Pi", new Card(CardValue.TWO, CardColor.SPADE))
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
                    Arguments.of("Z"),
                    Arguments.of("2"),
                    Arguments.of("A")
            );
        }

        @ParameterizedTest
        @DisplayName("Test valid card strings")
        @MethodSource("provideValidCardStrings")
        void testValidCard(String cardString, Card expectedCard) {
            assertEquals(expectedCard, Card.fromString(cardString));
        }

        @ParameterizedTest
        @DisplayName("Test invalid card strings")
        @MethodSource("provideInvalidCardStrings")
        void testInvalidCard(String cardString) {
            assertThrows(IllegalArgumentException.class, () -> Card.fromString(cardString));
        }
    }

    @Test
    @DisplayName("Test Card.compareTo")
    void testCompareTo() {
        var card1 = new Card(CardValue.ACE, CardColor.CLUB);
        var card2 = new Card(CardValue.KING, CardColor.CLUB);
        var card3 = new Card(CardValue.ACE, CardColor.CLUB);
        var card4 = new Card(CardValue.ACE, CardColor.DIAMOND);
        assertTrue(card1.compareTo(card2) > 0);
        assertTrue(card2.compareTo(card1) < 0);
        assertEquals(0, card1.compareTo(card3));
        assertTrue(card1.compareTo(card4) < 0);
    }
}