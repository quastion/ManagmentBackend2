package com.opencode.managment.app;

import com.opencode.managment.app.factory.Factory;
import com.opencode.managment.dto.BuildOrModIntentionDTO;
import com.opencode.managment.dto.BuyEsmDTO;
import com.opencode.managment.dto.PlayerDTO;
import com.opencode.managment.dto.SellEgpDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public final class Game {
    private static final int COST_PER_ESM_UNIT = 300;
    private static final int COST_PER_EGP_UNIT = 500;
    private static final int COST_PER_STANDARD_FACTORY_UNIT = 1000;
    private static final int COST_PER_AUTOMATED_FACTORY_UNIT = 1500;

    private Lobby lobby;
    private Bank bank;

    private HashSet<String> setOfFinishedSteps;
    private int month;
    private int crownPlayer;
    private int currentPlayer;

    public Game(Lobby lobby) {
        this.lobby = lobby;
        setOfFinishedSteps = new HashSet<>();
        bank = new Bank();
    }

    public void step(){
        for(Player player : lobby.getPlayers()){
            payBills(player);
            player.updateState();
        }
        setOfFinishedSteps.clear();
        increaseCurrentPlayerNumber();
        increaseMonth();
        bank.holdTenders(getPlayersCount());
        bank.randomizeLevel();
    }

    public void payBills(Player player){
        player.changeMoney(-player.getEsm()*COST_PER_ESM_UNIT);
        player.changeMoney(-player.getEgp()*COST_PER_EGP_UNIT);
        player.changeMoney(-player.getStandardFactoriesCount()*COST_PER_STANDARD_FACTORY_UNIT);
        player.changeMoney(-player.getAutomatedFactoriesCount()*COST_PER_AUTOMATED_FACTORY_UNIT);
    }

    public boolean isAllPlayersMoved(){
        return setOfFinishedSteps.size() >= getPlayersCount();
    }

    /**
     * Завершение хода конкретного игрока
     * @param playerName
     */
    public void finishStep(String playerName){
        setOfFinishedSteps.add(playerName);
        if(isAllPlayersMoved()) step();
    }

    private void increaseMonth(){
        month++;
    }

    private void increaseCurrentPlayerNumber(){
        currentPlayer++;
        if(currentPlayer >= getPlayersCount()) currentPlayer = 0;
    }

    public void buyEsm(BuyEsmDTO buyEsmDTO){
        Player player = getPlayerByNick(getPlayers(), buyEsmDTO.getUserName());
        if(player == null) return;
        bank.buyEsm(player, buyEsmDTO);
    }

    public void sellEgp(SellEgpDTO sellEgpDTO){
        Player player = getPlayerByNick(getPlayers(), sellEgpDTO.getUserName());
        if(player == null) return;
        bank.sellEgp(player, sellEgpDTO);
    }

    public void convertFactory(BuildOrModIntentionDTO buildOrModIntentionDTO){
        Player player = getPlayerByNick(getPlayers(), buildOrModIntentionDTO.getUserName());
        if(player == null) return;
        player.convertFactory();
    }

    public void build(BuildOrModIntentionDTO buildOrModIntentionDTO){
        Player player = getPlayerByNick(getPlayers(), buildOrModIntentionDTO.getUserName());
        if(player == null) return;
        player.buildFactory(buildOrModIntentionDTO.getType() == 0 ? Factory.FactoryType.STANDARD : Factory.FactoryType.AUTOMATED);
    }

    private Player getPlayerByNick(ArrayList<Player> players, String playerNick){
        for(Player player : players){
            if(player.getUserName().equals(playerNick))
                return player;
        }
        return null;
    }

    public Bank getBank() {
        return bank;
    }

    public int getMonth() {
        return month;
    }

    public int getCrownPlayer() {
        return crownPlayer;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public int getPlayersCount(){
        return lobby.getPlayers().size();
    }

    public ArrayList<Player> getPlayers(){
        return lobby.getPlayers();
    }
}
