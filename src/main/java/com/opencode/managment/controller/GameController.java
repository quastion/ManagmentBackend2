package com.opencode.managment.controller;

import com.opencode.managment.app.Game;
import com.opencode.managment.dto.LobbyDTO;
import com.opencode.managment.dto.PlayerDTO;
import com.opencode.managment.dto.UnknownDTO;
import com.opencode.managment.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class GameController {

    private GameService service;

    @Autowired
    public void setService(GameService service){
        this.service = service;
    }

    @PostMapping("/createLobby")
    public void createLobby(@RequestBody LobbyDTO lobbyDTO){
        service.createLobby(lobbyDTO);
    }

    @PostMapping("/joinLobby")
    public void joinLobby(@RequestBody PlayerDTO playerDTO){
        service.joinLobby(playerDTO);
    }

    @PostMapping("/startGame")
    public void startGame(@RequestBody UnknownDTO unknownDTO){
        service.startGame();
    }

    @PostMapping("/destroyGame")
    public void destroyGame(@RequestBody UnknownDTO unknownDTO){
        service.destroyGame();
    }

    @PostMapping("/finishStep")
    public void finishStep(@RequestBody UnknownDTO unknownDTO){
        service.finishStep("UNKNOWN");
    }
}
