package com.foster.pet.controller;

import javax.validation.Valid;
import javax.validation.constraints.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foster.pet.entity.Authentication;
import com.foster.pet.service.AuthenticationService;
import com.foster.pet.util.Response;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@NoArgsConstructor
@RequestMapping("/authentication")
public class AuthenticationController {

	@Autowired
	private AuthenticationService authenticationService;

	@Cacheable("authentication")
	@GetMapping(params = "id")
	public ResponseEntity<Response<Authentication>> getById(@RequestParam Long id) {
		log.info("Start - AuthenticationController.getById - Id: {}", id);
		Response<Authentication> response = new Response<Authentication>();

		Authentication authentication = this.authenticationService.findById(id);
		response.setData(authentication);

		log.info("End - AuthenticationController.getById - Authentication: {}", authentication.toString());
		return ResponseEntity.ok(response);
	}

	@Cacheable("authentication")
	@GetMapping(params = "email")
	public ResponseEntity<Response<Authentication>> getByEmail(@RequestParam @Email @Valid String email) {
		log.info("Start - AuthenticationController.getByEmail - Email: {}", email);
		Response<Authentication> response = new Response<Authentication>();

		Authentication authentication = this.authenticationService.findByEmail(email);
		response.setData(authentication);

		log.info("End - AuthenticationController.getByEmail - Authentication: {}", authentication.toString());
		return ResponseEntity.ok(response);
	}
}
