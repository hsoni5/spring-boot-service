package org.soni.exception;


import lombok.Data;

@Data
public class JWTVerificationException extends RuntimeException{
    private final String message;

    public JWTVerificationException(String message){
        super(message);
        this.message = message;
    }
}
