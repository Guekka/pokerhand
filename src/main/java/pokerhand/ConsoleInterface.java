package pokerhand;

import pokerhand.core.Card;
import pokerhand.core.CardValue;
import pokerhand.core.Hand;
import pokerhand.core.Party;

import java.util.Scanner;

public class ConsoleInterface {
    public static Hand getHandFromConsole(String value) {
        //switch statement with 13 cases, one for each card value
        CardValue cardValue = switch (value) {
            case "J" -> CardValue.JACK;
            case "Q" -> CardValue.QUEEN;
            case "K" -> CardValue.KING;
            case "A" -> CardValue.ACE;
            default -> {
                if (value.matches("[2-9]")) {
                    yield CardValue.values()[Integer.parseInt(value) - 2];
                } else {
                    throw new IllegalArgumentException("Card must be between 2 and 10, or J, Q, K, A");
                }
            }
        };
        return new Hand(new Card(cardValue));

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter card value: ");
        // PokerHand console interface
        System.out.println("Welcome to PokerHand console interface!");
        System.out.println("Please enter the first hand:");
        Hand hand1 = getHandFromConsole(scanner.nextLine());
        System.out.println("Please enter the second hand:");
        Hand hand2 = getHandFromConsole(scanner.nextLine());
        Party party = new Party(hand1, hand2);
        System.out.println("The winner is:");
        System.out.println(party.getWinner());
    }

}
