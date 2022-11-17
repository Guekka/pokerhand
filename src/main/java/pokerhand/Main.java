package pokerhand;

import pokerhand.core.Party;

/**
 * Main class
 *
 * <p>This class is used to start the game It creates a new party and starts it
 *
 * <p>
 */
public final class Main {
    public static void main(String[] args) {
        Party party = new Party();
        party.run(false);
    }
}
