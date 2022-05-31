package vttp2022.day02;

// import the collection framework
import java.util.*;

public class DataStructure {

    public static void main(String[] args) {

        // Create a list of Integer 
        // List - generics
        List<Integer> intList = new LinkedList<Integer>();
        intList.add(Integer.parseInt("42"));
        intList.add(10);
        intList.add(50);
        intList.add(2, 15);

        System.out.println(intList);

        for (int i = 0; i < intList.size(); i++) {
            System.out.printf("%d = %d\n", i, intList.get(i));
        }

    }
    
}
