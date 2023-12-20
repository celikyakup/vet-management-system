
package com.patika.vet.core.config;

import com.patika.vet.core.exception.NotFoundException;
import org.apache.catalina.connector.Response;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
    public ResponseEntity<ErrorMessage> handleNotFoundError(NotFoundException e, WebRequest request){
        ErrorMessage message=new ErrorMessage(HttpStatus.NOT_FOUND.value(), LocalDateTime.now(), e.getMessage(),request.getDescription(false) );
        return new ResponseEntity<ErrorMessage>(message,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> globalException(Exception e,WebRequest request){
        ErrorMessage message=new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(),LocalDateTime.now(),e.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(message,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleValidationErrorss(MethodArgumentNotValidException e,WebRequest request){
        ErrorMessage message=new ErrorMessage(HttpStatus.BAD_REQUEST.value(),LocalDateTime.now(),e.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
    }
}


