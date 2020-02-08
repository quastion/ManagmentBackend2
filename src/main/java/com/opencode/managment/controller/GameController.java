package com.opencode.managment.controller;

import com.opencode.managment.app.Game;
import com.opencode.managment.dto.*;
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

    /**
     * Проверка на существование лобби
     */
    @GetMapping("/checkLobby")
    public LobbyStatusDto checkLobby(){
        return service.isLobbyCreated();
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
    public void finishStep(@RequestBody FinishStepIntentionDTO finishStepIntentionDTO){
        service.finishStep(finishStepIntentionDTO);
    }

    @GetMapping("/gameInfo")
    public GameDTO getGameInfo(){
        return service.getGameInfo();
    }

    /**
     * Купить ЕСМ
     * @param
     */
    @PostMapping("/buyEsm")
    public void buyEsm(@RequestBody BuyEsmDTO buyEsmDTO){
        service.buyEsm(buyEsmDTO);
    }

    @PostMapping("/sellEgp")
    public void sellEgp(@RequestBody SellEgpDTO sellEgpDTO){
        service.sellEgp(sellEgpDTO);
    }

    @PostMapping("/getProduct")
    public void getProduct(@RequestBody ProductConversionIntentDTO productConversionIntentDTO){
        service.getProduct(productConversionIntentDTO);
    }
}
