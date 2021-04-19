package com.foster.pet.dto.authentication;

import java.io.Serializable;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import com.foster.pet.constant.AuthenticationRoleEnum;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthenticationFRPDTO implements Serializable {

	private static final long serialVersionUID = 4852433818663915173L;

	@Size(min = 1, max = 11, message = "O campo 'Id' deve conter entre 1 e 11 caracteres.")
	private Long id;

	@Email(message = "O campo 'Email' é inválido")
	@Size(min = 6, max = 80, message = "O campo 'Email' deve conter entre 6 e 80 caracteres.")
	private String email;

	@Size(min = 6, max = 40, message = "O campo 'Senha' deve conter entre 6 a 40 caracteres.")
	private String password;

	@Enumerated(EnumType.STRING)
	private AuthenticationRoleEnum role;
}
