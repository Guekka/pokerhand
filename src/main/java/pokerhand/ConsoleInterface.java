package pokerhand;

import java.util.Scanner;
import pokerhand.core.Hand;
import pokerhand.core.Party;

/** The ConsoleInterface class is used to interact with the user. */
public class ConsoleInterface {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to PokerHand console interface!");
        System.out.println("Please enter the first hand:");
        Hand hand1 = Hand.fromString(scanner.nextLine());
        System.out.println("Please enter the second hand:");
        Hand hand2 = Hand.fromString(scanner.nextLine());
        Party party = new Party(hand1, hand2);

        var winner = party.getWinner();
        if (winner.isPresent()) {
            System.out.println("The winner is:");
            System.out.println(winner.get());
            System.out.println("with a  : " + winner.get().calculateHandType());
        } else {
            System.out.println("That's a draw!");
        }
    }
}
