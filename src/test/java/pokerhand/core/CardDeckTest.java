package pokerhand.core;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class CardDeckTest {
    CardDeck deck;

    @BeforeEach
    void setUp() {
        deck = new CardDeck();
    }

    @Nested
    class TakeCard {
        @Test
        @DisplayName("Test that takeCard removes the card from the deck")
        void test_takeCard_WhenGivenACardThatIsInTheDeck_RemovesTheCardFromTheDeck() {
            Card card = new Card(CardValue.ACE, CardColor.HEART);
            deck.takeCards(List.of(card));
            assertFalse(deck.getCards().contains(card));
        }

        @Test
        @DisplayName("Test that takeCard throws an exception when the card is not in the deck")
        void test_takeCard_WhenGivenACardThatIsNotInTheDeck_ThrowsAnException() {
            ArrayList<Card> cards =
                    new ArrayList<>(List.of(new Card(CardValue.ACE, CardColor.HEART)));
            deck.takeCards(cards);
            assertThrows(IllegalArgumentException.class, () -> deck.takeCards(cards));
        }
    }
}
