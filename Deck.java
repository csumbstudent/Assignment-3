/**
 * Created by Chris on 9/16/2015.
 */
public class Deck {
    public static final short MAX_CARDS_IN_DECK = 52;
    public static final short MAX_PACKS = 6;
    public static final short MAX_CARDS = MAX_PACKS*MAX_CARDS_IN_DECK;
    private static Card[] masterPack = new Card[MAX_CARDS_IN_DECK];
    private Card[] cards;
    private int topCard;
    private int numPacks;
    public Deck(int numPacks){
        this.allocateMasterPack();
        if(numPacks > Deck.MAX_PACKS)
            this.init(Deck.MAX_PACKS);
        else if(numPacks < 1)
            this.init(1);
        else
            this.init(numPacks);
    }
    public Deck(){
        this.allocateMasterPack();
        this.init(1);
    }
    public void init(int numPacks){
        this.cards = new Card[numPacks*Deck.MAX_CARDS_IN_DECK];
        for(int i = 0; i < numPacks*Deck.MAX_CARDS_IN_DECK; i++){
            this.cards[i] = this.masterPack[i % Deck.MAX_CARDS_IN_DECK];
        }
        this.topCard = numPacks*Deck.MAX_CARDS_IN_DECK;
    }
    public void shuffle(){
        for(int i = this.topCard-1; i >= 0; i--){
            Card tmpCard = this.cards[i];
            this.cards[i] = this.cards[(int)(Math.random()*(this.topCard-1))];
            this.cards[(int)(Math.random()*(this.topCard-1))] = tmpCard;
        }
    }
    public Card dealCard(){
        if(this.topCard < 0)
            return new Card('0', Card.Suit.spades);
        else {
            Card card = new Card(this.cards[this.topCard - 1]);
            this.cards[this.topCard - 1] = null;
            this.topCard--;
            return card;
        }
    }
    public int getTopCard(){
        return this.topCard;
    }
    public Card inspectCard(int k){
        if(k >= this.topCard || k < 0)
            return new Card('0', Card.Suit.spades);
        else
            return new Card(this.cards[k]);
    }
    private static void allocateMasterPack(){
        if(Deck.masterPack != null){
            int masterPackPosition = 0;
            for(int i = 0; i < 4; i++){
                Deck.masterPack[masterPackPosition] = new Card('A', Card.Suit.values()[i]);
                masterPackPosition++;
                for(int x = 50; x <= 57; x++){
                    //Generate all of the number cards
                    Deck.masterPack[masterPackPosition] = new Card((char)x, Card.Suit.values()[i]);
                    masterPackPosition++;
                }
                Deck.masterPack[masterPackPosition] = new Card('T', Card.Suit.values()[i]);
                masterPackPosition++;
                Deck.masterPack[masterPackPosition] = new Card('J', Card.Suit.values()[i]);
                masterPackPosition++;
                Deck.masterPack[masterPackPosition] = new Card('Q', Card.Suit.values()[i]);
                masterPackPosition++;
                Deck.masterPack[masterPackPosition] = new Card('K', Card.Suit.values()[i]);
                masterPackPosition++;
            }
        }
    }
}
