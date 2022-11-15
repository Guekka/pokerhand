package pokerhand.core;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class PowerTest {
    @Nested
    @DisplayName("Test Power.equals")
    class TestEquals {
        @Test
        void testEqualToSame() {
            Power power1 = new Power(HandType.HIGH_CARD, List.of());
            Power power2 = new Power(HandType.HIGH_CARD, List.of());
            assertEquals(power1, power2);
        }

        @Test
        void testNotEqualToOtherType() {
            Power power1 = new Power(HandType.HIGH_CARD, List.of());
            assertNotEquals(power1, new Object());
        }

        @Test
        void testDifferentPower() {
            Power base = new Power(HandType.HIGH_CARD, List.of());
            Power differentSecondary = new Power(HandType.HIGH_CARD, List.of(CardValue.ACE));
            assertNotEquals(base, differentSecondary);

            Power differentPrimary = new Power(HandType.FOUR_OF_A_KIND, List.of());
            assertNotEquals(base, differentPrimary);
        }
    }

    @Nested
    @DisplayName("Test Power.hashCode")
    class TestHashCode {
        @Test
        void testEqualPower() {
            Power power1 = new Power(HandType.HIGH_CARD, List.of());
            Power power2 = new Power(HandType.HIGH_CARD, List.of());
            assertEquals(power1.hashCode(), power2.hashCode());
        }

        @Test
        void testDifferentPower() {
            Power power1 = new Power(HandType.HIGH_CARD, List.of());
            Power power2 = new Power(HandType.HIGH_CARD, List.of(CardValue.ACE));
            assertNotEquals(power1.hashCode(), power2.hashCode());
        }
    }

    @Nested
    @DisplayName("Test Power.compareTo")
    class TestCompareTo {
        @Nested
        @DisplayName("Test the primary values")
        class TestPrimary {
            @Test
            void testEqualPower() {
                Power power1 = new Power(HandType.HIGH_CARD, List.of());
                Power power2 = new Power(HandType.HIGH_CARD, List.of());
                assertEquals(0, power1.compareTo(power2));
            }

            @Test
            void testBiggerPower() {
                Power power1 = new Power(HandType.HIGH_CARD, List.of());
                Power power2 = new Power(HandType.FLUSH, List.of());
                assertEquals(-1, power1.compareTo(power2));
            }

            @Test
            void testSmallerPower() {
                Power power1 = new Power(HandType.FLUSH, List.of());
                Power power2 = new Power(HandType.HIGH_CARD, List.of());
                assertEquals(1, power1.compareTo(power2));
            }
        }

        @Nested
        @DisplayName("Test the secondary values")
        class TestSecondary {
            @Test
            void testEqualPower() {
                Power power1 = new Power(HandType.HIGH_CARD, List.of(CardValue.ACE));
                Power power2 = new Power(HandType.HIGH_CARD, List.of(CardValue.ACE));
                assertEquals(0, power1.compareTo(power2));
            }

            @Test
            void testBiggerPower() {
                Power power1 =
                        new Power(HandType.FOUR_OF_A_KIND, List.of(CardValue.ACE, CardValue.NINE));
                Power power2 =
                        new Power(HandType.FOUR_OF_A_KIND, List.of(CardValue.ACE, CardValue.QUEEN));
                assertTrue(power1.compareTo(power2) < 0);
            }

            @Test
            void testSmallerPower() {
                Power power1 = new Power(HandType.FLUSH, List.of(CardValue.ACE, CardValue.JACK));
                Power power2 = new Power(HandType.FLUSH, List.of());
                assertTrue(power1.compareTo(power2) > 0);
            }
        }
    }

    @Nested
    @DisplayName("Test if the power of a hand is calculated correctly")
    class TestPowerCalculation {

        @Test
        void testHighCard() {
            var highCard =
                    new Hand(
                            List.of(
                                    new Card(CardValue.ACE, CardColor.SPADE),
                                    new Card(CardValue.NINE, CardColor.CLUB),
                                    new Card(CardValue.THREE, CardColor.DIAMOND),
                                    new Card(CardValue.JACK, CardColor.CLUB),
                                    new Card(CardValue.TEN, CardColor.CLUB)));

            var expectedHandType = HandType.HIGH_CARD;
            var expectedSecondary =
                    List.of(
                            CardValue.ACE,
                            CardValue.JACK,
                            CardValue.TEN,
                            CardValue.NINE,
                            CardValue.THREE);

            assertEquals(new Power(expectedHandType, expectedSecondary), highCard.getPower());
        }

        @Test
        void testFlush() {
            var flush =
                    new Hand(
                            List.of(
                                    new Card(CardValue.ACE, CardColor.CLUB),
                                    new Card(CardValue.NINE, CardColor.CLUB),
                                    new Card(CardValue.THREE, CardColor.CLUB),
                                    new Card(CardValue.JACK, CardColor.CLUB),
                                    new Card(CardValue.TEN, CardColor.CLUB)));

            var expectedHandType = HandType.FLUSH;
            var expectedSecondary =
                    List.of(
                            CardValue.ACE,
                            CardValue.JACK,
                            CardValue.TEN,
                            CardValue.NINE,
                            CardValue.THREE);

            assertEquals(new Power(expectedHandType, expectedSecondary), flush.getPower());
        }

        @Test
        void testPair() {
            var pair =
                    new Hand(
                            List.of(
                                    new Card(CardValue.JACK, CardColor.CLUB),
                                    new Card(CardValue.JACK, CardColor.SPADE),
                                    new Card(CardValue.QUEEN, CardColor.CLUB),
                                    new Card(CardValue.KING, CardColor.CLUB),
                                    new Card(CardValue.ACE, CardColor.DIAMOND)));
            var expectedHandType = HandType.PAIR;
            var expectedSecondary =
                    List.of(CardValue.JACK, CardValue.ACE, CardValue.KING, CardValue.QUEEN);

            assertEquals(new Power(expectedHandType, expectedSecondary), pair.getPower());
        }

        @Test
        void testTwoPair() {
            var twopair =
                    new Hand(
                            List.of(
                                    new Card(CardValue.JACK, CardColor.CLUB),
                                    new Card(CardValue.JACK, CardColor.SPADE),
                                    new Card(CardValue.QUEEN, CardColor.CLUB),
                                    new Card(CardValue.QUEEN, CardColor.CLUB),
                                    new Card(CardValue.ACE, CardColor.DIAMOND)));
            var expectedHandType = HandType.TWO_PAIR;
            var expectedSecondary = List.of(CardValue.QUEEN, CardValue.JACK, CardValue.ACE);

            assertEquals(new Power(expectedHandType, expectedSecondary), twopair.getPower());
        }

        @Test
        void testTOAK() {
            var toak =
                    new Hand(
                            List.of(
                                    new Card(CardValue.JACK, CardColor.CLUB),
                                    new Card(CardValue.JACK, CardColor.CLUB),
                                    new Card(CardValue.JACK, CardColor.SPADE),
                                    new Card(CardValue.KING, CardColor.HEART),
                                    new Card(CardValue.ACE, CardColor.SPADE)));

            var expectedHandType = HandType.THREE_OF_A_KIND;
            var expectedSecondary = List.of(CardValue.JACK, CardValue.ACE, CardValue.KING);

            assertEquals(new Power(expectedHandType, expectedSecondary), toak.getPower());
        }

        @Test
        void testFullHouse() {
            var fullLouse =
                    new Hand(
                            List.of(
                                    new Card(CardValue.JACK, CardColor.CLUB),
                                    new Card(CardValue.JACK, CardColor.CLUB),
                                    new Card(CardValue.JACK, CardColor.SPADE),
                                    new Card(CardValue.KING, CardColor.HEART),
                                    new Card(CardValue.KING, CardColor.SPADE)));

            var expectedHandType = HandType.FULL_HOUSE;
            var expectedSecondary = List.of(CardValue.JACK, CardValue.KING);

            assertEquals(new Power(expectedHandType, expectedSecondary), fullLouse.getPower());
        }

        @Test
        void testFOAK() {
            var foak =
                    new Hand(
                            List.of(
                                    new Card(CardValue.JACK, CardColor.CLUB),
                                    new Card(CardValue.JACK, CardColor.CLUB),
                                    new Card(CardValue.JACK, CardColor.CLUB),
                                    new Card(CardValue.JACK, CardColor.HEART),
                                    new Card(CardValue.ACE, CardColor.SPADE)));

            var expectedHandType = HandType.FOUR_OF_A_KIND;
            var expectedSecondary = List.of(CardValue.JACK, CardValue.ACE);

            assertEquals(new Power(expectedHandType, expectedSecondary), foak.getPower());
        }

        @Test
        void testStraight() {
            var straight =
                    new Hand(
                            List.of(
                                    new Card(CardValue.TEN, CardColor.CLUB),
                                    new Card(CardValue.NINE, CardColor.CLUB),
                                    new Card(CardValue.EIGHT, CardColor.DIAMOND),
                                    new Card(CardValue.SEVEN, CardColor.SPADE),
                                    new Card(CardValue.SIX, CardColor.HEART)));
            var expectedHandType = HandType.STRAIGHT;
            var expectedSecondary = List.of(CardValue.TEN);
            assertEquals(new Power(expectedHandType, expectedSecondary), straight.getPower());
        }
    }
}
