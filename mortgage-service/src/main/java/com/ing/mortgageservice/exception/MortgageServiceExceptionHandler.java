package com.ing.mortgageservice.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MortgageServiceExceptionHandler extends ResponseEntityExceptionHandler {


    private class ErrorMessage {
        private String message;
        private String furtherActions;

        public ErrorMessage(String message, String furtherActions) {
            this.message = message;
            this.furtherActions = furtherActions;
        }

        public String getMessage() {
            return message;
        }

        public String getFurtherActions() {
            return furtherActions;
        }
    }

    @ExceptionHandler(MortgageServiceInputException.class)
    public final ResponseEntity<ErrorMessage> inputExceptionHandler(MortgageServiceInputException mortgageInputException) {
        ErrorMessage errorMessage = new ErrorMessage(mortgageInputException.getMessage(),
                "Please provide correct input");

        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorMessage> defaultExceptionHandler(Exception exception) {
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(),
                "Unknown error occurred, Kindly contact Support at <+++>");

        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
