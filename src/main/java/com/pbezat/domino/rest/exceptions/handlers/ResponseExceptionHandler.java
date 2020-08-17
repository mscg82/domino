package com.pbezat.domino.rest.exceptions.handlers;

import com.pbezat.domino.rest.exceptions.IncorrectParameterException;
import com.pbezat.domino.rest.exceptions.ParametersMissingException;
import com.pbezat.domino.rest.exceptions.handlers.tos.ParameterMissingResponseTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {IncorrectParameterException.class})
    protected ResponseEntity<Object> handleIncorrectParameter(final IncorrectParameterException ex, final WebRequest request) {
        final String bodyOfResponse = "Provided input parameters are incorrect.";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {ParametersMissingException.class})
    protected ResponseEntity<Object> handleMissingParameter(final ParametersMissingException ex, final WebRequest request) {
        final ParameterMissingResponseTO responseTO = new ParameterMissingResponseTO("There are some parameters missing in the request", ex.getParameterNames());
        return handleExceptionInternal(ex, responseTO, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
