package pokerhand.core;

import java.util.ArrayList;
import java.util.Objects;

public class Hand {
    //for now, the hand will only contain one card
    private final ArrayList<Card> cards;

    public Hand(Card card1) {
        this.cards = new ArrayList<>();
        //this.card contains card1
        this.cards.add(card1);
    }

    public static Hand fromString(String value) {
        //switch statement with 13 cases, one for each card value
        CardValue cardValue = switch (value) {
            case "J" -> CardValue.JACK;
            case "Q" -> CardValue.QUEEN;
            case "K" -> CardValue.KING;
            case "A" -> CardValue.ACE;
            default -> {
                if (value.matches("([2-9]|10)")) {
                    yield CardValue.values()[Integer.parseInt(value) - 2];
                } else {
                    throw new IllegalArgumentException("Card must be between 2 and 10, or J, Q, K, A");
                }
            }
        };
        return new Hand(new Card(cardValue));

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

    // returns the hand power => only HIGH_CARD is implemented for now
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
