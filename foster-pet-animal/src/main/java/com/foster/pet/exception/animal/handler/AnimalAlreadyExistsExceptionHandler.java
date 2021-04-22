package com.foster.pet.exception.animal.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.foster.pet.animal.exception.AnimalAlreadyExistsException;
import com.foster.pet.animal.util.Response;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AnimalAlreadyExistsExceptionHandler extends ResponseEntityExceptionHandler{

	public final ResponseEntity<Object> exceptionHandler(AnimalAlreadyExistsException exception){
		log.error("AnimalAlreadyExistsException", exception);
		
		Response<Void> response = new Response<>();
		response.addError(exception.getMessage());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
}
