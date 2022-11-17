package pokerhand.ui.core;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import pokerhand.core.Hand;
import pokerhand.ui.exceptions.UiException;

public class ConsoleUserInterface implements UserInterface {
    InputStream in;
    PrintStream out;
    PrintStream err;
    Scanner scanner;

    public ConsoleUserInterface(InputStream in, PrintStream out, PrintStream err) {
        this.in = in;
        this.out = out;
        this.err = err;
        this.scanner = new Scanner(in);
    }

    public ConsoleUserInterface() {
        this(System.in, System.out, System.err);
    }

    public void display(String message) {
        out.println(message);
    }

    public void displayError(String message) {
        err.println(message);
    }

    public void displayHand(Hand hand) {
        display(hand.toString());
    }

    @Override
    public String getChoice(String message, List<String> choices) {
        display(message);
        for (int i = 0; i < choices.size(); i++) {
            display((i + 1) + " - " + choices.get(i));
        }
        display("Enter your choice:");

        int choiceIndex;
        try {
            String choice = scanner.nextLine();
            choiceIndex = Integer.parseInt(choice) - 1;
        } catch (NumberFormatException e) {
            throw new UiException("Invalid input, please enter a number");
        }
        if (choiceIndex < 0 || choiceIndex >= choices.size()) {
            throw new UiException(
                    "Invalid choice, please enter a number between 1 and " + choices.size());
        }
        return choices.get(choiceIndex);
    }

    public Hand getHand() {
        return Hand.fromString(scanner.nextLine());
    }
}
