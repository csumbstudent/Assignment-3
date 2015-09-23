/*
   Assignment 3: The Card, Hand, and Deck classes.
   Description: The Card, Hand, and Deck classes implements functionality that would
                be expected from Cards, Hands, and Decks of typical playing cards.
   Team Members: Christopher Rendall
                 Caroline Lancaster
                 Daniel Kushner
*/

import java.util.Scanner; //For Scanner to read keyboard input.

/* Class Main acts as a driver to test classes Deck, Card, and Hand.*/
public class Main {
   //The program's entry point:
    public static void main(String[] args) {
       //First begin by testing the Card class.
        System.out.println("/*-----------------------------------------------");
        System.out.println("Testing class Card...\n");
	    Card[] cards = new Card[3];
        cards[0] = new Card();
        cards[1] = new Card('0', Card.Suit.hearts); //An invalid card.
        cards[2] = new Card('J', Card.Suit.clubs);
        for(Card card : cards){
            System.out.println(card);
        }
        cards[0].set('0', Card.Suit.hearts); //Set a valid card to an invalid card.
        cards[1].set('Q', Card.Suit.spades); //Set the invalid to a valid card.
        System.out.println("");
        for(Card card : cards){
            System.out.println(card);
        }
        System.out.println("-----------------------------------------------*\\\n");
       
       //Next, test the Hand class.
        System.out.println("/*-----------------------------------------------");
        System.out.println("Testing class Hand...\n");
        cards[0].set('3', Card.Suit.diamonds);
        Hand hand = new Hand();
       //Keep addings cards to the hand until the hand is full (hand.TakeCard() returns false).
       for(int cardToTake = 0; hand.takeCard(cards[cardToTake]); cardToTake++)
          if(cardToTake == 2)
            cardToTake = 0;
        System.out.println("Hand is full...");
        System.out.println("After deal");
        System.out.println("Hand = " + hand);
        System.out.println("\nInspecting card in position 50...");
        System.out.println(hand.inspectCard(50));
        System.out.println("\nInspecting card in position 49");
        System.out.println(hand.inspectCard(49));
        System.out.println("\nPlaying all cards in the deck...");
        while(hand.getNumCards() > 0)
            System.out.println(hand.playCard());
        System.out.println("\nHand after playing all cards: ");
        System.out.println("Hand = " + hand);
        System.out.println("-----------------------------------------------*\\\n");
        
       //Finally, test the Deck class.
        System.out.println("/*-----------------------------------------------");
        System.out.println("Testing class deck...");
        Deck deck = new Deck(2);
        System.out.println("Double Pack Unshufled:");
        while(deck.getTopCard() > 0)
            System.out.print(deck.dealCard() + " / ");
        System.out.println("\nDouble Pack Shuffled:");
        deck = new Deck(2);
        deck.shuffle();
        while(deck.getTopCard() > 0)
            System.out.print(deck.dealCard() + " / ");
        System.out.println("\nSingle Pack Unshuffled:");
        deck = new Deck();
        while(deck.getTopCard() > 0)
            System.out.print(deck.dealCard() + " / ");
        System.out.println("\nSingle Pack Shuffled:");
        deck = new Deck();
        deck.shuffle();
        while(deck.getTopCard() > 0)
            System.out.print(deck.dealCard() + " / ");
        System.out.println("\n-----------------------------------------------*\\\n");
        System.out.println("Feeding hands from a deck...");
        Scanner keyIn = new Scanner(System.in);
        int numPlayers = 0;
       //Keep reading input from the user until a valid numPlayers value is entered.
        do {
            System.out.println("Select a number of players from 1 to 10: ");
            if(!keyIn.hasNextInt()){
                keyIn.next();
                numPlayers = 0;
                System.out.println("Please enter a number...");
                continue;
            }
            else if((numPlayers = keyIn.nextInt()) < 1 || numPlayers > 10){
                numPlayers = 0;
                System.out.println("You must enter a number between 1 and 10.");
            }
        }while(numPlayers == 0);
        System.out.println("You have chosen to have " + numPlayers + " players.");
        System.out.println("Here are our hands, from an unshuffled deck:");
        deck = new Deck();
        Hand[] hands = new Hand[numPlayers];
        int handToDealTo = 0;
       //While the deck has at least one card in it, deal cards to the hands.
        while(deck.getTopCard() > 0){
           //Initialize the hand if it has not already been initialized.
            if(hands[handToDealTo] == null)
                hands[handToDealTo] = new Hand();
            hands[handToDealTo].takeCard(deck.dealCard()); //Take a card from the deck.
           //If the hand to deal to is greater than the number of players, set handToDealTo to 0.
           //Otherwise, increment it.
            handToDealTo = (handToDealTo == numPlayers-1) ? 0 : handToDealTo + 1;
        }
       //Print the hands.
        for(int i = 0; i < numPlayers; i++){
            System.out.print("\nHand " + (i+1) + hands[i]);
            hands[i].resetHand();
        }
        System.out.println("\n\nHere are our hands, from a shuffled deck:");
        deck = new Deck();
        deck.shuffle();
        while(deck.getTopCard() > 0){
            if(hands[handToDealTo] == null)
                hands[handToDealTo] = new Hand();
            hands[handToDealTo].takeCard(deck.dealCard());
            handToDealTo = (handToDealTo == numPlayers-1) ? 0 : handToDealTo + 1;
        }
        for(int i = 0; i < numPlayers; i++){
            System.out.print("\nHand " + (i+1) + hands[i]);
            hands[i].resetHand();
        }
        System.out.println("\n/*-----------------------------------------------");
    }
}


