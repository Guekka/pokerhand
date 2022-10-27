package pokerhand.core;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Party {
    private final ArrayList<Hand> hands;

    public Party(Hand hand1, Hand hand2) {
        this.hands = new ArrayList<>();
        this.hands.add(hand1);
        this.hands.add(hand2);
    }

    public Optional<Hand> getWinner() {
        // if all hands are equals, return None
        if (hands.stream().allMatch(hand -> hands.get(0).equals(hand))) return Optional.empty();

        // get maximum element
        return hands.stream().max(Comparator.comparing(Hand::getPower));
    }

    public List<Hand> getHands() {
        return this.hands;
    }
}
