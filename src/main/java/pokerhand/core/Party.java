package pokerhand.core;

import java.util.*;
import pokerhand.ui.core.ConsoleUserInterface;
import pokerhand.ui.core.UserInterface;

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

    public Party(Hand... hands) {
        this();
        Collections.addAll(this.hands, hands);
    }

    /**
     * Add a hand to the party
     *
     * @param hand the hand to add
     */
    public void addHand(Hand hand) {
        this.deck.takeCards(hand.getCards());
        this.hands.add(hand);
    }

    /**
     * Finds the winner of the party
     *
     * @return the Power of the winning hand
     */
    public Optional<Hand> getWinner() {
        // if all hands are equals, return None
        if (hands.stream().allMatch(hand -> hands.get(0).equals(hand))) return Optional.empty();

        // get maximum element
        return hands.stream().max(Comparator.comparing(Hand::getPower));
    }

    public List<Hand> getHands() {
        return this.hands;
    }

    /**
     * Start the game
     *
     * <p>Displays a welcome message
     */
    private void setUp() {
        this.ui.display("Welcome to the Poker Hand Game!");
    }

    /**
     * Reset the party
     *
     * <p>This method will reset the deck and the hands
     */
    private void reset() {
        this.deck.reset();
        this.hands.clear();
    }

    /** Tears down the party and displays a message */
    private void tearDown() {
        this.ui.display("Thanks for playing!");
    }

    /** Play a round of the game */
    private void gameLoop() {
        while (this.hands.size() < 2) {
            this.ui.display("Enter hand " + (this.hands.size() + 1) + ":");
            Hand hand;
            try {
                hand = this.ui.getHand();
                if (hand.getCards().size() != 5) {
                    throw new IllegalArgumentException("A hand must have 5 cards");
                }
                this.addHand(hand);
            } catch (IllegalArgumentException | IllegalStateException e) {
                this.ui.displayError(e.getMessage());
            }
        }
        Optional<Hand> winner = getWinner();
        if (winner.isPresent()) {
            this.ui.display("The winner is Player " + (this.hands.indexOf(winner.get()) + 1));
            this.ui.displayHand(winner.get());
            this.ui.display("With a " + winner.get().getPower().handType());
        } else {
            this.ui.display("It's a tie!");
        }
    }

    public void run(boolean runOnce) {
        boolean running = true;
        setUp();
        while (running) {
            gameLoop();
            reset();
            running = !runOnce && this.askToPlayAgain();
        }
        tearDown();
    }

    private boolean askToPlayAgain() {
        while (true) {
            try {
                String choice =
                        this.ui.getChoice("Do you want to play again?", Arrays.asList("Yes", "No"));
                if (choice.equals("No")) {
                    return false;
                }

                break;
            } catch (Exception e) {
                this.ui.displayError(e.getMessage());
            }
        }
        return true;
    }
}
