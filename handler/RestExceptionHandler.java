package com.springboottest.SpringBootTest.handler;

import com.springboottest.SpringBootTest.exceptions.ResourceNotFoundException;
import com.springboottest.SpringBootTest.models.errors.BasicResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException (ResourceNotFoundException ex, HttpServletRequest request) {
        BasicResponse errorDetails = new BasicResponse();
        errorDetails.setCode(HttpStatus.NOT_FOUND.value());
        errorDetails.setMessage(ex.getMessage());
        return new ResponseEntity<>(errorDetails, null, HttpStatus.NOT_FOUND);
    }

}
