package pokerhand.core;

/**
 * @param value only value attribute is needed for the first slice, the color attribute will be added later
 */
public record Card(CardValue value) {
    public String toString() {
        return value.toString();
    }
}