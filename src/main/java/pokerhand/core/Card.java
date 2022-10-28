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
        if (string.length() >= 3 && string.length() <= 4) {
            String value = string.substring(0, string.length() - 2);
            CardValue cardValue =
                    switch (value) {
                        case "J" -> CardValue.JACK;
                        case "Q" -> CardValue.QUEEN;
                        case "K" -> CardValue.KING;
                        case "A" -> CardValue.ACE;
                        default -> {
                            if (value.matches("([2-9]|10)")) {
                                yield (CardValue.values()[Integer.parseInt(value) - 2]);
                            } else {
                                throw new IllegalArgumentException(
                                        "Card must be between 2 and 10, or J, Q, K, A");
                            }
                        }
                    };
            String color = string.substring(string.length() - 2).toLowerCase();

            CardColor cardColor =
                    switch (color) {
                        case "tr", "trèfle", "trefle" -> CardColor.CLUB;
                        case "co", "coeur" -> CardColor.HEART;
                        case "ca", "carreau" -> CardColor.DIAMOND;
                        case "pi", "pique" -> CardColor.SPADE;
                        default -> throw new IllegalArgumentException(
                                "CardColor must be between Tr ,Co,Ca,Pi");
                    };
            return new Card(cardValue, cardColor);
        } else {
            throw new IllegalArgumentException("Card must have 3 or 4 characters");
        }
    }

    @Override
    public int compareTo(Card other) {
        if (value.compareTo(other.value()) > 0) {
            return 1;

        } else if (value.compareTo(other.value()) < 0) {
            return -1;
        }

        // Here, cards have the same value but maybe not the same color
        return this.color().compareTo(other.color());
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Card card) {
            return this.value == card.value && this.color == card.color;
        }
        return false;
    }

    /**
     * Representation of the card as a string.
     *
     * @return the string representation of the card
     */

    public String toString() {
        return value.toString() + color.toString();
    }

    public boolean isPreviousTo(Card other) {
        return this.value().ordinal() == other.value().ordinal() - 1;
    }
}
