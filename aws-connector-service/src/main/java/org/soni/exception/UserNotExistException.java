package org.soni.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "The user not exist")
public class UserNotExistException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public UserNotExistException(String message){
        super(message);
    }
}
