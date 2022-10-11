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

    // returns the hand power between 1 and 9 (from higher card to straight flush) => only 1 possibility for now
    private HandType calculatePrimary() {
        return HandType.HIGH_CARD;
    }

    private ArrayList<Integer> calculateSecondary(HandType primary) {
        if (primary == HandType.HIGH_CARD) {
            var ret = new ArrayList<Integer>();
            ret.add(cards.get(0).value());
            return ret;
        }
        throw new IllegalArgumentException("The primary power must be between 1 and 9");
    }

    public Power getPower() {
        var primary = calculatePrimary();
        return new Power(primary, calculateSecondary(primary));
    }
}
