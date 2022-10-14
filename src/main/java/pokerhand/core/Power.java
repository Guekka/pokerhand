package pokerhand.core;

import utils.ListComparator;

import java.util.List;
import java.util.Objects;

public record Power(HandType handType, List<CardValue> secondary) implements Comparable<Power> {

    @Override
    public int compareTo(Power other) {
        // if the handType is different, higher handType wins
        if (this.handType.compareTo(other.handType) > 0)
            return 1;

        if (this.handType.compareTo(other.handType) < 0)
            return -1;

        // if the primary power is the same, we can compare the secondary power
        var comparator = new ListComparator<CardValue>();
        return comparator.compare(this.secondary, other.secondary);
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Power power) {
            return this.compareTo(power) == 0;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.handType, this.secondary);
    }

}