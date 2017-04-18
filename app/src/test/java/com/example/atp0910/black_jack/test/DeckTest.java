package com.example.atp0910.black_jack.test;

import com.example.atp0910.black_jack.Card;

import java.util.Stack;

import junit.framework.TestCase;

/**
 * Created by Will on 4/12/2017.
 */
public class DeckTest extends TestCase {



    public void testShuffle() throws Exception {
        System.out.println("Testing Method " + Thread.currentThread().getStackTrace()[1]);
        com.example.atp0910.black_jack.Deck deck = new com.example.atp0910.black_jack.Deck();
        final boolean expected = false;
        Stack<Card> beforeShuffle = deck.getCards();
        deck.shuffle();
        Stack<Card> afterShuffle = deck.getCards();
        boolean actual = true;
        for(int i = 0; i < deck.getNumCards(); i++){
            if(beforeShuffle.pop() != afterShuffle.pop()){
                actual = false;
                break;
            }
        }

        assertEquals(expected,actual);
        System.out.println("Passed");
    }

    public void testDrawCard() throws Exception {
        System.out.println("Testing Method " + Thread.currentThread().getStackTrace()[1]);
        com.example.atp0910.black_jack.Deck deck = new com.example.atp0910.black_jack.Deck();
        final Card expected = deck.getCards().peek();
        assertEquals(expected,deck.drawCard());
        System.out.println("Passed");
    }

    public void testGetNumCards() throws Exception {
        System.out.println("Testing Method " + Thread.currentThread().getStackTrace()[1]);
        com.example.atp0910.black_jack.Deck deck = new com.example.atp0910.black_jack.Deck();
        final int expected = deck.getCards().size();
        assertEquals(expected, deck.getNumCards());
        System.out.println("Passed");
    }

}