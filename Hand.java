/* Class Hand represents a hand of cards. This is much like a collection of cards, but provides methods
   for interaction with the cards that are specific to a "hand," rather than a collection.
*/
public class Hand {
    public static final int MAX_CARDS = 50;
    private Card[] myCards = new Card[MAX_CARDS];
    int numCards = 0;
   /* Card()
      In: Nothing
      Out: Nothing
      Description: The default constructor for Hand does not actually do anything.
   */
   public Hand(){
    }
   /* void resetHand()
      In: Nothing
      Out: Nothing
      Description: This sets the hand to its default state, containing no cards.
   */
   public void resetHand(){
       this.myCards = new Card[MAX_CARDS];
       this.numCards = 0;
   }
   /* boolean takeCard(Card)
      In: A Card object
      Out: True if there is room in the hand for the card, false if otherwise
      Description: This takes a Card object and places a copy of that object into the hand.
   */
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