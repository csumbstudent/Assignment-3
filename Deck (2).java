/**
 * Created by Chris on 9/16/2015.
 */
public class Deck {
    public static final short MAX_CARDS_IN_PACK = 52;
    public static final short MAX_PACKS = 6;
    public static final short MAX_CARDS = MAX_PACKS * MAX_CARDS_IN_PACK;
    private static Card[] masterPack = new Card[MAX_CARDS_IN_PACK];
    private Card[] cards;
    private int topCard;
    private int numPacks;

    public Deck(int numPacks) {
        this.allocateMasterPack();
        if (numPacks > Deck.MAX_PACKS)
            this.init(Deck.MAX_PACKS);
        else if (numPacks < 1)
            this.init(1);
        else
            this.init(numPacks);
    }

    public Deck() {
        this.allocateMasterPack();
        this.init(1);
    }

    public void init(int numPacks) {
        this.cards = new Card[numPacks * Deck.MAX_CARDS_IN_PACK];
        for (int i = 0; i < numPacks * Deck.MAX_CARDS_IN_PACK; i++) {
            this.cards[i] = this.masterPack[i % Deck.MAX_CARDS_IN_PACK];
        }
        this.topCard = numPacks * Deck.MAX_CARDS_IN_PACK;
    }

    public void shuffle() {
        for (int i = this.topCard - 1; i >= 0; i--) {
            Card tmpCard = this.cards[i];
            this.cards[i] = this.cards[(int) (Math.random() * (this.topCard - 1))];
            this.cards[(int) (Math.random() * (this.topCard - 1))] = tmpCard;
        }
    }

    public Card dealCard() {
        if (this.topCard < 0)
            return new Card('0', Card.Suit.spades);
        else {
            Card card = new Card(this.cards[this.topCard - 1]);
            this.cards[this.topCard - 1] = null;
            this.topCard--;
            return card;
        }
    }

    public int getTopCard() {
        return this.topCard;
    }

    public Card inspectCard(int k) {
        if (k >= this.topCard || k < 0)
            return new Card('0', Card.Suit.spades);
        else
            return new Card(this.cards[k]);
    }

    private static void allocateMasterPack() {
        if (Deck.masterPack != null) {
            int masterPackPosition = 0;
            for (int i = 0; i < Card.Suit.values().length; i++) {
                for (int j = 0; j < Card.validCardValues.length; j++) {
                    Deck.masterPack[i * Card.validCardValues.length + j] = new Card(Card.validCardValues[j], Card.Suit.values()[i]);
                }
            }
        }
    }
}
