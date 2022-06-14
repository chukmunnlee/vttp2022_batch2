package vttp2022.day07;

import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ColMain {

    public static void main(String[] args) {

        Random rand = new SecureRandom();
        List<Integer> myList = new LinkedList<>();
        for (int i = 0; i < 100; i++)
            myList.add(rand.nextInt(100));

        //System.out.println(myList);

        int result = 0;
        for (int i = 0; i < myList.size(); i++) {
            int v = myList.get(i);
            // filter
            if (0 != (v % 2))
                continue;
            // map
            //System.out.printf("for %d\n", myList.get(i) + 1);
            result = result + myList.get(i);
        }

        System.out.println("result = " + result);

        List<Integer> intList = myList.stream()
            .filter(v -> 0 == (v % 2))
            .map(v -> v + 1)
            .toList();
        System.out.println(intList);

        int sumOfMyList = myList.stream()
            .filter(v -> 0 == (v % 2)) // stream
            .map(v -> v + 1) // stream
            .reduce(0, (acc, v) -> {
                //System.out.printf("x: %d, y: %d\n", x, y);
                return acc + v;
            });

        System.out.printf("sumOfMyList: %d\n", sumOfMyList);


            //.forEach(value -> {
            //    System.out.printf("forEach %d\n", value);
            //});

    }
    
}
