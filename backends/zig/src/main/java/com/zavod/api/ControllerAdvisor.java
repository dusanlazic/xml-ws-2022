package com.zavod.api;

import com.zavod.exception.EmailAlreadyInUseException;
import com.zavod.exception.FileUploadException;
import com.zavod.exception.WrongCredentialsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.FileNotFoundException;

@RestControllerAdvice
public class ControllerAdvisor {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(EmailAlreadyInUseException.class)
    public ResponseEntity<ResponseError> handleEmailAlreadyInUseException(EmailAlreadyInUseException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_XML)
                .body(new ResponseError(HttpStatus.CONFLICT, "Email adresa je zauzeta."));
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(WrongCredentialsException.class)
    public ResponseEntity<ResponseError> handleWrongCredentialsException(WrongCredentialsException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .contentType(MediaType.APPLICATION_XML)
                .body(new ResponseError(HttpStatus.UNAUTHORIZED, "Pogrešna adresa ili lozinka."));
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(FileUploadException.class)
    public ResponseEntity<ResponseError> handleFileUploadException(FileUploadException e) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .contentType(MediaType.APPLICATION_XML)
                .body(new ResponseError(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage()));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<ResponseError> handleFileNotFoundException(FileNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_XML)
                .body(new ResponseError(HttpStatus.NOT_FOUND, e.getMessage()));
    }
}
