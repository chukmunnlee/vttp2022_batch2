package vttp2022.day04;

import java.util.Map;
import java.util.Set;
import java.util.Collection;
import java.util.HashMap;

public class MapMain {
    public static void main(String[] args) {

        BankAccount fred = new BankAccount("fred");
        BankAccount barney = new BankAccount("barney");
        BankAccount wilma = new BankAccount("wilma");
        BankAccount betty = new BankAccount("betty");

        // Create a simple Map
        Map<String, BankAccount> accts = new HashMap<>();
        accts.put(fred.getAccountId(), fred);
        accts.put(barney.getAccountId(), barney);
        accts.put(wilma.getAccountId(), wilma);
        accts.put(betty.getAccountId(), betty);

        System.out.printf("size: %d\n", accts.size());
        System.out.printf("has fred: %b\n", accts.containsKey(fred.getAccountId()));
        System.out.printf("has pebbles: %b\n", accts.containsKey("pebbles"));

        Set<String> keys = accts.keySet();
        Collection<BankAccount> values = accts.values();

        BankAccount acct;
        for (String acctId: keys) {
            acct = accts.get(acctId);
            System.out.printf("acctid = %s, name = %s\n", acctId, acct.getName());
        }
    }
}