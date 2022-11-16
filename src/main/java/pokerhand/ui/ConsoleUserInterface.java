package pokerhand.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import pokerhand.core.Hand;

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

    public Hand getHand() {
        return Hand.fromString(scanner.nextLine());
    }
}
