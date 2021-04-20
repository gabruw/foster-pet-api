package com.foster.pet.dto.authentication;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenRDTO implements Serializable {

	private static final long serialVersionUID = 8524015670453225051L;

	@NotNull(message = "O campo 'Token' não pode ser vazio.")
	private String token;
}
