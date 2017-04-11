package com.example.atp0910.black_jack;

/**
 *
 * @author atp0910
 */
public class Dealer extends Person
{
    private Deck deck;
    public Dealer(String name)
    {
        super(name);
        deck = new Deck();
        deck.shuffle();
    }
    public Deck getDeck()
    {
        return deck;
    }
    public Card dealCard()
    {
        Card card = deck.drawCard();
        return card;
    }
    public void shuffleDeck()
    {
        deck.shuffle();
    }
    public void freshDeck()
    {
        deck = new Deck();
        shuffleDeck();
    }
}
