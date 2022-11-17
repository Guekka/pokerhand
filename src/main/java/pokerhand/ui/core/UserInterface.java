package pokerhand.ui.core;

import java.util.List;
import pokerhand.core.Hand;

/**
 * User interface
 *
 * <p>This interface is used to interact with the user It is used to display messages and get user
 * input
 *
 * <p>
 */
public interface UserInterface {
    /**
     * Display a message to the user
     *
     * @param message the message to display
     */
    void display(String message);

    /**
     * Display an error message to the user
     *
     * @param message the error message to display
     */
    void displayError(String message);

    /**
     * Reads a hand from the user
     *
     * @return the hand entered by the user
     */
    Hand getHand();

    /**
     * Display a hand to the user
     *
     * @param hand the hand to display
     */
    void displayHand(Hand hand);

    /**
     * Gets a choice from the user
     *
     * @param message the message to display
     * @param choices the choices to display
     * @return the choice entered by the user
     */
    String getChoice(String message, List<String> choices);
}
