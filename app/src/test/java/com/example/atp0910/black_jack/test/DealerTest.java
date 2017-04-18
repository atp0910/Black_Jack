package com.example.atp0910.black_jack.test;

import junit.framework.TestCase;
import com.example.atp0910.black_jack.Dealer;

/**
 * Created by Will on 4/12/2017.
 */
public class DealerTest extends TestCase {
    public void testGetDeck() throws Exception {
        System.out.println("Testing Method " + Thread.currentThread().getStackTrace()[1]);
        Dealer d = new Dealer("TestDealer");
        com.example.atp0910.black_jack.Deck deck = new com.example.atp0910.black_jack.Deck();
        //Making sure they have same number of cards
        assertEquals(deck.getNumCards(),d.getDeck().getNumCards());
        System.out.println("Passed");
    }

    public void testDealCard() throws Exception {
        System.out.println("Testing Method " + Thread.currentThread().getStackTrace()[1]);
        Dealer d = new Dealer("TestDealer");
        com.example.atp0910.black_jack.Card expected = d.getDeck().getCards().peek();
        com.example.atp0910.black_jack.Card actual = d.dealCard();
        assertEquals(expected,actual);
        System.out.println("Passed");
    }

    public void testShuffleDeck() throws Exception {
        System.out.println("Testing Method " + Thread.currentThread().getStackTrace()[1]);
        Dealer d = new Dealer("TestDealer");
        com.example.atp0910.black_jack.Deck deckBefore = d.getDeck();
        d.shuffleDeck();
        assertEquals(false,(d.getDeck().drawCard()==deckBefore.drawCard()));
        System.out.println("Passed");
    }

    public void testFreshDeck() throws Exception {
        System.out.println("Testing Method " + Thread.currentThread().getStackTrace()[1]);
        Dealer d = new Dealer("TestDealer");
        com.example.atp0910.black_jack.Deck deckBefore = d.getDeck();
        d.freshDeck();
        assertEquals(false,(d.getDeck()==deckBefore));
        System.out.println("Passed");
    }

}