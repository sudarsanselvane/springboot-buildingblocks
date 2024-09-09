package com.stacksimplify.restservices.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.validation.ConstraintViolationException;


@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

	// MethodArgumentNotValidException
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(),
				"From MethodArgumentNotValid Exception in GEH", ex.getMessage());

		return new ResponseEntity<>(customErrorDetails, HttpStatus.BAD_REQUEST);
	}

	// HttpRequestMethodNotSupportedException
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(),
				"From HttpRequestMethodNotSupported Exception in GEH - method not allowed", ex.getMessage());

		return new ResponseEntity<>(customErrorDetails, HttpStatus.METHOD_NOT_ALLOWED);
	}

	// UserNameNotFoundException
	@ExceptionHandler(userNameNotFoundException.class)
	public final ResponseEntity<Object> handleUserNameNotFoundException(userNameNotFoundException ex,
			WebRequest request) {

		CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(), ex.getMessage(),
				request.getDescription(true));

		return new ResponseEntity<>(customErrorDetails, HttpStatus.NOT_FOUND);
	}

	// ConstraintViolationException
	@ExceptionHandler(ConstraintViolationException.class)
	public final ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex,
			WebRequest request) {

		CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(), ex.getMessage(),
				request.getDescription(true));

		return new ResponseEntity<>(customErrorDetails, HttpStatus.BAD_REQUEST);
	}

}
