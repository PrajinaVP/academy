package com.prajina.academy.exception;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//@ControllerAdvice
public class AcademyExceptionHandler extends ResponseEntityExceptionHandler {

	Logger logger = LoggerFactory.getLogger(AcademyExceptionHandler.class);
	
	private static final String FIELD_ERROR_SEPARATOR = ": ";

	@ExceptionHandler({Exception.class}) 
	public ResponseEntity<Object> handleAllExceptions(Exception exception, WebRequest request) {
		ResponseStatus responseStatus = 
				exception.getClass().getAnnotation(ResponseStatus.class);
		final HttpStatus status = 
				responseStatus!=null ? responseStatus.value() : HttpStatus.INTERNAL_SERVER_ERROR;
		final String localizedMessage = exception.getLocalizedMessage();
		String message = (!localizedMessage.isEmpty() ? localizedMessage:status.getReasonPhrase());
		
		return getExceptionResponseEntity(exception, status, request, Collections.singletonList(message));
	}
	
	@ExceptionHandler({ConstraintViolationException.class})
	public ResponseEntity<Object> handleConstraintViolation(
			ConstraintViolationException exception, WebRequest request) {
		final List<String> validationErrors = exception.getConstraintViolations()
				.stream()
				.map(violation -> 
						violation.getPropertyPath() + FIELD_ERROR_SEPARATOR + violation.getMessage())
				.collect(Collectors.toList());
		
		return getExceptionResponseEntity(exception, HttpStatus.BAD_REQUEST, request, validationErrors);
	}
	
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
    		MethodArgumentNotValidException exception,
    		HttpHeaders headers,
    		HttpStatus status,
    		WebRequest request) {
    	
    	List<String> validationErrors = exception.getBindingResult()
    			.getFieldErrors()
    			.stream()
    			.map(error -> error.getField() + FIELD_ERROR_SEPARATOR + error.getDefaultMessage())
    			.collect(Collectors.toList());
    	
    	return getExceptionResponseEntity(exception, HttpStatus.BAD_REQUEST, request, validationErrors);
    	
    }
    
    private ResponseEntity<Object> getExceptionResponseEntity(
    		final Exception exception,
    		final HttpStatus status,
    		final WebRequest request,
    		final List<String> errors) {
    	
    	ExceptionResponse exceptionResponse = new ExceptionResponse(
    			UUID.randomUUID(), 
    			status.value(), 
    			errors, 
    			exception.getClass().getSimpleName(), 
    			request.getDescription(false),
    			status.getReasonPhrase());
    	
    	return new ResponseEntity<>(exceptionResponse, status);
    }

}