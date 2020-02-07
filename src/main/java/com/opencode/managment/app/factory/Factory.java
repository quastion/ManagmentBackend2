package com.opencode.managment.app.factory;

import com.opencode.managment.app.Player;

public class Factory {
    private static final int COST_OF_CONVERSION_1_ESM_TO_1_EGP = 2000;
    private static final int COST_OF_CONVERSION_2_ESM_TO_2_EGP = 3000;

    public static void convertEsmToEgp(Player player, int requiredCountEgp, int cost){
        player.changeEgp(requiredCountEgp);
        player.changeEsm(-requiredCountEgp);
        player.changeMoney(cost);
    }
}
