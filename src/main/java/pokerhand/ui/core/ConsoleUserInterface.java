package pokerhand.ui.core;

import pokerhand.core.Hand;
import pokerhand.ui.exceptions.UiException;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

/**
 * Console user interface
 *
 * <p>This class is used to interact with the user through streams By default it uses System.in and
 * System.out and System.err but it can be used with any other input and output streams by using the
 * constructor with the corresponding parameters
 *
 * <p>
 */
public class ConsoleUserInterface implements UserInterface {
    InputStream in;
    PrintStream out;
    PrintStream err;
    Scanner scanner;

    /**
     * Create a new ConsoleUserInterface with the given streams
     *
     * @param in  the input stream
     * @param out the output stream
     * @param err the error stream
     */
    public ConsoleUserInterface(InputStream in, PrintStream out, PrintStream err) {
        this.in = in;
        this.out = out;
        this.err = err;
        this.scanner = new Scanner(in);
    }

    /**
     * Create a new ConsoleUserInterface with default streams
     */
    public ConsoleUserInterface() {
        this(System.in, System.out, System.err);
    }

    /**
     * Display a message to the output stream
     *
     * @param message the message to display to the output stream
     */
    public void display(String message) {
        out.print(message);
    }


    /**
     * Display a message to the error stream
     *
     * @param message the error message to display to the error stream
     */
    public void displayError(String message) {
        err.println(message);
    }

    /**
     * Display a Hand to the output stream
     *
     * @param hand the hand to display to the output stream
     */
    public void displayHand(Hand hand) {
        display(hand.toString());
    }

    /**
     * Selects a choice from a list of choices
     *
     * @param message the message to display to the output stream
     * @param choices the choices to select from
     * @return the selected choice
     */
    @Override
    public String getChoice(String message, List<String> choices) {
        display(message);
        for (int i = 0; i < choices.size(); i++) {
            display((i + 1) + " - " + choices.get(i));
        }
        display("Saisissez votre choix : \n");

        int choiceIndex;
        try {
            String choice = scanner.nextLine();
            choiceIndex = Integer.parseInt(choice) - 1;
        } catch (NumberFormatException e) {
            throw new UiException("Entrée invalide, veuillez entrer un numéro\n");
        }
        if (choiceIndex < 0 || choiceIndex >= choices.size()) {
            throw new UiException(
                    "Choix invalide, veuillez entrer un nombre entre 1 et " + choices.size() + "\n");
        }
        return choices.get(choiceIndex);
    }

    /**
     * Get a Hand from the user
     *
     * @return a Hand from the input stream
     */
    public Hand getHand() {
        return Hand.fromString(scanner.nextLine());
    }
}
