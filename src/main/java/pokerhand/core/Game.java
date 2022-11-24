package pokerhand.core;

import java.util.*;
import pokerhand.ui.core.ConsoleUserInterface;
import pokerhand.ui.core.UserInterface;

public class Game {
    private final ArrayList<Hand> hands;
    private final CardDeck deck;
    private final UserInterface ui;

    public Game(UserInterface ui) {
        this.ui = ui;
        this.deck = new CardDeck();
        this.hands = new ArrayList<>();
    }

    public Game() {
        this(new ConsoleUserInterface());
    }

    public Game(Hand... hands) {
        this();
        Collections.addAll(this.hands, hands);
    }

    /**
     * Add a hand to the game
     *
     * @param hand the hand to add
     */
    public void addHand(Hand hand) {
        this.deck.takeCards(hand.getCards());
        this.hands.add(hand);
    }

    /**
     * Finds the winner of the game
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
     * Reset the game
     *
     * <p>This method will reset the deck and the hands
     */
    private void reset() {
        this.deck.reset();
        this.hands.clear();
    }

    /** Tears down the game and displays a message */
    private void tearDown() {
        this.ui.display("Thanks for playing!");
    }

    /** Play a round of the game */
    private void gameLoop() {
        while (this.hands.size() < 2) {
            this.ui.display("Main " + (this.hands.size() + 1) + ":  ");
            Hand hand;
            try {
                hand = this.ui.getHand();
                if (hand.getCards().size() != 5) {
                    throw new IllegalArgumentException("Une main doit avoir 5 cartes");
                }
                this.addHand(hand);
            } catch (IllegalArgumentException | IllegalStateException e) {
                this.ui.displayError(e.getMessage());
                this.ui.display("\n");
            }
        }
        Optional<Hand> winner = getWinner();
        if (winner.isPresent()) {
            this.ui.display(
                    "La main "
                            + (this.hands.indexOf(winner.get()) + 1)
                            + " gagne avec "
                            + winner.get().getPower().winMessage()
                            + "\n");

        } else {
            this.ui.display("Egalité\n");
        }
    }

    public void run(boolean runOnce) {
        boolean running = true;
        while (running) {
            gameLoop();
            reset();
            running = !runOnce && this.askToPlayAgain();
        }
    }

    private boolean askToPlayAgain() {
        while (true) {
            try {
                String choice =
                        this.ui.getChoice(
                                "Voulez-vous jouer à nouveau ?\n", Arrays.asList("Oui", "Non"));
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
