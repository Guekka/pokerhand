package pokerhand;

import pokerhand.core.Game;

/**
 * Main class
 *
 * <p>This class is used to start the game It creates a new game and starts it
 *
 * <p>
 */
public final class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.run(false);
    }
}
