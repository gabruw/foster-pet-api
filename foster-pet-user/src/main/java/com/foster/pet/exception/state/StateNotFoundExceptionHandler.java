package com.foster.pet.exception.state;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.foster.pet.util.Response;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class StateNotFoundExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(StateNotFoundException.class)
	public final ResponseEntity<Object> exceptionHandler(StateNotFoundException exception) {
		log.error("CityNotFoundException - Message: {}", exception);

		Response<Void> response = new Response<Void>();
		response.addError(exception.getMessage());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}
}
