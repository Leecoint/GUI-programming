package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Deck {
    private final ObservableList<Card> cards;
    public Deck(int size) {
        ArrayList<Card> temp = CardLibrary.ALL_CARDS.subList(0, size).stream().map(Card::copy).collect(Collectors.toCollection(ArrayList::new));
        this.cards = FXCollections.observableArrayList(temp);
    }

    public Card removeCard() {
        Card top = cards.get(cards.size()-1);
        cards.remove(top);
        return top;
    }

    public ObservableList<Card> getCards() {
        return cards;
    }

    public boolean hasEnough(int amountNeeded) {
        return cards.size() >= amountNeeded;
    }
}
