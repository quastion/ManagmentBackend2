package com.opencode.managment.service;

import com.opencode.managment.app.Game;
import com.opencode.managment.app.Lobby;
import com.opencode.managment.app.Player;
import com.opencode.managment.dto.*;
import com.opencode.managment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

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
        if(lobby.getPlayers().size() >= lobby.getCountOfPlayer()){
            startGame();
        }
    }

    public LobbyDTO getLobbyInfo(){
        return new LobbyDTO(lobby);
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
        GameDTO gameDTO = new GameDTO();
        if(game != null) gameDTO = GameDTO.createGameInfo(game);
        return gameDTO;
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

    public void getProduct(ProductConversionIntentionDTO productConversionIntentionDTO){
        game.getProduct(productConversionIntentionDTO);
    }

    public GameOverInfoDTO getGameOverInfo(){
        return game.getGameOverInfo();
    }

    public void getLoan( LoanDTO loanDTO){
        game.getLoan(loanDTO);
    }
}
