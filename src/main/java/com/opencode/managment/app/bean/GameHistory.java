package com.opencode.managment.app.bean;

import com.opencode.managment.app.Game;
import com.opencode.managment.dto.GameOverInfoDTO;
import org.springframework.stereotype.Component;

@Component
public class GameHistory {
    private Game game;

    public GameHistory(){ }

    public void setGame(Game game) {
        this.game = game;
    }

    public GameOverInfoDTO getLastGameResults(){
        if(game == null) return null;

        return game.getGameOverInfo();
    }

    public Game getGame() {
        return game;
    }
}
