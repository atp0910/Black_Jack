package com.example.atp0910.black_jack;
/**
 *
 * @author atp0910
 */
public class Person 
{
    private Hand hand;
    private String name;
    public Person(String name)
    {
        hand = new Hand();
        this.name = name;
    }
    public void hit(Card card)
    {
        hand.addCard(card);
    }
    public void newHand()
    {
        hand = new Hand();
    }
    public String getName()
    {
        return name;
    }
    public Hand getHand()
    {
        return hand;
    }
    public int getScore()
    {
        int score = 0;
        Card [] cards = hand.getCards();
        for(int i = 0; i < 5; i++)
        {
            if(cards[i] == null)
            {
                break;
            }
            int faceVal = cards[i].getVal();
            if(faceVal == 14)
            {
                score += 11;
                if(score > 21)
                {
                    score -= 10;
                }
            }
            else if(faceVal > 10)
            {
                score += 10;
            }
            else
            {
                score += faceVal;
            }
        }
        return score;
    }
}
