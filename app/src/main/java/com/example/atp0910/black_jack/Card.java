package com.example.atp0910.black_jack;
/**
 *
 * @author atp0910
 */
public class Card 
{
    /*
        1  = Hearts
        2  = Diamonds
        3  = Clubs
        4  = Spades
        ----------
        11 = Jack
        12 = Queen
        13 = King
        14 = Ace
    */
    private final int suit;
    private final int faceVal;
    public Card(int suit, int val)
    {
        this.suit = suit;
        this.faceVal = val;
    }
    public int getSuit()
    {
        return suit;
    }
    public int getVal()
    {
        return faceVal;
    }
    public int imageValue() {
        int num = faceVal - 2; //card image values start at zero
        switch (suit) {
            case 1:
                num += 0;
                break;    //hearts = 0 - 12
            case 2:
                num += 13;
                break;   //diamonds = 13 - 25
            case 3:
                num += 26;
                break;   //clubs = 26 - 38
            case 4:
                num += 39;
                break;   //spades = 39 - 51
        }
        return num;
    }
    public String toString()
    {
        return "x"+imageValue();
    }
}
