package model;

import model.exception.EmptyDeckException;
import model.exception.RoundNotReadyException;

import java.util.ArrayList;
import java.util.LinkedList;

public class Dealer {
    private final Deck mainDeck;
    private final Deck secondaryDeck;
    private final ArrayList<Player> players;
    private int roundCounter = 0;

    public Dealer(ArrayList<Player> players) {
        this.mainDeck = new Deck(16);
        this.secondaryDeck = new Deck(6);
        this.players = players;
    }

    public Deck getMainDeck() { return mainDeck; }
    public Deck getSecondaryDeck() { return secondaryDeck; }
    public ArrayList<Player> getPlayers() { return players; }

    public void deal() throws EmptyDeckException {
        if (!mainDeck.hasEnough(players.size())) {
            throw new EmptyDeckException("Not enough cards in main deck to deal!");
        }
        if (!secondaryDeck.hasEnough(1)) {
            throw new EmptyDeckException("Not enough cards in secondary deck to deal!");
        }
        roundCounter++;
        for (Player player : players) {
            LinkedList<Card> deal = new LinkedList<>();
            deal.add(this.mainDeck.removeCard());
            if ((roundCounter-1)%players.size() == players.indexOf(player)) {
                deal.add(this.secondaryDeck.removeCard());
            }
            player.deal(deal);
        }
    }

    public void play() throws RoundNotReadyException {
        if (!everyPlayerSelected()) {
            throw new RoundNotReadyException("All players must select a card from their hand!");
        }
        for (Player player : players) {
            Player nextPlayer;
            if (players.indexOf(player) == players.size() - 1) {
                nextPlayer = players.get(0);
            }
            else {
                nextPlayer = players.get(players.indexOf(player) + 1);
            }
            player.play(nextPlayer);
        }
    }

    private boolean everyPlayerSelected() {
        for (Player player : players) {
            if (!player.hasSelected()) {
                return false;
            }
        }
        return true;
    }

}
