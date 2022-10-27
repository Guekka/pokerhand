package pokerhand.core;

/** The HandType enum represents the type of hand in a poker hand. */
public enum HandType {
    /**
     * The hand is a high card. A hand which has no pairs and is not a straight or a flush. The
     * lowest possible winning hand in poker.
     */
    HIGH_CARD,
    /**
     * The hand is a pair. One pair, or simply a pair, is a hand that contains two cards of one rank
     * and three cards of three other ranks (the kickers), such as 4♥ 4♠ K♠ 10♦ 5♠ ("one pair,
     * fours" or a "pair of fours"). It ranks below two pair and above high card.
     */
    PAIR,
    /**
     * The hand is two pairs. Two pair is a hand that contains two cards of one rank, two cards of
     * another rank and one card of a third rank (the kicker), such as J♥ J♣ 4♣ 4♠ 9♥ ("two pair,
     * jacks and fours" or "two pair, jacks over fours" or "jacks up").
     */
    TWO_PAIR,
    /**
     * The hand is three of a kind. Three of a kind, trips or a set is a hand that contains three
     * cards of one rank and two cards of two other ranks (the kickers), such as 9♣ 9♠ 9♦ 7♠ 3♣
     * ("three of a kind, nines" or "set of nines"). It ranks below a straight and above two pair.
     */
    THREE_OF_A_KIND,
    /**
     * The hand is a straight. A straight is a hand that contains five cards of sequential rank, not
     * all of the same suit, such as 7♣ 6♠ 5♠ 4♥ 3♥ (a "seven-high straight"). It ranks below a
     * flush and above three of a kind.
     */
    STRAIGHT,
    /**
     * The hand is a flush. A flush is a hand of playing cards where all cards are of the same suit.
     * There are different types of flush, including straight, where the flush is formed from a run
     * of cards in unbroken sequence of ranks. Flushes are one of the types of scoring hand in
     * poker.
     */
    FLUSH,
    /**
     * The hand is a full house. A full house is a hand that contains any three cards of the same
     * rank together with any two cards of the same rank. For example, 3♣ 3♠ 3♦ 6♣ 6♥ ("three of a
     * kind, threes full of sixes").
     */
    FULL_HOUSE,
    /**
     * The hand is four of a kind. Four of a kind, quads or quads is a hand that contains four cards
     * of one rank and one card of another rank (the kicker), such as 2♣ 2♠ 2♦ 2♥ 3♣ ("four of a
     * kind, twos" or "quads of twos"). It ranks below a straight flush and above a full house.
     */
    FOUR_OF_A_KIND,
    /**
     * The hand is a straight flush. A straight flush is a poker hand containing five cards of
     * sequential rank, all of the same suit, such as Q♣ J♣ 10♣ 9♣ 8♣ (a "queen-high straight
     * flush"). It ranks below a royal flush and above a flush.
     */
    STRAIGHT_FLUSH
}