/* Class Card represents a typical card that would be found in a deck of playing cards.
   It has private members to hold the value and suit of the card. It also has methods
   to validate and set these data members. 
*/
class Card {
   //The four standard suits are supported.
   public enum Suit{clubs, diamonds, hearts, spades};
   private char value;
   private Suit suit;
   //errorFlag is set to true if the user tries to create or set a card's value
   //to one that is not in the validCardValues array. This will cause the card's
   //toString() method to indicate that the card is invalid.
   boolean errorFlag;
   //validCardValues holds values that a card is allowed to be.
   public static char[] validCardValues = {'A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K'};
   
   /* Card(char, Suit)
      In: A char representing the card's value, and a Suit representing the card's suit.
      Out: Nothing
      Description: This is a constructor that takes a value and a suit for a card. This will
                   create a card of the specified value and suit.
   */
   public Card(char value, Suit suit){
       this.set(value, suit);
   }
   /* Card()
      In: Nothing
      Out: Nothing
      Description: This is a default constructor that takes no values. It will create an Ace of Spades.
   */
   public Card(){
       this.set('A', Suit.spades);
   }
   /* Card(Card)
      In: A Card object
      Out: Nothing
      Description: This is a copy constructor that returns a NEW card with the same values as the card
                   passed into it. 
   */
   public Card(Card card){
       this.set(card.value, card.suit);
   }
   /* boolean set(char, Suit)
      In: A char representing the card's value and a Suit representing the card's suit.
      Out: True if the value and suit are valid, false if otherwise.
      Description: This set's the card's suit and value, if they are valid. Otherwise,
                   it sets the card's errorFlag to true.
   */
   public boolean set(char value, Suit suit){
       if(Card.isValid(value, suit)) {
           this.errorFlag = false;
           this.value = value;
           this.suit = suit;
           return true;
       }
       else{
           this.errorFlag = true;
           return false;
       }
   }
   /* boolean isValid(char, Suit)
      In: A char representing the card's value and a Suit representing its suit.
      Out: True if the value is valid, false if otherwise.
      Description: This function determines whether the value passed to it is a valid
                   value for a card. It checks the value against the valid values stored
                   in Card.validCardValues.
   */
   private static boolean isValid(char value, Suit suit){
       for(char validValue : Card.validCardValues)
           if(String.valueOf(validValue).toLowerCase().equals(String.valueOf(value).toLowerCase()))
               return true;
       return false;
   }
   /* char getValue()
      In: Nothing
      Out: A char holding the card's value.
      Description: This is an accessor for the card's value. 
   */
   public char getValue(){
       return value;
   }
   /* Suit getSuit()
      In: Nothing
      Out: The card's suit type.
      Description: This is an accessor for the card's suit.
   */
   public Suit getSuit(){
       return this.suit;
   }
   /* String toString()
      In: Nothing
      Out: A String object containing the value and suit of the card,
           or [INVALID CARD] if the errorFlag is set to true.
      Description: This returns the card's value to the caller in String form.
   */
   public String toString(){
       if(this.errorFlag == true)
           return "[INVALID CARD]";
       else
           return this.value + " of " + suit.toString();
   }
}

