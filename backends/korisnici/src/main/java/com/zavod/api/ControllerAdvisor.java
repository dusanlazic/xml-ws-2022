package com.zavod.api;

import com.zavod.exception.EmailAlreadyInUseException;
import com.zavod.exception.WrongCredentialsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
}
