package com.example.atp0910.black_jack;

/**
 *
 * @author atp0910
 */
public class Player extends Person {
    private double money;
    private int wins;
    private int losses;
    public boolean insurance;
    public Player(String name)
    {
        super(name);
        wins = 0;
        losses = 0;
        money = 100.00;
        insurance = false;
    }
    public double getMoneyAmount()
    {
        return money;
    }
    public double bet(double amount)
    {
        money -= amount;
        return amount;
    }
    public void winMoney(double amount)
    {
        money += amount;
    }
    public int getWins()
    {
        return wins;
    }
    public int getLosses()
    {
        return losses;
    }
    public void addWin() {
        wins++;
    }

    public void addLoss() {
        losses++;
    }
}
