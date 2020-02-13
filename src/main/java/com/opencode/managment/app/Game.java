package com.opencode.managment.app;

import com.opencode.managment.app.factory.Factory;
import com.opencode.managment.dto.*;

import java.util.*;

public final class Game {
    private static final int COST_PER_ESM_UNIT = 300;
    private static final int COST_PER_EGP_UNIT = 500;
    private static final int COST_PER_STANDARD_FACTORY_UNIT = 1000;
    private static final int COST_PER_AUTOMATED_FACTORY_UNIT = 1500;
    private static final int MONTHS_LIMIT = 40;

    private Lobby lobby;
    private Bank bank;

    private HashSet<String> setOfFinishedSteps;
    private int month;
    private int crownPlayer;
    private int currentPlayer;

    public State gameState;
    private GameOverInfoDTO gameOverInfoDTO;

    public static enum State{
        GAME_OVER, GAME
    }

    public Game(Lobby lobby) {
        this.lobby = lobby;
        setOfFinishedSteps = new HashSet<>();
        bank = new Bank();
        gameState = State.GAME;
        gameOverInfoDTO = new GameOverInfoDTO();
    }

    private void step(){
        if(!isGame()) return;

        for(Player player : lobby.getPlayers()){
            payBills(player);
            player.updateState();
        }
        setOfFinishedSteps.clear();
        increaseMonth();
        bank.holdTenders(getPlayersCount());
        bank.randomizeLevel();
        increaseCrownPlayer();
        checkPlayersGameOverCondition();
        checkGameOverCondition();
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
        if(!isGame()) return;

        setOfFinishedSteps.add(playerName);
        increaseCurrentPlayerNumber();
        if(isAllPlayersMoved()) step();
    }

    private void increaseMonth(){
        month++;
    }

    private void increaseCurrentPlayerNumber(){
        currentPlayer++;
        if(currentPlayer >= getPlayersCount()) currentPlayer = 0;
    }

    private void increaseCrownPlayer(){
        crownPlayer++;
        if(crownPlayer >= getPlayersCount()) crownPlayer = 0;
        for(int i = 0; i < getPlayers().size(); i++) getPlayers().get(i).setCrownPlayer(false);
        getPlayers().get(crownPlayer).setCrownPlayer(true);
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

    public void getLoan(LoanDTO loanDTO){
        Player player = getPlayerByNick(getPlayers(), loanDTO.getUserName());
        if(player == null) return;
        player.getLoan(loanDTO);
    }

    public void getProduct( ProductConversionIntentionDTO productConversionIntentionDTO){
        Player player = getPlayerByNick(getPlayers(), productConversionIntentionDTO.getUserName());
        if(player == null) return;
        player.getProduct(productConversionIntentionDTO);
    }

    private Player getPlayerByNick(ArrayList<Player> players, String playerNick){
        for(Player player : players){
            if(player.getUserName().equals(playerNick))
                return player;
        }
        return null;
    }

    private void checkGameOverCondition(){
        if(month >= MONTHS_LIMIT ||
                getPlayersCount() <= 1){
            gameState = State.GAME_OVER;
            gameOverInfoDTO.setEndOfGame(true);
            getPlayers().sort((o1, o2) -> o1.getMoney() - o2.getMoney());
            gameOverInfoDTO.getPlayers().addAll(getPlayers());
            Collections.reverse(gameOverInfoDTO.getPlayers());
        }
    }

    private void checkPlayersGameOverCondition(){
        for(int i = 0; i < getPlayersCount(); i++){
            Player player = getPlayers().get(i);
            if(player.getMoney() <= 0){
                gameOverInfoDTO.getPlayers().add(player);
                getPlayers().remove(player);
            }
        }
    }

    public Player getCurrentPlayerEntity(){
        return lobby.getPlayers().get(currentPlayer);
    }

    public GameOverInfoDTO getGameOverInfo(){
        return gameOverInfoDTO;
    }

    public boolean isGame(){
        return gameState == State.GAME;
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

    public State getGameState() {
        return gameState;
    }
}
