package pokerhand.core;

import java.util.ArrayList;
import java.util.List;

public class Party {
    private final ArrayList<Hand> hands;

    public Party(Hand hand1, Hand hand2) {
        this.hands = new ArrayList<>();
        this.hands.add(hand1);
        this.hands.add(hand2);
    }

    public List<Hand> getHands() {
        return this.hands;
    }
}