package pokerhand.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class PowerTest {
    @Nested
    @DisplayName("Test comparaison between two Power")
    class TestComparisons {
        @Nested
        @DisplayName("Test the equals method")
        class TestEquals {
            @Test
            void testEqualPower() {
                Power power1 = new Power(HandType.HIGH_CARD, new ArrayList<>());
                Power power2 = new Power(HandType.HIGH_CARD, new ArrayList<>());
                assertEquals(power1, power2);
            }

            @Test
            void testDifferentObjects() {
                Power power1 = new Power(HandType.HIGH_CARD, new ArrayList<>());
                assertNotEquals(power1, new Object());
            }

            @Test
            void testDifferentPower() {
                Power power1 = new Power(HandType.HIGH_CARD, new ArrayList<>());
                Power power2 = new Power(HandType.HIGH_CARD, new ArrayList<>() {{
                    add(CardValue.ACE);
                }});
                assertNotEquals(power1, power2);
            }
        }

        @Nested
        @DisplayName("Test the hashcode method")
        class TestHashCode {
            @Test
            void testEqualPower() {
                Power power1 = new Power(HandType.HIGH_CARD, new ArrayList<>());
                Power power2 = new Power(HandType.HIGH_CARD, new ArrayList<>());
                assertEquals(power1.hashCode(), power2.hashCode());
            }

            @Test
            void testDifferentPower() {
                Power power1 = new Power(HandType.HIGH_CARD, new ArrayList<>());
                Power power2 = new Power(HandType.HIGH_CARD, new ArrayList<>() {{
                    add(CardValue.ACE);
                }});
                assertNotEquals(power1.hashCode(), power2.hashCode());
            }
        }

        @Nested
        @DisplayName("Test the compareTo method")
        class TestCompareTo {
            @Nested
            @DisplayName("Test the primary values")
            class TestPrimary {
                @Test
                void testEqualPower() {
                    Power power1 = new Power(HandType.HIGH_CARD, new ArrayList<>());
                    Power power2 = new Power(HandType.HIGH_CARD, new ArrayList<>());
                    assertEquals(0, power1.compareTo(power2));
                }

                @Test
                void testBiggerPower() {
                    Power power1 = new Power(HandType.HIGH_CARD, new ArrayList<>());
                    Power power2 = new Power(HandType.FLUSH, new ArrayList<>());
                    assertEquals(-1, power1.compareTo(power2));
                }

                @Test
                void testSmallerPower() {
                    Power power1 = new Power(HandType.FLUSH, new ArrayList<>());
                    Power power2 = new Power(HandType.HIGH_CARD, new ArrayList<>());
                    assertEquals(1, power1.compareTo(power2));
                }
            }

            @Nested
            @DisplayName("Test the secondary values")
            class TestSecondary {
                @Test
                void testEqualPower() {
                    Power power1 = new Power(HandType.HIGH_CARD, new ArrayList<>() {{
                        add(CardValue.ACE);
                    }});
                    Power power2 = new Power(HandType.HIGH_CARD, new ArrayList<>() {{
                        add(CardValue.ACE);
                    }});
                    assertEquals(0, power1.compareTo(power2));
                }

                @Test
                void testBiggerPower() {
                    Power power1 = new Power(HandType.HIGH_CARD, new ArrayList<>());
                    Power power2 = new Power(HandType.HIGH_CARD, new ArrayList<>() {{
                        add(CardValue.ACE);
                    }});
                    assertEquals(-1, power1.compareTo(power2));
                }

                @Test
                void testSmallerPower() {
                    Power power1 = new Power(HandType.HIGH_CARD, new ArrayList<>() {{
                        add(CardValue.ACE);
                    }});
                    Power power2 = new Power(HandType.HIGH_CARD, new ArrayList<>());
                    assertEquals(1, power1.compareTo(power2));
                }
            }
        }
    }

}