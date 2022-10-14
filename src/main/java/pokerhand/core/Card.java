package pokerhand.core;

/**
 * The Card class represents a card in a poker hand with a CardValue.
 *
 * @param value the value of the card
 * @param color
 */
public record Card(CardValue value, CardColor color) implements Comparable<Card> {
    /**
     * Creates a Card from a string.
     *
     * @param string the string representation of the card
     * @return the card represented by the string
     */
    public static Card fromString(String string) {
        String value = string.substring(0, string.length() - 2);
        CardValue cardValue = switch (string) {
            case "J" -> CardValue.JACK;
            case "Q" -> CardValue.QUEEN;
            case "K" -> CardValue.KING;
            case "A" -> CardValue.ACE;
            default -> {
                if (value.matches("([2-9]|10)")) {
                    yield (CardValue.values()[Integer.parseInt(string) - 2]);
                } else {
                    throw new IllegalArgumentException("Card must be between 2 and 10, or J, Q, K, A");
                }
            }
        };
        String color = string.substring(string.length() - 2);

        CardColor cardColor = switch (color) {
            case "Tr" -> CardColor.CLUB;
            case "Co" -> CardColor.HEART;
            case "Ca" -> CardColor.DIAMOND;
            case "Pi" -> CardColor.SPADE;
            default -> throw new IllegalArgumentException("CardColor must be between Tr ,Co,Ca,Pi");
        };
        return new Card(cardValue, cardColor);

    }

    /**
     * Representation of the card as a string.
     *
     * @return the string representation of the card
     */
    public String toString() {
        return value.toString();
    }

    @Override
    public int compareTo(Card other) {
        return this.value.compareTo(other.value);
    }

}