package org.soni.exception;

public class NoRecordsFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public NoRecordsFoundException(String message){
        super(message);
    }
}
