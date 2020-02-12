package com.opencode.managment.service;

import com.opencode.managment.app.Game;
import com.opencode.managment.app.Lobby;
import com.opencode.managment.app.Player;
import com.opencode.managment.dto.*;
import com.opencode.managment.exception.LobbyAlreadyCreatedException;
import com.opencode.managment.exception.NoDataException;
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
        if(lobby != null){
            throw new LobbyAlreadyCreatedException();
        }
        lobby = new Lobby(lobbyDTO);
    }

    public void joinLobby(PlayerDTO playerDTO){
        if(lobby == null){
            throw new NoDataException();
        }
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
        if(lobby == null){
            throw new NoDataException();
        }
        if(lobby.canStartGame()){
            game = new Game(lobby);
        }
    }

    public void destroyGame(){
        game = null;
        lobby = null;
    }

    public void finishStep(FinishStepIntentionDTO finishStepIntentionDTO){
        if(game == null){
            throw new NoDataException();
        }
        game.finishStep(finishStepIntentionDTO.getUserName());
    }

    public LobbyStatusDto isLobbyCreated(){
        return new LobbyStatusDto(lobby != null ? 1 : 0);
    }

    public GameDTO getGameInfo(){
        if(game == null){
            throw new NoDataException();
        }
        return GameDTO.createGameInfo(game);
    }

    public void buyEsm(BuyEsmDTO buyEsmDTO){
        if(game == null){
            throw new NoDataException();
        }
        game.buyEsm(buyEsmDTO);
    }

    public void sellEgp(SellEgpDTO sellEgpDTO){
        if(game == null){
            throw new NoDataException();
        }
        game.sellEgp(sellEgpDTO);
    }

    public void convertFactory(BuildOrModIntentionDTO buildOrModIntentionDTO){
        if(game == null){
            throw new NoDataException();
        }
        game.convertFactory(buildOrModIntentionDTO);
    }

    public void build(BuildOrModIntentionDTO buildOrModIntentionDTO){
        if(game == null){
            throw new NoDataException();
        }
        game.build(buildOrModIntentionDTO);
    }

    public void getProduct(ProductConversionIntentionDTO productConversionIntentionDTO){
        if(game == null){
            throw new NoDataException();
        }
        game.getProduct(productConversionIntentionDTO);
    }

    public GameOverInfoDTO getGameOverInfo(){
        if(game == null){
            throw new NoDataException();
        }
        return game.getGameOverInfo();
    }

    public void getLoan( LoanDTO loanDTO){
        if(game == null){
            throw new NoDataException();
        }
        game.getLoan(loanDTO);
    }
}
