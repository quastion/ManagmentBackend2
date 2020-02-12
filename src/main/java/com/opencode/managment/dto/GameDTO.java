package com.opencode.managment.dto;

import com.opencode.managment.app.Game;
import com.opencode.managment.app.Player;
import lombok.Data;

import java.util.ArrayList;

/**
 * Контейнер для данных о игре
 */
@Data
public class GameDTO {
    private int month; // текущий месяц (круг)
    private int crownPlayer; // порядковый номер игрока с короной
    private int currentPlayer; // текущий игрок, который ходит
    private int numberEsm; // сколько ЕСМ банк продает
    private int costEsm; // минимальная цена покупки ЕСМ
    private int numberEgp; // сколько ЕГП банк покупает
    private int costEGP; // максимальная цена за которую покупает ЕГП
    private ArrayList<PlayerDTO> players;

    public static GameDTO createGameInfo(Game game){
        GameDTO gameDTO = new GameDTO();
        gameDTO.setMonth(game.getMonth());
        gameDTO.setCrownPlayer(game.getCrownPlayer());
        gameDTO.setCurrentPlayer(game.getCurrentPlayer());
        gameDTO.setNumberEsm(game.getBank().getEsmNumber(game.getPlayersCount()));
        gameDTO.setCostEsm(game.getBank().getEsmCost());
        gameDTO.setNumberEgp(game.getBank().getEgpNumber(game.getPlayersCount()));
        gameDTO.setCostEGP(game.getBank().getEgpCost());

        ArrayList<PlayerDTO> playerDTOS = new ArrayList<>();
        for (int i = 0; i < game.getPlayersCount(); i++){
            Player player = game.getPlayers().get(i);
            PlayerDTO playerDTO = new PlayerDTO();
            playerDTO.setPlayer(player.getUserName());
            playerDTO.setAva(player.getUser().getUserAvatar());
            playerDTO.setMoney(player.getMoney());
            playerDTO.setSuperFactory(player.getAutomatedFactoriesCount());
            playerDTO.setFactory(player.getStandardFactoriesCount());
            playerDTO.setEsm(player.getEsm());
            playerDTO.setEgp(player.getEgp());
            playerDTO.setIsCrownPlayer(player.getIsCrownPlayer());
            playerDTO.setAvailableLoansCount(player.getAvailableLoansCount());
            playerDTO.setIsLoan(player.isLoan());
            playerDTO.setOutstandingLoan(player.getOutstandingLoan());
            playerDTO.setLoanRepaymentTime(player.getLoanRepaymentTime());
            playerDTO.setNumberInLobby(player.getNumberInLobby());
            playerDTO.setTime(Player.TIME_TO_STEP);
            playerDTOS.add(playerDTO);
        }
        gameDTO.setPlayers(playerDTOS);

        return gameDTO;
    }
}
