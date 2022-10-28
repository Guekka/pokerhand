package pokerhand.core;

import java.util.*;
import java.util.stream.Stream;

public class Hand {
    private final List<Card> cards;
    private final EnumMap<CardValue, Integer> valueCount = new EnumMap<>(CardValue.class);

    public Hand(List<Card> cards) {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException("Hand must contain at least one card");
        }
        this.cards = cards;
        this.calculateValueCount();
    }

    private void calculateValueCount() {
        for (Card card : cards) {
            valueCount.put(card.value(), valueCount.getOrDefault(card.value(), 0) + 1);
        }
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
    public int hashCode() {
        return Objects.hash(cards);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hand hand = (Hand) o;
        return Objects.equals(cards, hand.cards);
    }

    @Override
    public String toString() {
        return cards.toString();
    }

    public Power getPower() {
        HandType handType = calculateHandType();
        return new Power(handType, calculateSecondary(handType));
    }

    /** Calculates the highest hand type of this hand */
    public HandType calculateHandType() {
        if (isStraightFlush()) {
            return HandType.STRAIGHT_FLUSH;
        }
        if (isFourOfAKind()) {
            return HandType.FOUR_OF_A_KIND;
        }
        if (isFullHouse()) {
            return HandType.FULL_HOUSE;
        }
        if (isFlush()) {
            return HandType.FLUSH;
        }
        if (isStraight()) {
            return HandType.STRAIGHT;
        }
        if (isThreeOfAKind()) {
            return HandType.THREE_OF_A_KIND;
        }
        if (isTwoPair()) {
            return HandType.TWO_PAIR;
        }
        if (isPair()) {
            return HandType.PAIR;
        }
        return HandType.HIGH_CARD;
    }

    /**
     * Calculates the secondary power of a hand. For HIGH_CARD, this is done by reverse sorting the
     * cards
     */
    private List<CardValue> calculateSecondary(HandType handType) {
        return switch (handType) {
            case HIGH_CARD, FLUSH -> this.cards.stream()
                    .sorted(Collections.reverseOrder())
                    .map(Card::value)
                    .toList();
            case STRAIGHT, STRAIGHT_FLUSH -> List.of(
                    this.cards.stream().max(Card::compareTo).map(Card::value).orElseThrow());

            case PAIR -> sameCardHands(2);

            case THREE_OF_A_KIND -> sameCardHands(3);

            case FOUR_OF_A_KIND -> sameCardHands(4);

            default -> throw new UnsupportedOperationException("Not implemented yet");
        };
    }

    private boolean isStraightFlush() {
        return isStraight() && isFlush();
    }

    private boolean isFourOfAKind() {
        return valueCount.containsValue(4);
    }

    private boolean isFullHouse() {
        return isThreeOfAKind() && isPair();
    }

    private boolean isFlush() {
        CardColor possibleColor = cards.get(0).color();
        return this.cards.stream().allMatch(card -> card.color() == possibleColor);
    }

    private boolean isStraight() {
        List<Card> sortedCards = this.cards.stream().distinct().sorted().toList();
        if (sortedCards.size() != 5) {
            return false;
        }
        for (int i = 0; i < sortedCards.size() - 1; i++) {
            if (!sortedCards.get(i).isPreviousTo(sortedCards.get(i + 1))) {
                return false;
            }
        }
        return true;
    }

    private boolean isThreeOfAKind() {
        return valueCount.containsValue(3);
    }

    private boolean isTwoPair() {
        return false;
    }

    private boolean isPair() {
        return valueCount.containsValue(2);
    }

    /**
     * method to get the duplicate cards in the Hand, then the other values sorted
     *
     * @param numberOfCards the number of identical cards in the hand
     */
    private List<CardValue> sameCardHands(int numberOfCards) {
        CardValue duplicate =
                this.cards.stream()
                        .map(Card::value)
                        .distinct()
                        .filter(
                                value ->
                                        this.cards.stream()
                                                        .filter(card -> card.value().equals(value))
                                                        .count()
                                                == numberOfCards)
                        .findFirst()
                        .orElseThrow();
        List<CardValue> rest =
                this.cards.stream()
                        .filter(card -> !card.value().equals(duplicate))
                        .sorted(Collections.reverseOrder())
                        .map(Card::value)
                        .toList();
        return Stream.concat(Stream.of(duplicate), rest.stream()).toList();
    }
}
