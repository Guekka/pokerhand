package pokerhand.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PartyTest {

    @Nested
    @DisplayName("Tests the winner for a party with two hand and a single card per hand")
    class TestWinnerWithOneCard {
        @Test
        void TestEqualHandType() {
            Party party = new Party(new Hand(List.of(new Card(CardValue.ACE))), new Hand(List.of(new Card(CardValue.ACE))));
            assertEquals(party.getWinner(), party.getWinner());
        }

        @Nested
        @DisplayName("Tests the winner for a party with two hand and a single card per hand")
        class TestDifferentHandType {
            @Test
            void testSmallerHandType() {
                Party party = new Party(new Hand(List.of(new Card(CardValue.ACE))), new Hand(List.of(new Card(CardValue.KING))));
                assertEquals(party.getHands().get(0), party.getWinner());
            }

            @Test
            void testBiggerHandType() {
                Party party = new Party(new Hand(List.of(new Card(CardValue.KING))), new Hand(List.of(new Card(CardValue.ACE))));
                assertEquals(party.getHands().get(1), party.getWinner());
            }
        }

    }

}