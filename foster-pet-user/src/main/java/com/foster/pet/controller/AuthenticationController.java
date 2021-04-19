package com.foster.pet.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foster.pet.dto.authentication.AuthenticationFRPDTO;
import com.foster.pet.dto.token.TokenRDTO;
import com.foster.pet.exception.token.TokenEmptyException;
import com.foster.pet.service.AuthenticationService;
import com.foster.pet.util.JwtUtil;
import com.foster.pet.util.Response;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@NoArgsConstructor
@RequestMapping("/authentication")
public class AuthenticationController {

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private AuthenticationService authenticationService;

	@Cacheable("authentication")
	@GetMapping(params = "id")
	public ResponseEntity<Response<AuthenticationFRPDTO>> findById(@RequestParam Long id) {
		log.info("Start - AuthenticationController.findById - Id: {}", id);
		Response<AuthenticationFRPDTO> response = new Response<>();

		AuthenticationFRPDTO authentication = this.authenticationService.findById(id);
		response.setData(authentication);

		log.info("End - AuthenticationController.findById - Authentication: {}", authentication.toString());
		return ResponseEntity.ok(response);
	}

	@Cacheable("authentication")
	@GetMapping(params = "email")
	public ResponseEntity<Response<AuthenticationFRPDTO>> findByEmail(@RequestParam @Email @Valid String email) {
		log.info("Start - AuthenticationController.findByEmail - Email: {}", email);
		Response<AuthenticationFRPDTO> response = new Response<>();

		AuthenticationFRPDTO authentication = this.authenticationService.findByEmail(email);
		response.setData(authentication);

		log.info("End - AuthenticationController.findByEmail - AuthenticationFRPDTO: {}", authentication.toString());
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "/refresh")
	public ResponseEntity<Response<TokenRDTO>> refresh(@RequestHeader(name = "Authorization") String authorization,
			HttpServletRequest request) {
		log.info("Start - AuthenticationController.refresh - HttpServletRequest: {}", request.toString());
		Response<TokenRDTO> response = new Response<>();

		Optional<String> token = Optional.ofNullable(request.getHeader(this.jwtTokenUtil.getHeader()));
		if (token.isEmpty()) {
			log.error("TokenEmptyException - HttpServletRequest: {}", request.toString());
			throw new TokenEmptyException();
		}

		TokenRDTO returnedToken = this.authenticationService.refresh(token.get());
		response.setData(returnedToken);

		log.info("End - AuthenticationController.refresh - TokenRDTO: {}", returnedToken.toString());
		return ResponseEntity.ok(response);
	}
}
