package com.opencode.managment.service;

import com.opencode.managment.app.Game;
import com.opencode.managment.app.Lobby;
import com.opencode.managment.app.Player;
import com.opencode.managment.app.bean.GameHistory;
import com.opencode.managment.dto.*;
import com.opencode.managment.exception.CanNotModificateLobbyException;
import com.opencode.managment.exception.LobbyAlreadyCreatedException;
import com.opencode.managment.exception.NoDataException;
import com.opencode.managment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class GameService {
    private Lobby lobby;
    private Game game;

    @Autowired
    private GameHistory gameHistory;

    public void createLobby(LobbyDTO lobbyDTO) {
//        if (lobby != null) {
//            throw new LobbyAlreadyCreatedException();
//        }
        if(game!=null && game.getGameState() == Game.State.GAME_OVER){
            game = null;
        }
        if (game != null) {
            throw new CanNotModificateLobbyException();
        }
        lobby = new Lobby(lobbyDTO);
    }

    public void joinLobby(PlayerDTO playerDTO) {
        if (lobby == null) {
            throw new NoDataException();
        }
        if (game != null) {
            throw new CanNotModificateLobbyException();
        }
        lobby.join(new Player(playerDTO));
        if (lobby.getPlayers().size() >= lobby.getCountOfPlayer()) {
            startGame();
        }
    }

    public void leaveLobby(PlayerDTO playerDTO) {
        if (lobby == null) {
            throw new NoDataException();
        }
        if (game != null) {
            throw new CanNotModificateLobbyException();
        }
        lobby.leave(new Player(playerDTO));
        if (lobby.getPlayers().size() <= 0) {
            lobby = null;
        }
    }

    public LobbyDTO getLobbyInfo() {
        if (lobby == null) {
            throw new NoDataException();
        }
        return new LobbyDTO(lobby);
    }

    public void startGame() {
        if (lobby == null) {
            throw new NoDataException();
        }
        if (lobby.canStartGame()) {
            game = new Game(lobby);
            gameHistory.setGame(game);
        }
    }

    public void destroyGame() {
        game = null;
        lobby = null;
    }

    public void finishStep(FinishStepIntentionDTO finishStepIntentionDTO) {
        if (game == null) {
            throw new NoDataException();
        }
        game.finishStep(finishStepIntentionDTO.getUserName());
    }

    public LobbyStatusDto isLobbyCreated() {
        return new LobbyStatusDto(lobby != null ? 1 : 0);
    }

    public GameDTO getGameInfo() {

        if (gameHistory.getGame() == null) {
            throw new NoDataException();
        }
        return GameDTO.createGameInfo(gameHistory.getGame());
    }

    public void buyEsm(BuyEsmDTO buyEsmDTO) {
        if (game == null) {
            throw new NoDataException();
        }
        game.buyEsm(buyEsmDTO);
    }

    public void sellEgp(SellEgpDTO sellEgpDTO) {
        if (game == null) {
            throw new NoDataException();
        }
        game.sellEgp(sellEgpDTO);
    }

    public void convertFactory(BuildOrModIntentionDTO buildOrModIntentionDTO) {
        if (game == null) {
            throw new NoDataException();
        }
        game.convertFactory(buildOrModIntentionDTO);
    }

    public void build(BuildOrModIntentionDTO buildOrModIntentionDTO) {
        if (game == null) {
            throw new NoDataException();
        }
        game.build(buildOrModIntentionDTO);
    }

    public void getProduct(ProductConversionIntentionDTO productConversionIntentionDTO) {
        if (game == null) {
            throw new NoDataException();
        }
        game.getProduct(productConversionIntentionDTO);
    }

    public GameOverInfoDTO getGameOverInfo() {
        if (gameHistory.getGame() == null) {
            throw new NoDataException();
        }
        return gameHistory.getLastGameResults();
    }

    public void getLoan(LoanDTO loanDTO) {
        if (game == null) {
            throw new NoDataException();
        }
        game.getLoan(loanDTO);
    }

    public Game getGame() {
        return game;
    }
}
