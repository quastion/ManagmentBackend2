package com.opencode.managment.exception;

public class NoDataException extends RuntimeException {
    public NoDataException() {
        super("Невозможно получить данные, игра или лобби не созданы!");
    }
}
