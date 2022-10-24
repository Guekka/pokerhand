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

    /**
     * Creates a Hand from a string.
     *
     * @param value Expected format: "A 10 2"
     */
    public static Hand fromString(String value) {
        var cards = Arrays.stream(value.split(" ")).map(Card::fromString).toList();
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

    /**
     * Calculates the highest hand type of this hand
     */
    private HandType calculateHandType() {
        if (isFlush()) return HandType.FLUSH;

        return HandType.HIGH_CARD;
    }

    /**
     * Calculates the secondary power of a hand.
     * For HIGH_CARD, this is done by reverse sorting the cards
     */
    private List<CardValue> calculateSecondary(HandType handType) {
        if (handType == HandType.HIGH_CARD) {
            return cards.stream().sorted(Collections.reverseOrder()).map(Card::value).toList();
        }
        if (handType == HandType.FLUSH) {
            return cards.stream().sorted().map(Card::value).toList();
        }

        throw new UnsupportedOperationException("Not implemented yet");

    }

    public boolean isFlush() {
        CardColor possibleColor = cards.get(0).color();
        return this.cards.stream().allMatch(card -> card.color() == possibleColor);
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
