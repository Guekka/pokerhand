package pokerhand.core;

import java.util.ArrayList;
import java.util.List;

public class Party {
    private final ArrayList<Hand> hands;

    public Party(int hand1card1, int hand2card1) {
        this.hands = new ArrayList<>();
        this.hands.add(new Hand(new Card(hand1card1)));
        this.hands.add(new Hand(new Card(hand2card1)));
    }

    public List<Hand> getHands() {
        return this.hands;
    }
}