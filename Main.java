import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        System.out.println("/*-----------------------------------------------");
        System.out.println("Testing class Card...\n");
	    Card[] cards = new Card[3];
        cards[0] = new Card();
        cards[1] = new Card('0', Card.Suit.hearts);
        cards[2] = new Card('J', Card.Suit.clubs);
        for(Card card : cards){
            System.out.println(card);
        }
        cards[0].set('0', Card.Suit.hearts);
        cards[1].set('Q', Card.Suit.spades);
        System.out.println("");
        for(Card card : cards){
            System.out.println(card);
        }
        System.out.println("-----------------------------------------------*\\\n");
        System.out.println("/*-----------------------------------------------");
        System.out.println("Testing class Hand...\n");
        cards[0].set('3', Card.Suit.diamonds);
        Hand hand = new Hand();
        int cardToTake = 0;
        while(hand.takeCard(cards[cardToTake])){
            cardToTake++;
            if(cardToTake > 2)
                cardToTake = 0;
        }
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

        System.out.println("/*-----------------------------------------------");
        System.out.println("Testing class deck...");
        Deck deck = new Deck(2);
        System.out.println("Double Pack Unshufled:");
        while(!deck.inspectCard(deck.getTopCard()-1).errorFlag)
            System.out.print(deck.dealCard() + " / ");
        System.out.println("\nDouble Pack Shuffled:");
        deck = new Deck(2);
        deck.shuffle();
        while(!deck.inspectCard(deck.getTopCard()-1).errorFlag)
            System.out.print(deck.dealCard() + " / ");
        System.out.println("\nSingle Pack Unshuffled:");
        deck = new Deck();
        while(!deck.inspectCard(deck.getTopCard()-1).errorFlag)
            System.out.print(deck.dealCard() + " / ");
        System.out.println("\nSingle Pack Shuffled:");
        deck = new Deck();
        deck.shuffle();
        while(!deck.inspectCard(deck.getTopCard()-1).errorFlag)
            System.out.print(deck.dealCard() + " / ");
        System.out.println("-----------------------------------------------*\\\n");
        System.out.println("Feeding hands from a deck...");
        Scanner keyIn = new Scanner(System.in);
        int numPlayers = 0;
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
        while(!deck.inspectCard(deck.getTopCard()-1).errorFlag){
            if(hands[handToDealTo] == null)
                hands[handToDealTo] = new Hand();
            hands[handToDealTo].takeCard(deck.dealCard());
            handToDealTo = (handToDealTo == numPlayers-1) ? 0 : handToDealTo + 1;
        }
        for(int i = 0; i < numPlayers; i++){
            System.out.print("\nHand " + (i+1) + hands[i]);
            hands[i].resetHand();
        }
        System.out.println("\n\nHere are our hands, from a shuffled deck:");
        deck = new Deck();
        deck.shuffle();
        while(!deck.inspectCard(deck.getTopCard()-1).errorFlag){
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
