package com.opencode.managment.configuration;

import com.opencode.managment.dto.ExceptionDTO;
import com.opencode.managment.exception.CanNotModificateLobbyException;
import com.opencode.managment.exception.LobbyAlreadyCreatedException;
import com.opencode.managment.exception.NoDataException;
import com.opencode.managment.exception.SuchUserAlreadyUseException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionResolver extends ResponseEntityExceptionHandler {

    @ExceptionHandler(SuchUserAlreadyUseException.class)
    public ResponseEntity<ExceptionDTO> handleSuchUserAlreadyUseException(){
        return new ResponseEntity(new ExceptionDTO("Пользователь с такими данными уже используется","Error"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoDataException.class)
    public ResponseEntity<ExceptionDTO> handleNoDataException(){
        return new ResponseEntity(new ExceptionDTO("Невозможно получить данные. Возможно они еще не готовы.","Error"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(LobbyAlreadyCreatedException.class)
    public ResponseEntity<ExceptionDTO> handleLobbyAlreadyCreatedException(){
        return new ResponseEntity(new ExceptionDTO("Лобби уже создана! Одновременно может существовать только 1 игровая лобби!","Error"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CanNotModificateLobbyException.class)
    public ResponseEntity<ExceptionDTO> handleCanNotModificateLobbyException(){
        return new ResponseEntity(new ExceptionDTO("Не удалось совершить действие над лобби. Возможно уже идет игра.","Error"), HttpStatus.BAD_REQUEST);
    }
}
