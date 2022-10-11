package pokerhand.core;

/**
 * @param value only value attribute is needed for the first slice, the color attribute will be added later
 */
public record Card(int value) {
    //if the value is not between 2 and 14 (two to ace), the value is set to 2
    public Card {
        if (value < 2 || value > 14)
            throw new IllegalArgumentException("The value of a card must be between 2 and 14");
    }
}