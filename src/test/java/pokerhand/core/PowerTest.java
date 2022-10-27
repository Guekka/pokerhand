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
}
