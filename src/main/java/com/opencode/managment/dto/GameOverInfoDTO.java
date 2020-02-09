package com.opencode.managment.dto;

import com.opencode.managment.app.Player;

import java.util.ArrayList;

public class GameOverInfoDTO {
    private boolean endOfGame;
    private ArrayList<Player> players;

    public GameOverInfoDTO() {
        endOfGame = false;
        players = new ArrayList<>();
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public boolean isEndOfGame() {
        return endOfGame;
    }

    public void setEndOfGame(boolean endOfGame) {
        this.endOfGame = endOfGame;
    }
}
