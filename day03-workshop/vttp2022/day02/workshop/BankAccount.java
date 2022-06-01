package vttp2022.day02.workshop;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class BankAccount {

    private String name = "";
    private String acctId = UUID.randomUUID().toString().substring(0,8);
    private float balance = 0;
    private List<String> transaction = new LinkedList<>();

    //public BankAccount() { }

    public BankAccount(String name) {
        this.name = name;
    }
    public BankAccount(String name, float initialBal) {
        this.name = name;
        this.balance = initialBal;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getAcctId() {
        return acctId;
    }
    //public void setAcctId(String acctId) {
    //    this.acctId = acctId;
    //}

    public float getBalance() {
        return balance * .0001f;
    }
    public void setBalance(float balance) {
        this.balance = balance;
    }

    public List<String> getTransaction() {
        return transaction;
    }
    public void setTransaction(List<String> transaction) {
        this.transaction = transaction;
    }

    
    
}
