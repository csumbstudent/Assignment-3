/**
 * Created by Chris on 9/16/2015.
 */
public class Hand {
    public static final int MAX_CARDS = 50;
    private Card[] myCards = new Card[MAX_CARDS];
    int numCards = 0;
    public Hand(){

    }
    public void resetHand(){
        for(int i = 0; i < numCards; i++)
            this.myCards[i] = null;
        this.numCards = 0;
    }
    public boolean takeCard(Card card){
        if(this.numCards >= MAX_CARDS)
            return false;
        else {
            this.myCards[numCards] = new Card(card);
            this.numCards++;
            return true;
        }
    }
    public Card playCard(){
        Card card = this.myCards[this.numCards-1];
        this.myCards[this.numCards-1] = null;
        this.numCards--;
        return card;
    }
    public String toString(){
        String handString = "( ";
        for(int i = 0; i <  this.numCards; i++){
            handString += this.myCards[i].toString();
            if(i != this.numCards - 1)
                handString += ", ";
        }
        handString += " )";
        return handString;
    }
    public int getNumCards(){
        return this.numCards;
    }
    public Card inspectCard(int k){
        if(k >= this.numCards)
            return new Card('0', Card.Suit.spades);
        else
            return new Card(this.myCards[k]);
    }
}
