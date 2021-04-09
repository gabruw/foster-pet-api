package com.foster.pet.exception.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.foster.pet.util.Response;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class CompanyNotFoundExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(CompanyNotFoundException.class)
	public final ResponseEntity<Object> exceptionHandler(CompanyNotFoundException exception) {
		log.error("CompanyNotFoundException - Message: {}", exception);

		Response<Void> response = new Response<>();
		response.addError(exception.getMessage());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}
}
