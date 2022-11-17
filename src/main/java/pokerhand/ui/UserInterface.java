package pokerhand.ui;

import pokerhand.core.Hand;

public interface UserInterface {
    void display(String message);

    void displayError(String message);

    Hand getHand();

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
