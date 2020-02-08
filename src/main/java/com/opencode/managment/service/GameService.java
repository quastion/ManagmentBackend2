package com.opencode.managment.service;

import com.opencode.managment.app.Game;
import com.opencode.managment.app.Lobby;
import com.opencode.managment.app.Player;
import com.opencode.managment.dto.*;
import com.opencode.managment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    private UserRepository userRepository;
    private Lobby lobby;
    private Game game;

    @Autowired
    public void setRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createLobby(LobbyDTO lobbyDTO){
        lobby = new Lobby(lobbyDTO);
    }

    public void joinLobby(PlayerDTO playerDTO){
        if(isLobbyCreated().getStatus() == 0) return;
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

    public void finishStep(FinishStepIntentionDTO finishStepIntentionDTO){
        game.finishStep(finishStepIntentionDTO.getUserName());
    }

    public LobbyStatusDto isLobbyCreated(){
        return new LobbyStatusDto(lobby != null ? 1 : 0);
    }

    public GameDTO getGameInfo(){
        if(game == null) throw new Error("Игра не создана!");
        return GameDTO.createGameInfo(game);
    }

    public void buyEsm(BuyEsmDTO buyEsmDTO){
        game.buyEsm(buyEsmDTO);
    }

    public void sellEgp(SellEgpDTO sellEgpDTO){
        game.sellEgp(sellEgpDTO);
    }

    public void convertFactory(BuildOrModIntentionDTO buildOrModIntentionDTO){
        game.convertFactory(buildOrModIntentionDTO);
    }

    public void build(BuildOrModIntentionDTO buildOrModIntentionDTO){
        game.build(buildOrModIntentionDTO);
    }

    // TODO: 08.02.2020 Реализовать 
    public void getProduct( ProductConversionIntentionDTO productConversionIntentionDTO){
        
    }

    // TODO: 08.02.2020 Реализовать получение ссуды
}
