package com.opencode.managment.app;

import com.opencode.managment.dto.BuyEsmDTO;
import com.opencode.managment.dto.SellEgpDTO;
import javafx.collections.transformation.SortedList;

import java.util.*;
import java.util.stream.Collectors;

public class Bank {
    private Random random;
    private int level = 3;
    private double[][] chanceTable;

    private HashMap<Player, BuyEsmDTO> esmPlayers;
    private HashMap<Player, SellEgpDTO> egpPlayers;

    {
        esmPlayers = new HashMap<>();
        egpPlayers = new HashMap<>();
        random = new Random();
        chanceTable = new double[][]{
                {1.0/3, 1.0/3, 1.0/6, 1.0/12, 1.0/12},
                {1.0/4, 1.0/3, 1.0/4, 1.0/12, 1.0/12},
                {1.0/12, 1.0/4, 1.0/3, 1.0/4, 1.0/12},
                {1.0/12, 1.0/12, 1.0/4, 1.0/3, 1.0/4},
                {1.0/12, 1.0/12, 1.0/6, 1.0/3, 1.0/3}
        };
    }

    public int randomizeLevel(){
        double chance = random.nextDouble();

        for(int i = 0; i < chanceTable.length; i++){
            if(i + 1 == level){
                double prevSum = 0;
                level = chanceTable[i].length;
                for(int j = 0; j < chanceTable[i].length; j++){
                    if(prevSum < chance && prevSum + chanceTable[i][j] >= chance){
                        level = j + 1;
                        break;
                    }
                    prevSum += chanceTable[i][j];
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

    public void buyEsm(Player player, BuyEsmDTO buyEsmDTO){
        esmPlayers.put(player, buyEsmDTO);
    }

    public void sellEgp(Player player, SellEgpDTO sellEgpDTO){
        egpPlayers.put(player, sellEgpDTO);
    }

    /**
     * Провести торги
     */
    public void holdTenders(int playersCount){
        int esmCount = getEsmNumber(playersCount);
        Set<Map.Entry<Player, BuyEsmDTO>> esmSet = esmPlayers.entrySet();
        List<Map.Entry<Player, BuyEsmDTO>> esmList = esmSet.stream().sorted(
                (o1, o2) -> {
                    int res = 0;
                    if(o2.getValue().getCostEsm() - o1.getValue().getCostEsm() == 0){
                        if(o1.getKey().getIsCrownPlayer())
                            res = -1;
                        if(o2.getKey().getIsCrownPlayer())
                            res = 1;
                    }
                    else{
                        res = o2.getValue().getCostEsm() - o1.getValue().getCostEsm();
                    }
                    return res;
                })
                .collect(Collectors.toList());
        for (Map.Entry<Player, BuyEsmDTO> entry: esmList){
            if(entry.getValue().getNumberEsm() <= esmCount){
                entry.getKey().changeEsm(entry.getValue().getNumberEsm());
                entry.getKey().changeMoney(-entry.getValue().getNumberEsm() * entry.getValue().getCostEsm());
            }
            else{
                entry.getKey().changeEsm(esmCount);
                entry.getKey().changeMoney(-esmCount * entry.getValue().getCostEsm());
            }
            esmCount -= entry.getValue().getNumberEsm();
            if(esmCount <= 0) break;
        }

        int egpCount = getEgpNumber(playersCount);
        Set<Map.Entry<Player, SellEgpDTO>> egpSet = egpPlayers.entrySet();
        List<Map.Entry<Player, SellEgpDTO>> egpList = egpSet.stream().sorted(
                (o1, o2) -> {
                    int res = 0;
                    if(o1.getValue().getCostEgp() - o2.getValue().getCostEgp() == 0){
                        if(o1.getKey().getIsCrownPlayer())
                            res = -1;
                        if(o2.getKey().getIsCrownPlayer())
                            res = 1;
                    }
                    else{
                        res = o1.getValue().getCostEgp() - o2.getValue().getCostEgp();
                    }
                    return res;
                })
                .collect(Collectors.toList());
        for (Map.Entry<Player, SellEgpDTO> entry: egpList){
            if(entry.getValue().getNumberEgp() <= egpCount){
                entry.getKey().changeEgp(-entry.getValue().getNumberEgp());
                entry.getKey().changeMoney(entry.getValue().getNumberEgp() * entry.getValue().getCostEgp());
            }
            else{
                entry.getKey().changeEgp(-egpCount);
                entry.getKey().changeMoney(egpCount * entry.getValue().getCostEgp());
            }
            egpCount -= entry.getValue().getNumberEgp();
            if(egpCount <= 0) break;
        }

        esmPlayers.clear();
        egpPlayers.clear();
    }
}
