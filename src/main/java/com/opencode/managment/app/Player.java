package com.opencode.managment.app;

import com.opencode.managment.app.factory.Factory;
import com.opencode.managment.dto.LoanDTO;
import com.opencode.managment.dto.PlayerDTO;
import com.opencode.managment.dto.ProductConversionIntentionDTO;
import com.opencode.managment.entity.User;
import com.opencode.managment.service.UserService;

import java.util.ArrayList;
import java.util.Objects;

public class Player {
    private static final int LOAN_REPAYMENT_TIME = 12;
    public static final int TIME_FOR_STEP = 120;

    private User user;
    private String userName;
    private int numberInLobby;
    private boolean isCrownPlayer;
    private int leftTimeForStep = TIME_FOR_STEP;

    //Фабрики
    private int standardFactoriesCount = 2;
    private int automatedFactoriesCount = 0;
    private int esm = 4;
    private int egp = 2;
    private int money = 10000;
    private ArrayList<Factory> factoryBuildingQueue;

    //Ссуда
    private int availableLoansCount = 2;
    private boolean isLoan = false;
    private int outstandingLoan = 0; //остаток погашения ссуды
    private int loanRepaymentTime = 0; //время до погашения ссуды

    public Player(PlayerDTO playerDTO) {
        userName = playerDTO.getUserName();
        numberInLobby = playerDTO.getNumberInLobby();
        isCrownPlayer = playerDTO.getIsCrownPlayer();
        user = UserService.getRepository().fundByUserName(playerDTO.getUserName());
        factoryBuildingQueue = new ArrayList<>();
    }

    public User getUser() {
        return user;
    }

    /**
     * Построить обычную или модернизированную фабрику
     * @param factoryType
     */
    public void buildFactory(Factory.FactoryType factoryType){
        if(factoryType == Factory.FactoryType.STANDARD && money >= Factory.COST_OF_BUILDING_STANDARD_FACTORY / 2){
            changeMoney(-Factory.COST_OF_BUILDING_STANDARD_FACTORY / 2);
            factoryBuildingQueue.add(new Factory(factoryType));
        } else if(factoryType == Factory.FactoryType.AUTOMATED && money >= Factory.COST_OF_BUILDING_AUTOMATED_FACTORY / 2){
            changeMoney(-Factory.COST_OF_BUILDING_AUTOMATED_FACTORY / 2);
            factoryBuildingQueue.add(new Factory(factoryType));
        }
    }

    /**
     * Модернизировать фабрику
     */
    public void convertFactory(){
        if(standardFactoriesCount > 0 && money >= Factory.COST_OF_RECONSTRUCTION_FACTORY / 2){
            changeMoney(-Factory.COST_OF_RECONSTRUCTION_FACTORY / 2);
            Factory factory = new Factory(Factory.FactoryType.STANDARD);
            factory.convertFactory();
            factoryBuildingQueue.add(factory);
        }
    }

    public void getProduct( ProductConversionIntentionDTO productConversionIntentionDTO){
        changeMoney(-productConversionIntentionDTO.getSumOfMoney());
        changeEsm(-productConversionIntentionDTO.getNumberOfEsmToEgp());
        changeEgp(productConversionIntentionDTO.getNumberOfEsmToEgp());
    }

    /**
     * Размер возможной ссуды
     * @return
     */
    public int availableLoanValue(){
        return (Factory.COST_OF_BUILDING_STANDARD_FACTORY * standardFactoriesCount +
                Factory.COST_OF_BUILDING_AUTOMATED_FACTORY * automatedFactoriesCount ) /2;
    }

    public void getLoan(LoanDTO loanDTO){
        int loanValue = loanDTO.getLoanValue();
        if(!isLoan && availableLoansCount > 0 && availableLoanValue() >= loanValue){
            availableLoansCount--;
            isLoan = true;
            outstandingLoan = loanValue;
            loanRepaymentTime = LOAN_REPAYMENT_TIME;
            changeMoney(loanValue);
        }
    }

    public void updateLoanState(){
        if(isLoan){
            changeMoney(-(int)(outstandingLoan * 0.01));
            loanRepaymentTime--;
            if(loanRepaymentTime <= 0){
                isLoan = false;
                loanRepaymentTime = 0;
                changeMoney(-outstandingLoan);
                outstandingLoan = 0;
            }
        }
    }

    public void updateFactoriesStates(){
        for(Factory factory : factoryBuildingQueue){
            factory.updateState();
            if(factory.getState() == Factory.State.BUILT){
                if(factory.getFactoryType() == Factory.FactoryType.STANDARD && money >= Factory.COST_OF_BUILDING_STANDARD_FACTORY / 2){
                    changeMoney(-Factory.COST_OF_BUILDING_STANDARD_FACTORY / 2);
                    factoryBuildingQueue.remove(factory);
                    standardFactoriesCount++;
                } else if(factory.getFactoryType() == Factory.FactoryType.AUTOMATED && money >= Factory.COST_OF_BUILDING_AUTOMATED_FACTORY / 2){
                    changeMoney(-Factory.COST_OF_BUILDING_AUTOMATED_FACTORY / 2);
                    factoryBuildingQueue.remove(factory);
                    automatedFactoriesCount++;
                }
            }
            else if(factory.getState() == Factory.State.CONVERTED && money >= Factory.COST_OF_RECONSTRUCTION_FACTORY / 2){
                changeMoney(-Factory.COST_OF_RECONSTRUCTION_FACTORY / 2);
                factoryBuildingQueue.remove(factory);
                automatedFactoriesCount++;
                standardFactoriesCount--;
            }
        }
    }

    private void updateLeftTimeForStep(){
        leftTimeForStep = TIME_FOR_STEP;
    }

    public void updateState(){
        updateFactoriesStates();
        updateLoanState();
        updateLeftTimeForStep();
    }

    public String getUserName() {
        return userName;
    }

    public int getNumberInLobby() {
        return numberInLobby;
    }

    public void setCrownPlayer(boolean crownPlayer) {
        isCrownPlayer = crownPlayer;
    }

    public boolean getIsCrownPlayer() {
        return isCrownPlayer;
    }

    public int getStandardFactoriesCount() {
        return standardFactoriesCount;
    }

    public void setStandardFactoriesCount(int standardFactoriesCount) {
        this.standardFactoriesCount = standardFactoriesCount;
    }

    public int getAutomatedFactoriesCount() {
        return automatedFactoriesCount;
    }

    public void setAutomatedFactoriesCount(int automatedFactoriesCount) {
        this.automatedFactoriesCount = automatedFactoriesCount;
    }

    public int getEsm() {
        return esm;
    }

    public void setEsm(int esm) {
        this.esm = esm;
    }

    public int getEgp() {
        return egp;
    }

    public void setEgp(int egp) {
        this.egp = egp;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void changeMoney(int value){
        money+=value;
    }

    public void changeLeftTimeForStep(int value){
        leftTimeForStep+=value;
    }

    public void changeEsm(int value){
        esm+=value;
    }

    public void changeEgp(int value){
        egp+=value;
    }

    public boolean isLoan() {
        return isLoan;
    }

    public int getAvailableLoansCount() {
        return availableLoansCount;
    }

    public int getOutstandingLoan() {
        return outstandingLoan;
    }

    public int getLoanRepaymentTime() {
        return loanRepaymentTime;
    }

    public void setNumberInLobby(int numberInLobby) {
        this.numberInLobby = numberInLobby;
    }

    public int getLeftTimeForStep() {
        return leftTimeForStep;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(user, player.user) &&
                Objects.equals(userName, player.userName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(user, userName);
    }
}
