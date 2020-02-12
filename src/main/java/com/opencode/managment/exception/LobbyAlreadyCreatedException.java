package com.opencode.managment.exception;

public class LobbyAlreadyCreatedException extends RuntimeException {
    public LobbyAlreadyCreatedException(){
        super("Лобби уже создана! Одновременно может существовать только 1 игровая лобби!");
    }
}
