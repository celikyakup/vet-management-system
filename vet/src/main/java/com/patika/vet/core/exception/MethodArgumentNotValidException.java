package com.patika.vet.core.exception;

import org.apache.logging.log4j.message.Message;

public class MethodArgumentNotValidException extends RuntimeException{
    public MethodArgumentNotValidException(String message){
        super(message);
    }
}
