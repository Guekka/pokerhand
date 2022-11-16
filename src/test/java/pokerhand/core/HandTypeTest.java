package pokerhand.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class HandTypeTest {

    @Test
    void testToString() {
        assertEquals("High Card", HandType.HIGH_CARD.toString());
        assertEquals("Pair", HandType.PAIR.toString());
        assertEquals("Two Pair", HandType.TWO_PAIR.toString());
        assertEquals("Three of a Kind", HandType.THREE_OF_A_KIND.toString());
        assertEquals("Straight", HandType.STRAIGHT.toString());
        assertEquals("Flush", HandType.FLUSH.toString());
        assertEquals("Full House", HandType.FULL_HOUSE.toString());
        assertEquals("Four of a Kind", HandType.FOUR_OF_A_KIND.toString());
        assertEquals("Straight Flush", HandType.STRAIGHT_FLUSH.toString());
    }
}
