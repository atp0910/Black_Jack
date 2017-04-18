package com.example.atp0910.black_jack.test;

import com.example.atp0910.black_jack.Hand;
import com.example.atp0910.black_jack.Person;

import junit.framework.TestCase;

/**
 * Created by Will on 4/12/2017.
 */
public class PersonTest extends TestCase {
    String s = "Tester";
    Person p1 = new Person(s + "2");
    String pos = "Testing possibility: ";

    public void testHit() throws Exception {
        System.out.println("Testing Method " + Thread.currentThread().getStackTrace()[1]);
        Person p = new Person(s);
        com.example.atp0910.black_jack.Card c = new com.example.atp0910.black_jack.Card(2,3);
        p.hit(c);
        assertEquals(c,p.getHand().getCard(0));
        System.out.println("Passed");
    }

    public void testNewHand() throws Exception {
        System.out.println("Testing Method " + Thread.currentThread().getStackTrace()[1]);
        Person p = new Person(s);
        final boolean expected = false;
        Hand h = p.getHand();
        p.newHand();
        boolean actual = (h == p.getHand());
        assertEquals(expected,actual);
        System.out.println("Passed");
    }

    public void testGetName() throws Exception {
        System.out.println("Testing Method " + Thread.currentThread().getStackTrace()[1]);
        Person p = new Person(s);
        assertEquals(s,p.getName());
        System.out.println("Passed");
    }

    public void testGetHand() throws Exception {
        System.out.println("Testing Method " + Thread.currentThread().getStackTrace()[1]);
        Person p = new Person(s);
        com.example.atp0910.black_jack.Hand hand = p.getHand();
        for(com.example.atp0910.black_jack.Card c : hand.getCards()){
            assertEquals(null,c);
            System.out.println("Card Passed");
        }
    }

    public void testGetScore() throws Exception {
        System.out.println("Testing Method " + Thread.currentThread().getStackTrace()[1]);
        System.out.println("Running tests for several possible hands in " + p1.getName() + "'s hand.");

        System.out.println(pos + "4 aces in hand");
        p1.getHand().addCard(new com.example.atp0910.black_jack.Card(1,14));
        p1.getHand().addCard(new com.example.atp0910.black_jack.Card(2,14));
        p1.getHand().addCard(new com.example.atp0910.black_jack.Card(3,14));
        p1.getHand().addCard(new com.example.atp0910.black_jack.Card(4,14));
        int actual = p1.getScore();
        assertEquals(14,actual);
        System.out.println("Passed");
        p1.newHand();

        int faceexpected = 20;
        int bjexpected = 21; // ;)
        System.out.println(pos + "2 face cards");
        p1.getHand().addCard(new com.example.atp0910.black_jack.Card(1,11));
        p1.getHand().addCard(new com.example.atp0910.black_jack.Card(2,12));
        assertEquals(faceexpected,p1.getScore());
        System.out.println("Passed");
        p1.newHand();

        System.out.println(pos + "Blackjack (face + ace)");
        p1.getHand().addCard(new com.example.atp0910.black_jack.Card(3,13));
        p1.getHand().addCard(new com.example.atp0910.black_jack.Card(4,14));
        assertEquals(bjexpected,p1.getScore());
        System.out.println("Passed");
        p1.newHand();

        System.out.println(pos + "Blackjack (10 + ace)");
        p1.getHand().addCard(new com.example.atp0910.black_jack.Card(3,10));
        p1.getHand().addCard(new com.example.atp0910.black_jack.Card(4,14));
        assertEquals(bjexpected,p1.getScore());
        System.out.println("Passed");
        p1.newHand();
    }
}