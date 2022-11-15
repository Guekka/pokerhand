package pokerhand.core;

import java.util.ArrayList;
import java.util.List;

public class CardDeck {

    private final ArrayList<Card> cards;

    public CardDeck() {
        this.cards = new ArrayList<>();
        for (CardValue value : CardValue.values()) {
            for (CardColor color : CardColor.values()) {
                this.cards.add(new Card(value, color));
            }
        }
    }

    public void takeCards(List<Card> cards) {
        // check if all cards are in the deck
        if (!this.cards.containsAll(cards)) {
            throw new IllegalArgumentException("One or more cards are not in the deck");
        }
        this.cards.removeAll(cards);
    }

    public List<Card> getCards() {
        return cards;
    }

    public void reset() {
        this.cards.clear();
        for (CardValue value : CardValue.values()) {
            for (CardColor color : CardColor.values()) {
                this.cards.add(new Card(value, color));
            }
        }
    }
}
