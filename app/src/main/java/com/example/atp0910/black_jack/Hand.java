package com.example.atp0910.black_jack;
/**
 *
 * @author atp0910
 */
public class Hand 
{
    private Card [] cards = new Card [5];
    public Hand()
    {
        cards[0] = null;
        cards[1] = null;
        cards[2] = null;
        cards[3] = null;
        cards[4] = null;
    }
    public void addCard(Card card)
    {
        for(int i = 0; i < 5; i++)
        {
            if(cards[i] == null)
            {
                cards[i] = card;
                break;
            }
        }
    }
    public void removeCard(int i)
    {
        {
            cards[i] = null;
        }
    }
    public int getCount()
    {
        int count = 0;
        for(Card card: cards)
        {
            if(card != null)
            {
                count++;
            }
        }
        return count;
    }
    public Card [] getCards()
    {
        return cards;
    }
    public Card getCard(int i){return cards[i];}
    @Override
    public String toString()
    {
        return "s";
    }
}
