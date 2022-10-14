package pokerhand.core;

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

        //if the handType power is the same, we can compare the secondary power (secondary list will be the same size)
        for (int i = 0; i < this.secondary.size(); i++) {
            if (this.secondary.get(i).compareTo(power.secondary.get(i)) > 0)
                return 1;

            if (this.secondary.get(i).compareTo(power.secondary.get(i)) < 0)
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
        return Objects.hash(this.handType, this.secondary);
    }

}