package pokerhand.core;

import java.util.ArrayList;
import java.util.Objects;

public class Hand {
    private final ArrayList<Card> cards;

    public Hand(Card card1) {
        this.cards = new ArrayList<>();
        this.cards.add(card1);
    }

    public static Hand fromString(String value) {
        return new Hand(Card.fromString(value));

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hand hand = (Hand) o;
        return Objects.equals(cards, hand.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards);
    }

    private HandType calculateHandType() {
        return HandType.HIGH_CARD;
    }

    private ArrayList<CardValue> calculateSecondary(HandType handType) {
        if (handType == HandType.HIGH_CARD) {
            var ret = new ArrayList<CardValue>();
            ret.add(cards.get(0).value());
            return ret;
        }
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Power getPower() {
        HandType handType = calculateHandType();
        return new Power(handType, calculateSecondary(handType));
    }

    @Override
    public String toString() {
        return cards.toString();
    }
}
