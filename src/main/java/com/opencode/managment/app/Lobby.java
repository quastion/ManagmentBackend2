package com.opencode.managment.app;

import com.opencode.managment.dto.LobbyDTO;

import java.util.ArrayList;

public class Lobby {
    private ArrayList<Player> players;
    private String lobbyName;
    private int countOfPlayer;
    private String creator;

    {
        players = new ArrayList<>();
    }

    public Lobby() {}

    public Lobby(LobbyDTO lobbyDTO) {
        lobbyName = lobbyDTO.getLobbyName();
        countOfPlayer = lobbyDTO.getCountOfPlayer();
        creator = lobbyDTO.getCreator();
    }

    public void join(Player player){
        if(!canJoin()) return;
        players.add(player);
    }

    public boolean canJoin(){
        return players.size() < 4;
    }

    public boolean canStartGame(){
        return players.size() >= 2;
    }

    public String getLobbyName() {
        return lobbyName;
    }

    public void setLobbyName(String lobbyName) {
        this.lobbyName = lobbyName;
    }

    public int getCountOfPlayer() {
        return countOfPlayer;
    }

    public void setCountOfPlayer(int countOfPlayer) {
        this.countOfPlayer = countOfPlayer;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
}
