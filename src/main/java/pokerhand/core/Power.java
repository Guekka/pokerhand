package pokerhand.core;

import utils.ListComparator;

import java.util.List;
import java.util.Objects;

public record Power(HandType handType, List<CardValue> secondary) implements Comparable<Power> {

    @Override
    public int compareTo(Power power) {
        //if the handType power is different, higher handType power wins
        if (this.handType.compareTo(power.handType) > 0)
            return 1;

        if (this.handType.compareTo(power.handType) < 0)
            return -1;

        //if the primary power is the same, we can compare the secondary power
        var comparator = new ListComparator<CardValue>();
        return comparator.compare(this.secondary, power.secondary);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Power power) {
            return this.compareTo(power) == 0;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.handType, this.secondary);
    }

}