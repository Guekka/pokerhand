package pokerhand.core;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Hand {
    private final List<Card> cards;

    public Hand(List<Card> cards) {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException("Hand must contain at least one card");
        }
        this.cards = cards;
    }

    public static Hand fromString(String value) {
        var cards = Arrays.stream(value.split(" "))
                .map(Card::fromString)
                .toList();
        return new Hand(cards);
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

    private List<CardValue> calculateSecondary(HandType handType) {
        if (handType == HandType.HIGH_CARD) {
            return cards.stream()
                    .sorted(Collections.reverseOrder())
                    .map(Card::value)
                    .toList();
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
