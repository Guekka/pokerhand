package pokerhand.ui;

import pokerhand.core.Hand;

public interface UserInterface {
    void display(String message);

    void displayError(String message);

    Hand getHand();

    void displayHand(Hand hand);
}
