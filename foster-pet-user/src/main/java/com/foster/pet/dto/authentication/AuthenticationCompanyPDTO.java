package com.foster.pet.dto.authentication;

import java.io.Serializable;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.foster.pet.constant.AuthenticationRoleEnum;
import com.foster.pet.dto.company.CompanyPDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthenticationCompanyPDTO implements Serializable {

	private static final long serialVersionUID = -1005444019593483919L;

	@Email(message = "O campo 'Email' é inválido")
	@Size(min = 6, max = 80, message = "O campo 'Email' deve conter entre 6 e 80 caracteres.")
	private String email;

	@Size(min = 6, max = 40, message = "O campo 'Senha' deve conter entre 6 a 40 caracteres.")
	private String password;

	@Enumerated(EnumType.STRING)
	private AuthenticationRoleEnum role;

	@NotNull
	private CompanyPDTO company;
}
