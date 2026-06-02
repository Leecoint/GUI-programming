package model;

import javafx.beans.property.*;
import javafx.collections.*;
import model.exception.FullHandException;

import java.util.LinkedList;

public class Player {
    private static final int MAX_HAND_SIZE = 4;

    private final String name;
    private final ObservableList<Card> hand;
    private final ObservableList<Card> tempHand;
    private final ObjectProperty<Card> selectedCardProperty;
    private final IntegerProperty totalHealthProperty;

    public Player(String name) {
        this.name = name;
        this.hand = FXCollections.observableArrayList();
        this.tempHand = FXCollections.observableArrayList();

        this.selectedCardProperty = new SimpleObjectProperty<>();
        this.totalHealthProperty = new SimpleIntegerProperty(0);

        hand.addListener((ListChangeListener<Card>) change -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    for (Card card : change.getAddedSubList()) {
                        card.healthProperty().addListener((observable, oldValue, newValue) -> calculateHealth());
                    }
                }
            }
            calculateHealth();
        });
    }

    public ObservableList<Card> getHand() {
        return hand;
    }
    public ObservableList<Card> getTempHand() {
        return tempHand;
    }
    public String getName() {
        return name;
    }
    public ReadOnlyObjectProperty<Card> selectedCardProperty() {
        return selectedCardProperty;
    }
    public Card getSelectedCard() {
        return selectedCardProperty.get();
    }
    public ReadOnlyIntegerProperty totalHealthProperty() {
        return totalHealthProperty;
    }

    public void deal(LinkedList<Card> deal) {
        this.tempHand.clear();
        this.tempHand.addAll(deal);
    }

    public void place(Card card) throws FullHandException {
        if (handFull()) {
            throw new FullHandException("Hand is full!");
        }
        tempHand.remove(card);
        hand.add(card);
    }

    public void play(Player nextPlayer) {
        nextPlayer.applyDamage(getSelectedCard());
        selectedCardProperty.set(null);
    }

    public void select(Card card)  {
        selectedCardProperty.set(card);
    }

    public boolean hasSelected() {
        return selectedCardProperty.get() != null;
    }

    private void calculateHealth() {
        int sum = 0;
        for (Card card : hand) {
            sum += card.getHealth();
        }
        totalHealthProperty.set(sum);
    }

    private boolean handFull() {
        return hand.size() >= MAX_HAND_SIZE;
    }

    public void applyDamage(Card card) {
        LinkedList<Card> matches = new LinkedList<Card>();
        for (Card c : hand) {
            c.applyDamage(card);
            if (c.getHealth() <= 0) {
                matches.add(c);
            }
        }
        for (Card c : matches) {
            hand.remove(c);
        }
    }
}
