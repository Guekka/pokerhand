package pokerhand.core;

import java.util.ArrayList;
import java.util.List;

public class CardDeck {

    private final ArrayList<Card> cards;

    public CardDeck() {
        this.cards = new ArrayList<>();
        this.cards.addAll(generateCards());
    }

    public void takeCards(List<Card> cards) {
        // check if all cards are in the deck
        if (!this.cards.containsAll(cards)) {
            throw new IllegalArgumentException("Une ou plusieurs cartes ne sont pas dans le jeu");
        }
        this.cards.removeAll(cards);
    }

    public List<Card> getCards() {
        return cards;
    }

    private ArrayList<Card> generateCards() {
        ArrayList<Card> cardArrayList = new ArrayList<>();
        for (CardValue value : CardValue.values()) {
            for (CardColor color : CardColor.values()) {
                cardArrayList.add(new Card(value, color));
            }
        }
        return cardArrayList;
    }

    public void reset() {
        this.cards.clear();
        this.cards.addAll(generateCards());
    }
}
