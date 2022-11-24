package pokerhand.core;

import java.util.List;
import java.util.Objects;
import utils.ListComparator;

public record Power(HandType handType, List<CardValue> secondary) implements Comparable<Power> {
    @Override
    public int compareTo(Power other) {
        // if the handType is different, higher handType wins
        if (this.handType.compareTo(other.handType) > 0) return 1;

        if (this.handType.compareTo(other.handType) < 0) return -1;

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

    public String winMessage() {
        return switch (this.handType) {
            case HIGH_CARD -> "gagne avec carte la plus élevée : " + secondary.get(0);
            case PAIR -> "gagne avec paire de " + secondary.get(0);
            case TWO_PAIR -> "gagne avec double paire de "
                    + secondary.get(0)
                    + " et "
                    + secondary.get(1);
            case THREE_OF_A_KIND -> "gagne avec brelan de " + secondary.get(0);
            case STRAIGHT -> "gagne avec quinte de " + secondary.get(0);
            case FLUSH -> "gagne avec couleur de " + secondary.get(0);
            case FULL_HOUSE -> "gagne avec full de " + secondary.get(0) + " et " + secondary.get(1);
            case FOUR_OF_A_KIND -> "gagne avec carré de " + secondary.get(0);
            case STRAIGHT_FLUSH -> "gagne avec quinte flush de " + secondary.get(0);
        };
    }
}
