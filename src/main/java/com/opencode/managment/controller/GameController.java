package com.opencode.managment.controller;

import com.opencode.managment.dto.LobbyDTO;
import com.opencode.managment.dto.PlayerDTO;
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
    public void createLobby(@RequestBody PlayerDTO playerDTO){
        service.joinLobby(playerDTO);
    }
}
