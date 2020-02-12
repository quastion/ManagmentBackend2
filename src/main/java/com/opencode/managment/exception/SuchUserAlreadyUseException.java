package com.opencode.managment.exception;

public class SuchUserAlreadyUseException extends RuntimeException{
    public SuchUserAlreadyUseException() {
        super("Пользователь с такими учетными данными уже используется!");
    }
}
