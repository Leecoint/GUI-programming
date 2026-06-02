package model;

import java.util.ArrayList;

public class LoginModel {
    private final int MAX_PLAYERS = 4;
    private final ArrayList<Player> players;
    public LoginModel() {
        players = new ArrayList<>();
    }
    public void addToGame(Player player) {
        if (players.size() == MAX_PLAYERS) {
            throw new RuntimeException("Too many players");
        }
        players.add(player);
    }
    public ArrayList<Player> getPlayers() {
        return players;
    }
}

