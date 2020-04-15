package app;

import java.util.Random;

public class WashCard{
    private String id;
    private double balance;

    public WashCard(String id){
        Random rand = new Random();
        this.id = id;
        this.balance = (rand.nextInt(10) + 1) * 100;
    }

    public WashCard(String id, double balance){
        this.id = id;
        this.balance = balance;
    }

    public double getBalance() {
        return this.balance;
    }

    public void addAmount(double amount){
        if(amount > 0){
            this.balance += amount;
        }
    }

    public void withdraw(double amount){
        if(amount < this.balance){
            this.balance -= amount;
        }
    }

    public String getId(){
        return this.id;
    }

    public String toString(){
        return String.format("Card Id: %s\nCurrent Balance: %.2f kr.", this.id, this.balance);
    }
}