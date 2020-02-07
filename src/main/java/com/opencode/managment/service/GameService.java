package com.opencode.managment.service;

import com.opencode.managment.app.Game;
import com.opencode.managment.app.Lobby;
import com.opencode.managment.app.Player;
import com.opencode.managment.dto.LobbyDTO;
import com.opencode.managment.dto.PlayerDTO;
import com.opencode.managment.repository.GameSessionRepository;
import com.opencode.managment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    private GameSessionRepository gameSessionRepository;
    private UserRepository userRepository;
    private Lobby lobby;
    private Game game;

    @Autowired
    public void setProductRepository(GameSessionRepository gameSessionRepository) {
        this.gameSessionRepository = gameSessionRepository;
    }

    @Autowired
    public void setRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createLobby(LobbyDTO lobbyDTO){
        lobby = new Lobby(lobbyDTO);
    }

    public void joinLobby(PlayerDTO playerDTO){
        if(lobby == null) return;
        lobby.join(new Player(playerDTO));
    }

    public void startGame(){
        if(lobby.canStartGame()){
            game = new Game(lobby);
        }
    }

    public void destroyGame(){
        game = null;
        lobby = null;
    }

    public void finishStep(String playerName){
        game.finishStep(playerName);
    }
}
