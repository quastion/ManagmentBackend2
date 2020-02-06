package com.opencode.managment.service;

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
}
