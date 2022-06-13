package vttp2022.day06.cards;

import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import vttp2022.day06.cards.Card.Suit;

public class Deck {

    private List<Card> cards = new LinkedList<>();

    public Deck() {
        // Call the other constructor 
        //with the number of decks to create
        this(1);
    }
    public Deck(int numDecks) {
        // Create the number of decks
        for (int d = 0; d < numDecks; d++) {
            for (Suit s: Suit.values()) {
                for (int i = 0; i < Card.CARD_NAMES.length; i++) {
                    Card card = new Card(s, Card.CARD_NAMES[i], Card.CARD_VALUE[i]);
                    cards.add(card);
                }
            }
        }
    }

    public void shuffle() {
        // Create a random number generartor with a seed
        //Random rand = new Random(System.currentTimeMillis());
        // Better random number generator
        Random rand = new SecureRandom();
        for (int p = 0; p < cards.size(); p++) {
            Card srcCard = cards.get(p);
            int idx = rand.nextInt(0, cards.size());
            Card toSwap = cards.get(idx);
            cards.set(p, toSwap);
            cards.set(idx, srcCard);
        }
    }

    public Card take() {
        return cards.remove(0);
    }

    public List<Card> take(int num) {
        List<Card> cards = new LinkedList<>();
        for (int i = 0; i < num; i++) {
            if (remaining() > 0)
                cards.add(take());
            else 
                break;
        }
        return cards;
    }

    public int remaining() {
        return cards.size();
    }

    @Override
    public String toString() {
        return cards.toString();
    }
    
}
