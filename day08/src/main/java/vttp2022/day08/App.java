package vttp2022.day08;

import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) {
        List<Integer> intList = new LinkedList<>();
        Random rand = new SecureRandom();
        for (int i = 0; i < 1000; i++)
            intList.add(rand.nextInt());

        intList.stream()
            .map(v -> v * 2)
            .filter(v -> v > 0)
            .sorted()
            .limit(10)
            .forEach(v -> {
                System.out.printf("%d ", v);
            });
    }
}
