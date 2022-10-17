package pokerhand.core;


/**
 * The Card class represents a card in a poker hand with a CardValue.
 *
 * @param value the value of the card
 */
public record Card(CardValue value) {
    public String toString() {
        return value.toString();
    }
}