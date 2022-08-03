package vttp2022.ssf.day17.services;

import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class DiceService {

    private Random rand = new SecureRandom();

    public Integer roll() {
        return roll(1).get(0);
    }
    public List<Integer> roll(int count) {
        List<Integer> rolls = new LinkedList<>();
        for (int i = 0; i < count; i++)
            rolls.add(rand.nextInt(1, 7));
        return rolls;
    }
}
