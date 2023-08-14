package org.soni.exception;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AwsServiceException extends RuntimeException {
    private final String message;

    public AwsServiceException(String message) {
        super(message);
        this.message = message;
    }
}
