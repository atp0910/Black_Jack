package com.example.atp0910.black_jack.test;

import junit.framework.TestCase;
import com.example.atp0910.black_jack.Card;

/**
 * Created by Will on 4/12/2017.
 */
public class CardTest extends TestCase {

    public void testGetSuit() throws Exception {
        System.out.println("Testing Method " + Thread.currentThread().getStackTrace()[1]);
        Card c = new Card(2,3);
        assertEquals(2,c.getSuit());
        System.out.println("Passed");
    }

    public void testGetVal() throws Exception {
        System.out.println("Testing Method " + Thread.currentThread().getStackTrace()[1]);
        Card c = new Card(2,3);
        assertEquals(3,c.getVal());
        System.out.println("Passed");
    }

    public void testImageValue() throws Exception {
        System.out.println("Testing Method " + Thread.currentThread().getStackTrace()[1]);
        Card c = new Card(2,3);
        assertEquals(14,c.imageValue());
        System.out.println("Passed");
    }

}