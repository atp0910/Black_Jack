package com.example.atp0910.black_jack.test;

import junit.framework.TestCase;
import com.example.atp0910.black_jack.Hand;

/**
 * Created by Will on 4/12/2017.
 */
public class HandTest extends TestCase {
    public void testAddCard() throws Exception {
        System.out.println("Testing Method " + Thread.currentThread().getStackTrace()[1]);
        Hand h = new Hand();
        com.example.atp0910.black_jack.Card c = new com.example.atp0910.black_jack.Card(2,10);
        h.addCard(c);
        assertEquals(c,h.getCard(0));
        System.out.println("Passed");
    }

    public void testGetCount() throws Exception {
        System.out.println("Testing Method " + Thread.currentThread().getStackTrace()[1]);
        Hand h = new Hand();
        assertEquals(0,h.getCount());
        System.out.println("Passed");
    }

    public void testGetCards() throws Exception {
        System.out.println("Testing Method " + Thread.currentThread().getStackTrace()[1]);
        Hand h = new Hand();
        for(com.example.atp0910.black_jack.Card c : h.getCards()){
            assertEquals(null,c);
            System.out.println("Card Passed");
        }
    }

    public void testGetCard() throws Exception {
        System.out.println("Testing Method " + Thread.currentThread().getStackTrace()[1]);
        Hand h = new Hand();
        h.addCard(new com.example.atp0910.black_jack.Card(1,2));
        com.example.atp0910.black_jack.Card expected = new com.example.atp0910.black_jack.Card(2,3);
        h.addCard(expected);
        assertEquals(expected,h.getCard(1));
        System.out.println("Passed");
    }

}