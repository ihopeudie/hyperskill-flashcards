package flashcards.dao;

import flashcards.domain.Card;

import java.util.*;
import java.util.stream.Collectors;

public class Storage {
    private static Storage instance;
    private final List<Card> cards;

    private Storage() {
        this.cards = new ArrayList<>();
    }

    public int size() {
        return cards.size();
    }

    public List<Card> list() {
        return Collections.unmodifiableList(cards);
    }

    public void add(Card card) {
        Card cardToAdd = findCardByTerm(card.getTerm());
        if (cardToAdd != null) {
            cardToAdd.setDefinition(card.getDefinition());
            cardToAdd.setErrorCounter(card.getErrorCounter());
            return;
        }
        cardToAdd = new Card(card.getTerm(), card.getDefinition(), card.getErrorCounter());
        this.cards.add(cardToAdd);
    }

    public void remove(Card card) {
        this.cards.remove(card);
    }

    public int addAll(List<Card> cards) {
        cards.forEach(this::add);
        return cards.size();
    }

    public boolean hasCardWithTerm(String str) {
        return cards
                .stream()
                .anyMatch(card -> card.getTerm().equals(str));
    }

    public boolean hasCardWithDefinition(String str) {
        return cards
                .stream()
                .anyMatch(card -> card.getDefinition().equals(str));
    }


    public static Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }

    public boolean removeByTerm(String term) {
        Card card = findCardByTerm(term);
        if (card != null) {
            remove(card);
        }
        return card != null;
    }

    private Card findCardByTerm(String term) {
        return cards
                .stream()
                .filter(c -> c.getTerm().equalsIgnoreCase(term))
                .findFirst().orElse(null);
    }

    public String findTermByDefinition(String definition) {
        Card card = cards
                .stream()
                .filter(c -> c.getDefinition().equalsIgnoreCase(definition))
                .findFirst().orElse(null);
        return card != null ? card.getTerm() : null;
    }

    public Card getRandomCard() {
        Random random = new Random();
        return cards.get(random.nextInt(size()));
    }

    public List<Card> getHardestCards() {
        int max = cards.stream()
                .max(Comparator.comparingInt(Card::getErrorCounter)).map(Card::getErrorCounter).orElse(0);
        if (max == 0) {
            return Collections.emptyList();
        }
        return cards.stream().filter(c->c.getErrorCounter() == max).collect(Collectors.toList());
    }

    public void resetStats() {
        cards.forEach(card -> card.setErrorCounter(0));
    }
}