/* Class Deck represents a deck of cards consisting of a variable number of 52 card packs. It
   contains a master pack, which the deck is built off of. It also contains member functions
   that would be expected of a deck of cards, providing functionality like shuffling
   and dealing.
*/
class Deck {
   public static final short MAX_CARDS_IN_PACK = 52;
   public static final short MAX_PACKS = 6;
   public static final short MAX_CARDS = MAX_PACKS * MAX_CARDS_IN_PACK;
   //The masterPack is a pack of cards that the cards in the deck are built off of.
   //It contains one card for each value/suit combination. This is static,
   //as it does not change per object instantiated.
   private static Card[] masterPack = new Card[MAX_CARDS_IN_PACK];
   private Card[] cards; //The cards in the object's deck. Not static, as each deck object can have different cards.
   private int topCard; //The position of the card on the top of the deck.
   private int numPacks; //The deck can consist of multiple packs of cards.
  
   /* Deck(int)
      In: An integer specifying the number of packs to build the deck from.
      Out: Nothing
      Description: This is a constructor that will build a deck composed of the
                   specified number of packs.
   */
   public Deck(int numPacks) {
      //Build the master pack.
       this.allocateMasterPack();
      //If the user wants more packs than are available, give them the max.
       if (numPacks > Deck.MAX_PACKS)
           this.init(Deck.MAX_PACKS);
      //If the user wants 0 or less packs, give them one.
       else if (numPacks < 1)
           this.init(1);
       else
       //Otherwise, build the deck with the specified number of packs.
           this.init(numPacks); 
   }
   /* Deck()
      In: None
      Out: Nothing
      Description: This default constructor builds a deck with one pack.
   */
   public Deck() {
       this.allocateMasterPack();
       this.init(1);
   }
   /* void init(int)
      In: An integer whose value is the number of packs to build the deck from.
      Out: Nothing
      Description: This will initialize the cards array data member to a complete deck built
                   from the specified number of packs.
   */
   public void init(int numPacks) {
      //Initialize the cards array.
      this.cards = new Card[numPacks * Deck.MAX_CARDS_IN_PACK];
      //Until the total number of cards are reached, keep adding cards from the 
      //master pack.
      for (int i = 0; i < numPacks * Deck.MAX_CARDS_IN_PACK; i++) {
          this.cards[i] = this.masterPack[i % Deck.MAX_CARDS_IN_PACK];
      }
      //Set the top card to the last card allocated.
      this.topCard = numPacks * Deck.MAX_CARDS_IN_PACK;
   }
   /* void shuffle()
      In: Nothing
      Out: Nothing
      Description: This uses a Fisher-Yates shuffle to shuffle all of the cards in the
                   deck. 
   */
   public void shuffle() {
      //Beginning with the top card, decrement i until i is 0.
       for (int i = this.topCard - 1; i >= 0; i--) {
           Card tmpCard = this.cards[i]; //Store the card at i, since it will be overwritten.
          //Choose a random card position from within the deck.
          int randomPosition = (int) (Math.random() * (this.topCard - 1));
          //Take the card from the random position and store it in the ith position.
           this.cards[i] = this.cards[randomPosition];
          //Take the card from the ith position, and put it into the randomly chosen position.
           this.cards[randomPosition] = tmpCard;
          //The cards have now been swapped.
       }
   }
   /* Card dealCard()
      In: Nothing
      Out: A copy of the Card object on the top of the deck.
      Description: This function makes a copy of the card on the top of the deck,
                   removes that card from the deck, and returns the copy to the caller.
   */
   public Card dealCard() {
      //Return an invalid card if there are no cards in the deck.
       if (this.topCard < 0)
           return new Card('0', Card.Suit.spades);
       else {
          //Create a copy of the card on the top of the deck.
           Card card = new Card(this.cards[this.topCard - 1]);
          //Set the actual card on the top of the deck to null, to destroy it.
           this.cards[this.topCard - 1] = null;
          //The topCard is now one less than it was.
           this.topCard--;
          //return the copy.
           return card;
       }
   }
   /* int getTopCard()
      In: Nothing
      Out: An integer whose value is the position of the top card in the deck.
      Description: This is a basic accessor function.
   */
    public int getTopCard() {
       return this.topCard;
   }
   /* Card inspectCard(int)
      In: An integer representing the position of the card to be inspected.
      Out: A copy of the card at the specified position, or an invalid card if there is no
           card in that position.
      Description: This function returns a Card object whose values are equal to the card in
                   the specified position.
   */
    public Card inspectCard(int k) {
       //If k is invalid, return an invalid card.
       if (k >= this.topCard || k < 0)
           return new Card('0', Card.Suit.spades);
       else
       //Otherwise, return a copy of the card in position k.
           return new Card(this.cards[k]);
   }
   /* void allocateMasterPack()
      In: Nothing
      Out: Nothing
      Description: This function fills the masterPack if it is not already filled. It fills the pack
                   with valid card values.
   */
    private static void allocateMasterPack() {
       //If Deck.masterPack is null, then it needs to be filled, otherwise, nothing needs to be done.
       if (Deck.masterPack != null) {
          //For each suit, fill the masterPack with each valid card value from that suit.
           for (int i = 0; i < Card.Suit.values().length; i++) {
               for (int j = 0; j < Card.validCardValues.length; j++) {
                   Deck.masterPack[i * Card.validCardValues.length + j] = new Card(Card.validCardValues[j], Card.Suit.values()[i]);
               }
           }
       }
   }
}
/* Class Hand represents a hand of cards. This is much like a collection of cards, but provides methods
   or interaction with the cards that are specific to a "hand," rather than a collection.
*/
class Hand {
   public static final int MAX_CARDS = 50;
   private Card[] myCards = new Card[MAX_CARDS];
   int numCards = 0;
  /* Hand()
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
   /* Card playCard()
     In: Nothing
     Out: A Card object with the same values as the card on the top of the hand.
     Description: This creates a copy of the first card in the hand and returns it to the caller.
  */
  public Card playCard(){
      Card card = this.myCards[this.numCards-1];
      this.myCards[this.numCards-1] = null;
      this.numCards--;
      return card;
  }
   /* String toString()
     In: Nothing
     Out: A String object containing the cards in the hand.
     Description: This will provide a textual representation of the data contained in hand to the caller.
  */
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
   /* int getNumCards()
     In: Nothing
     Out: An integer whose value is the number of cards in the hand.
     Description: This is a basic accessor function.
  */
  public int getNumCards(){
      return this.numCards;
  }
   /* Card inspectCard(int)
      In: An integer representing the position of the card to be inspected.
      Out: A copy of the card at the specified position, or an invalid card if there is no
           card in that position.
      Description: This function returns a Card object whose values are equal to the card in
                   the specified position.
   */
  public Card inspectCard(int k){
      if(k >= this.numCards || k < 0)
          return new Card('0', Card.Suit.spades);
      else
          return new Card(this.myCards[k]);
  }
}
