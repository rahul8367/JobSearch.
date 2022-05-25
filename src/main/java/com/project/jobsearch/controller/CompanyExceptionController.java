package com.project.jobsearch.controller;

import com.project.jobsearch.custom.exception.NotFoundException;
import com.project.jobsearch.custom.exception.NullException;
import com.project.jobsearch.pojo.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CompanyExceptionController {
    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ExceptionResponse> exception(NotFoundException exception) {
            ExceptionResponse com=new ExceptionResponse();
            com.setStatus(HttpStatus.NOT_FOUND.value());
            com.setMessage(exception.getMessage());
            com.setTimeStamp(System.currentTimeMillis());
            return new ResponseEntity<>(com, HttpStatus.NOT_FOUND);
    }
    // Add another exception handler ... to catch any exception (catch all)

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleException(Exception exc) {

        // create CustomerErrorResponse

        ExceptionResponse error = new ExceptionResponse(
                HttpStatus.BAD_REQUEST.value(),
                exc.getMessage(),
                System.currentTimeMillis());

        // return ResponseEntity

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    // to find the null  values
    @ExceptionHandler(value = NullException.class)
    public ResponseEntity<ExceptionResponse> exception1(NullException exception) {
        ExceptionResponse com=new ExceptionResponse();
        com.setStatus(HttpStatus.NOT_FOUND.value());
        com.setMessage(exception.getMessage());
        com.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(com, HttpStatus.NOT_FOUND);
    }

}
