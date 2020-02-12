package com.opencode.managment.exception;

public class CanNotModificateLobbyException extends RuntimeException {
    public CanNotModificateLobbyException(){
        super("Не удалось совершить действие над лобби. Возможно уже идет игра.");
    }
}
