package com.opencode.managment.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public final class Game {
    private static final int COST_PER_ESM_UNIT = 300;
    private static final int COST_PER_EGP_UNIT = 500;
    private static final int COST_PER_STANDARD_FACTORY_UNIT = 1000;
    private static final int COST_PER_AUTOMATED_FACTORY_UNIT = 1500;

    private Lobby lobby;

    private HashSet<String> setOfFinishedSteps;

    public Game(Lobby lobby) {
        this.lobby = lobby;
        setOfFinishedSteps = new HashSet<>();
    }

    public void step(){
        for(Player player : lobby.getPlayers()){
            payBills(player);
        }
        setOfFinishedSteps.clear();
    }

    public void payBills(Player player){
        player.changeMoney(player.getEsm()*COST_PER_ESM_UNIT);
        player.changeMoney(player.getEgp()*COST_PER_EGP_UNIT);
        player.changeMoney(player.getStandardFactoriesCount()*COST_PER_STANDARD_FACTORY_UNIT);
        player.changeMoney(player.getAutomatedFactoriesCount()*COST_PER_AUTOMATED_FACTORY_UNIT);
    }

    public boolean isAllPlayersMoved(){
        return setOfFinishedSteps.size() >= lobby.getPlayers().size();
    }

    public void finishStep(String playerName){
        setOfFinishedSteps.add(playerName);
        if(isAllPlayersMoved()) step();
    }
}
