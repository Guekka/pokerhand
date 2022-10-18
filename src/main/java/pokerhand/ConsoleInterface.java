package pokerhand;

import pokerhand.core.Hand;
import pokerhand.core.Party;

import java.util.Scanner;

/**
 * The ConsoleInterface class is used to interact with the user.
 */
public class ConsoleInterface {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter card value: ");
        // PokerHand console interface
        System.out.println("Welcome to PokerHand console interface!");
        System.out.println("Please enter the first hand:");
        Hand hand1 = Hand.fromString(scanner.nextLine());
        System.out.println("Please enter the second hand:");
        Hand hand2 = Hand.fromString(scanner.nextLine());
        Party party = new Party(hand1, hand2);
        System.out.println("The winner is:");
        System.out.println(party.getWinner());
    }

}
