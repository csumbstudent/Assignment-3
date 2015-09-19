/**
 * Created by Chris on 9/16/2015.
 */
public class Card {
    public enum Suit{clubs, diamonds, hearts, spades};
    private char value;
    private Suit suit;
    boolean errorFlag;
    public static char[] validCardValues = {'A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K'};
    public Card(char value, Suit suit){
        this.set(value, suit);
    }
    public Card(){
        this.set('A', Suit.spades);
    }
    public Card(Card card){
        this.set(card.value, card.suit);
    }
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
    private static boolean isValid(char value, Suit suit){
        for(char validValue : Card.validCardValues)
            if(validValue == value)
                return true;
        return false;
    }
    public char getValue(){
        return value;
    }
    public Suit getSuit(){
        return this.suit;
    }
    public String toString(){
        if(this.errorFlag == true)
            return "[INVALID CARD]";
        else
            return this.value + " of " + suit.toString();
    }
}