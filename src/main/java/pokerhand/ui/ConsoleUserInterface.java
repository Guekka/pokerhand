package pokerhand.ui;

import java.util.Scanner;
import pokerhand.core.Hand;

public class ConsoleUserInterface implements UserInterface {
    Scanner scanner = new Scanner(System.in);

    public void display(String message) {
        System.out.println(message);
    }

    public void displayError(String message) {
        System.err.println(message);
    }

    public void displayHand(Hand hand) {
        System.out.println(hand);
    }

    public Hand getHand() {
        return Hand.fromString(scanner.nextLine());
    }
}
