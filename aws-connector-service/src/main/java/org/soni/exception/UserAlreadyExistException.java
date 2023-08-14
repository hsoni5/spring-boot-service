package org.soni.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "The user already exist")
public class UserAlreadyExistException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public UserAlreadyExistException(String message){
        super(message);
    }
}