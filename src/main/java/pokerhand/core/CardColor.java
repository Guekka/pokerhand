package pokerhand.core;

public enum CardColor {
    CLUB,
    DIAMOND,
    HEART,
    SPADE;

    @Override
    public String toString() {
        return switch (this) {
            case CLUB -> "♣";
            case DIAMOND -> "♦";
            case HEART -> "♥";
            case SPADE -> "♠";
        };
    }
}
