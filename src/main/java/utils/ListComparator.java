package utils;

import java.util.Comparator;
import java.util.List;

public class ListComparator<T extends Comparable<T>> implements Comparator<List<T>> {
    @Override
    public int compare(List<T> lhs, List<T> rhs) {
        if (lhs.size() != rhs.size())
            return Integer.compare(
                    lhs.size(), rhs.size()); // If the size is different, the list with the biggest
        // size is bigger

        for (int i = 0; i < lhs.size(); i++) {
            if (lhs.get(i).compareTo(rhs.get(i)) > 0) return 1;

            if (lhs.get(i).compareTo(rhs.get(i)) < 0) return -1;
        }
        return 0;
    }
}
