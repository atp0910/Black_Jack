package com.example.atp0910.black_jack;

/**
 *
 * @author atp0910
 */
public class Player extends Person {
    private double money;
    private int wins;
    private int losses;

    public Player(String name) {
        super(name);
        wins = 0;
        losses = 0;
        money = 100.00;
    }

    public void bet(double amount) {
        money -= amount;
    }

    public void win() {
        wins++;
    }

    public void lose() {
        losses++;
    }
}
