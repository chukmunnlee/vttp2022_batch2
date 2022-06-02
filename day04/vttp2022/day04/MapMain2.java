package vttp2022.day04;

import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

public class MapMain2 {
    public static void main(String[] args) {

        List<String> names = new LinkedList<>();
        names.add("fred");
        names.add("barney");
        names.add("wilma");
        names.add("betty");

        Map<String, BankAccount> accts = new HashMap<>();

        for (String n: names) {
            BankAccount acct = new BankAccount(n);
            accts.put(acct.getAccountId(), acct);
        }

        Set<String> keys = accts.keySet();
        Collection<BankAccount> values = accts.values();

        BankAccount acct;
        for (String acctId: keys) {
            acct = accts.get(acctId);
            System.out.printf("acctid = %s, name = %s\n", acctId, acct.getName());
        }
    }
}