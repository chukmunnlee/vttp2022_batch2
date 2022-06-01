package vttp2022.day02.workshop;

public class FixedDepositAccount extends BankAccount {

    private float interest = 0;
    private int duration = 0;

    public FixedDepositAccount(String name, float initialBal) {
        // call the parent's class default constructor
        super(name, initialBal);
    }
    public FixedDepositAccount(String name, float initialBal, float interest) {
        super(name, initialBal); // ..
        this.interest = interest;
    }
    public FixedDepositAccount(String name, float initialBal, float interest, int duration) {
        this(name, initialBal, initialBal); // .
        this.duration = duration;
    }

    @Override
    public float getBalance() {
       return super.getBalance() * .1f; 
    }
    
}
