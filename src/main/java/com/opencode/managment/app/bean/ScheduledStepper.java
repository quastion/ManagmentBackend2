package com.opencode.managment.app.bean;

import com.opencode.managment.app.Game;
import com.opencode.managment.app.Player;
import com.opencode.managment.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling
@Component
public class ScheduledStepper {
    @Autowired
    private GameService gameService;

    @Scheduled(fixedRate = 1000)
    public void checkTimeToStep(){
        Game game = gameService.getGame();
        if(game == null || game.getPlayersCount() <= 1){
            return;
        }
        Player currentPlayer = game.getCurrentPlayerEntity();
        currentPlayer.changeLeftTimeForStep(-1);
        if(currentPlayer.getLeftTimeForStep() <= 0){
            game.finishStep(currentPlayer.getUserName());
        }
    }
}
