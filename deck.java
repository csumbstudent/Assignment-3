//Deck of Cards Assignment
import java.io.*;
import java.util.*;
import java.lang.*;

class Card
{
	private char value;
	private Suit suit;
	private State state1;
	private boolean errorFlag;
	public enum Suit { diamonds, clubs, spades, hearts } ;
	public enum State { delete, active };
	public static char[] VALIDVALUES = {'2', '3','4','5','6','7','8','9', '10', 'J', 'Q', 'K', 'A'};
	
	public Card(char value, Suit suit)
	{
		set(value, suit);
		
	}
	public Card()
	{
		this('A', Suit.spades);
	}
	
	public boolean set (char value, Suit suit)
	{
		errorFlag = true;
		char upVal1 = Character.toUpperCase(value);
		
		if (testValue(upVal1))
		{
			errorFlag = false;
			this.value = upVal1;
			this.suit = suit;
		}
		else {}
		return !errorFlag;
		
	}
	
	public State getState()
	{
		return state1;
	}
	
	public void setState(State state1)
	{
		this.state1 = state1;
	}
	
	public Suit getSuit()
	{
		return suit;
	}
	
	public char getValue()
	{
		return value;
	}
	
	public boolean getErrorFlag()
	{
		return errorFlag;
	}
	
	public boolean equals(Card card1)
	{
		return (card1.getState() == state1 && card1.getSuit() == suit && card1.getValue() == value);
	}
	
	private boolean testValue(char upVal1)
	{
		for (char c1: VALIDVALUES)
		{
			if (upVal1 == c1) 
			{
				return true;
			}
			return false;
		}
	}
	
	public String toString()
	{
		String A_str;
		
		if (errorFlag == true)
		{
			A_STR = "invalid";
			
		}
		else if (state1 == State.deleted)
		{
			A_str = "(deleted)";
		}
		else
		{
			A_str = value +  " of" + suit;
		}
		return A_str;
	}
}

class Deck
{
	public static int MAXCARDS = 52;
	public static Card[] MASTERDECK = new CARD[MAXCARDS];
	private Card[] cards;
	private int topCard;
	private int numPacks;
	
	public Deck (int num_Packs)
	{
		populateMaster();
		this.numPacks = numPacks;
		init(this.numPacks);
		
	}
	
	public Deck()
	{
		this(1);
	}
	
	public void init(int numPacks)
	{
		cards = new Card[numPacks * MAXCARDS];
		for (int i = 0; i < numPacks; i++)
		{
			for (int j = 0; j < MAXCARDS; j++)
			{
				cards[i * MAXCARDS + j] = MASTERDECK[j];
			}
		}
		topCard = numPacks * MAXCARDS - 1;
	}
	
	public String toSTring()
	{
		String Astr = "";
		for (int i = 0; i < topCard +1; i++)
		{
			Astr = Astr + cards[i] + " / ";
		}
		return Astr + "\n";
	}
	
	public void shuffle()
	{
		Random r = new Random();
		for (int i = 0; i <= topCard; i++)
		{
			int r1 = r.nextInt(topCard);
			Card temp1 = cards[i] = cards[r1];
			cards[r1] = temp1;
		}
	}
	
	public Card dealCard()
	{
		Card card;
		if(topCard >= 0)
		{
			card = card[topCard];
			topCard--;
		}
		else
		{
			card = null;
		}
		return card;
	}
	
	public boolean removeCard(Card A_card)
	{
		int shift = 0;
		for (int i = 0; i < cards.length; i++)
		{
			if (cards[i].equals(Acard))
				System.out.printlin(cards[i]);
			shift++;
		}
		
		else if (shift != 0)
		{
			cards[i-shift] = cards[i];
		}
		topCard = topCard - shift;
		return (shift != 0);
	}
	
	public boolean addCard(Card card)
	{
		int cardCount = 0;
		for (int i = 0; i < topCard + 1; i++)
		{
			if (cards[i].equals(card))
			{
				cardCount++;
			}
			
			if (cardCount < numPacks)
			{
				topCard++;
				card[topCard] = card;
				return true;
			}
			else (return false;)
		}
	}
	
	public int getNumCards()
	{
		return topCard + 1;
	}
	
	public Card inspectCard(int i)
	{
		Card cad1;
		if (i > topCard)
		{
			card = new Card("Z", Card.Suit.spades);
		}
		else
		{
			card = card[i];
		}
		return card;
	}
	
	private static void populateMaster()
	{
		for (int i = 0; i < Card.ValidValues.length-1; i++)
		{
			for (int j = 0; j < Card.Suit.values().length; j++)
			{
				MASTERDECK[j * (Card.ValidValues.length - 1) + i] = new Card(Card.VALIDVALUES[i], Card.Suit.values()[i]);
			}
		}
	}
}
