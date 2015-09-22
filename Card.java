/* Class Card represents a typical card that would be found in a deck of playing cards.
   It has private members to hold the value and suit of the card. It also has methods
   to validate and set these data members. 
*/
public class Card {
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
           if(validValue == value)
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

