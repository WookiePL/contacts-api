package com.webapi.contacts.configuration;

import com.webapi.contacts.exception.InaccessibleContactException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ EntityNotFoundException.class })
    public ResponseEntity<Object> handleEntityNotFoundException(Exception ex, WebRequest request) {
        return new ResponseEntity<Object>(
                "Sorry, but requested entity wasn't found.", new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ InaccessibleContactException.class })
    public ResponseEntity<Object> handleInaccessibleContactException(Exception ex, WebRequest request) {
        return new ResponseEntity<Object>(
                "Sorry, but this user cannot modify this contact.", new HttpHeaders(), HttpStatus.FORBIDDEN);
    }

}
