package com.opencode.managment.app;

import java.util.Random;

public class Bank {
    private Random random;
    private int level = 3;
    private double[][] chanceTable;

    {
        random = new Random();
        chanceTable = new double[][]{
                {1/3, 1/3, 1/6, 1/12, 1/12},
                {1/4, 1/3, 1/4, 1/12, 1/12},
                {1/12, 1/4, 1/3, 1/4, 1/12},
                {1/12, 1/12, 1/4, 1/3, 1/4},
                {1/12, 1/12, 1/6, 1/3, 1/3}
        };
    }

    public int randomizeLevel(){
        double chance = random.nextDouble();

        for(int i = 0; i < chanceTable.length; i++){
            if(i + 1 == level){
                for(int j = 0; j < chanceTable[i].length; j++){
                    if(chanceTable[i][j] >= chance){
                        level = j + 1;
                        break;
                    }
                }
                break;
            }
        }

        return level;
    }

    public int getEsmNumber(int playerCount){
        return (int)((0.5 + 0.5 * level) *  playerCount);
    }

    public int getEsmCost(){
        int esmCost = 0;
        switch (level){
            case 1:
                esmCost = 800;
                break;
            case 2:
                esmCost = 650;
                break;
            case 3:
                esmCost = 500;
                break;
            case 4:
                esmCost = 400;
                break;
            case 5:
                esmCost = 300;
                break;
        }
        return esmCost;
    }

    public int getEgpNumber(int playerCount){
        return (int)((3.5 - 0.5 * level) *  playerCount);
    }

    public int getEgpCost(){
        int egpCost = 0;
        switch (level){
            case 1:
                egpCost = 6500;
                break;
            case 2:
                egpCost = 6000;
                break;
            case 3:
                egpCost = 5500;
                break;
            case 4:
                egpCost = 5000;
                break;
            case 5:
                egpCost = 4500;
                break;
        }
        return egpCost;
    }
}
