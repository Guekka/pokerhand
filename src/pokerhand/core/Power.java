package pokerhand.core;

import java.util.List;
import java.util.Objects;

public record Power(HandType primary, List<Integer> secondary) implements Comparable<Power> {

    @Override
    public int compareTo(Power power) {
        //if the primary power is different, higher primary power wins
        if (this.primary.compareTo(power.primary) > 0)
            return 1;

        if (this.primary.compareTo(power.primary) < 0)
            return -1;

        //if the primary power is the same, we can compare the secondary power (secondary list will be the same size)
        for (int i = 0; i < this.secondary.size(); i++) {
            if (this.secondary.get(i) > power.secondary.get(i))
                return 1;

            if (this.secondary.get(i) < power.secondary.get(i))
                return -1;
        }

        //if the secondary power is the same, Powers are equal
        return 0;
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
        return Objects.hash(this.primary, this.secondary);
    }

    @Override
    public String toString() {
        return this.primary + " / " + this.secondary;
    }

}