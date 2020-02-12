package com.opencode.managment.controller;

import com.opencode.managment.dto.*;
import com.opencode.managment.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody LobbyStatusDto checkLobby(){
        return service.isLobbyCreated();
    }

    @PostMapping("/createLobby")
    @ResponseStatus(HttpStatus.OK)
    public void createLobby(@RequestBody LobbyDTO lobbyDTO){
        service.createLobby(lobbyDTO);
    }

    @PostMapping("/joinLobby")
    @ResponseStatus(HttpStatus.OK)
    public void joinLobby(@RequestBody PlayerDTO playerDTO){
        service.joinLobby(playerDTO);
    }

    @GetMapping("/lobbyInfo")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody LobbyDTO getLobbyInfo(){
        return service.getLobbyInfo();
    }

    @PostMapping("/startGame")
    @ResponseStatus(HttpStatus.OK)
    public void startGame(@RequestBody UnknownDTO unknownDTO){
        service.startGame();
    }

    @PostMapping("/destroyGame")
    @ResponseStatus(HttpStatus.OK)
    public void destroyGame(@RequestBody UnknownDTO unknownDTO){
        service.destroyGame();
    }

    @PostMapping("/finishStep")
    @ResponseStatus(HttpStatus.OK)
    public void finishStep(@RequestBody FinishStepIntentionDTO finishStepIntentionDTO){
         service.finishStep(finishStepIntentionDTO);
    }

    @GetMapping("/gameInfo")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody GameDTO getGameInfo(){
        return service.getGameInfo();
    }

    /**
     * Купить ЕСМ
     * @param
     */
    @PostMapping("/buyEsm")
    @ResponseStatus(HttpStatus.OK)
    public void buyEsm(@RequestBody BuyEsmDTO buyEsmDTO){
        service.buyEsm(buyEsmDTO);
    }

    @PostMapping("/sellEgp")
    @ResponseStatus(HttpStatus.OK)
    public void sellEgp(@RequestBody SellEgpDTO sellEgpDTO){
        service.sellEgp(sellEgpDTO);
    }

    @PostMapping("/convertFactory")
    @ResponseStatus(HttpStatus.OK)
    public void convertFactory(@RequestBody BuildOrModIntentionDTO buildOrModIntentionDTO){
        service.convertFactory(buildOrModIntentionDTO);
    }

    @PostMapping("/build")
    @ResponseStatus(HttpStatus.OK)
    public void build(@RequestBody BuildOrModIntentionDTO buildOrModIntentionDTO){
        service.build(buildOrModIntentionDTO);
    }

    @PostMapping("/getLoan")
    @ResponseStatus(HttpStatus.OK)
    public void getLoan(@RequestBody LoanDTO loanDTO){
        service.getLoan(loanDTO);
    }

    @PostMapping("/getProduct")
    @ResponseStatus(HttpStatus.OK)
    public void getProduct(@RequestBody ProductConversionIntentionDTO productConversionIntentionDTO){
        service.getProduct(productConversionIntentionDTO);
    }

    @GetMapping("/getGameOverInfo")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody GameOverInfoDTO getGameOverInfo(){
        return service.getGameOverInfo();
    }
}
