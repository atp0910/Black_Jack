package com.example.atp0910.black_jack;
import java.util.Collections;
import java.util.Stack;
public class Deck 
{
    private final Stack <Card> cards = new Stack();
    public Deck()
    {
        Card card;
        for(int suit = 1; suit <= 4; suit++)
        {
            for(int val = 2; val <= 14; val++)
            {
                card = new Card(suit, val);
                cards.push(card);
            }
        }
    }
    public void shuffle()
    {
        Collections.shuffle(cards);
    }
    public Card drawCard()
    {
        Card drawnCard = cards.pop();
        return drawnCard;
    }
    public int getNumCards()
    {
        return cards.size();
    }
    public Stack<Card> getCards() { return cards; }
}
