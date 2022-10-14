package pokerhand.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @Test
    void value() {
        var card = new Card(CardValue.ACE);
        assertEquals(CardValue.ACE, card.value());
    }

}