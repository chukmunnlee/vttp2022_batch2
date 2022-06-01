package vttp2022.day02.workshop;

public class Main {

    public static void main(String[] args) {
        BankAccount acct = new BankAccount();
        System.out.printf("name: %s\n", acct.getName());
        System.out.printf("acctId: %s\n", acct.getAcctId());
        System.out.printf("balance: %f\n", acct.getBalance());
    }
    
}
