package pokerhand.core;

import java.util.ArrayList;

public class Hand {
    //for now, the hand will only contain one card
    private final ArrayList<Card> cards;

    public Hand(Card card1) {
        this.cards = new ArrayList<>();
        //this.card contains card1
        this.cards.add(card1);
    }

    // returns the hand power => only HIGH_CARD is implemented for now
    private HandType calculatePrimary() {
        return HandType.HIGH_CARD;
    }

    private ArrayList<CardValue> calculateSecondary(HandType primary) {
        if (primary == HandType.HIGH_CARD) {
            var ret = new ArrayList<CardValue>();
            ret.add(cards.get(0).value());
            return ret;
        }
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Power getPower() {
        var primary = calculatePrimary();
        return new Power(primary, calculateSecondary(primary));
    }
}
