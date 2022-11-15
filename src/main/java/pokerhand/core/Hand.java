package pokerhand.core;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Hand {
    private final List<Card> cards;
    private final EnumMap<CardValue, Integer> valueCount = new EnumMap<>(CardValue.class);
    private final Power power;

    public Hand(List<Card> cards) {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException("Hand must contain at least one card");
        }
        this.cards = cards;
        this.calculateValueCount();
        this.power = this.calculatePower();
    }

    private Power calculatePower() {
        HandType handType = calculateHandType();
        return new Power(handType, calculateSecondary(handType));
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

    private void calculateValueCount() {
        for (Card card : cards) {
            valueCount.put(card.value(), valueCount.getOrDefault(card.value(), 0) + 1);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPower());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hand hand = (Hand) o;
        return Objects.equals(getPower(), hand.getPower());
    }

    @Override
    public String toString() {
        return cards.toString();
    }

    public Power getPower() {
        return power;
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
            case TWO_PAIR -> calculateSecondaryTwoPair();
            case FULL_HOUSE -> sameCardHands(3).subList(0, 2);
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
        int numberOfPair = 0;
        for (Integer i : valueCount.values()) {
            if (i == 2) numberOfPair++;
        }
        return numberOfPair == 2;
    }

    private boolean isPair() {
        return valueCount.containsValue(2);
    }

    private List<CardValue> calculateSecondaryTwoPair() {
        Predicate<CardValue> appearsTwice =
                value ->
                        this.cards.stream().filter(card -> card.value().equals(value)).count() == 2;

        ArrayList<CardValue> res =
                this.cards.stream()
                        .map(Card::value)
                        .distinct()
                        .filter(appearsTwice)
                        .sorted(Collections.reverseOrder())
                        .collect(Collectors.toCollection(ArrayList::new));
        for (Card c : cards) {
            if (!res.contains(c.value())) res.add(c.value());
        }
        return res;
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

    public List<Card> getCards() {
        return cards;
    }
}
