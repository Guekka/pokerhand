package utils;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

class ListComparatorTest {

    @Test
    void testBySize() {
        var small = List.of(1, 2, 3);
        var big = List.of(1, 2, 3, 4, 5);

        var comparator = new ListComparator<Integer>();
        assertTrue(comparator.compare(small, big) < 0);
    }

    @Test
    void testByValue() {
        var big = List.of(1, 2, 4);
        var small = List.of(1, 2, 3);

        var comparator = new ListComparator<Integer>();
        assertTrue(comparator.compare(big, small) > 0);
    }
}
