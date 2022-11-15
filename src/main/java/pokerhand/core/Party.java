package pokerhand.core;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import pokerhand.ui.ConsoleUserInterface;
import pokerhand.ui.UserInterface;

public class Party {
    private final ArrayList<Hand> hands;
    private final CardDeck deck;
    private final UserInterface ui;

    public Party(UserInterface ui) {
        this.ui = ui;
        this.deck = new CardDeck();
        this.hands = new ArrayList<>();
    }

    public Party() {
        this(new ConsoleUserInterface());
    }

    public Party(Hand hand1, Hand hand2) {
        this();
        addHand(hand1);
        addHand(hand2);
    }

    public void addHand(Hand hand) {
        this.deck.takeCards(hand.getCards());
        this.hands.add(hand);
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

    private void setUp() {
        this.ui.display("Welcome to the Poker Hand Game!");
    }

    private void tearDown() {
        this.ui.display("Thanks for playing!");
        this.deck.reset();
        this.hands.clear();
    }

    private void gameLoop() {
        while (this.hands.size() < 2) {
            this.ui.display("Enter a hand:");
            Hand hand;
            try {
                hand = this.ui.getHand();
                this.addHand(hand);
            } catch (IllegalArgumentException e) {
                this.ui.displayError(e.getMessage());
            }
        }
        Optional<Hand> winner = getWinner();
        if (winner.isPresent()) {
            this.ui.display("The winner is:");
            this.ui.displayHand(winner.get());
        } else {
            this.ui.display("It's a tie!");
        }
    }

    public void run() {
        setUp();
        gameLoop();
        tearDown();
    }

    public static void main(String[] args) {
        Party party = new Party();
        party.run();
    }
}
