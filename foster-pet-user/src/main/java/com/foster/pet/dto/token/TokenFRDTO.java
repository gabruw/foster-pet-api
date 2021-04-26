package com.foster.pet.dto.token;

import java.io.Serializable;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.foster.pet.constant.AuthenticationRole;
import com.foster.pet.constant.UserType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenFRDTO implements Serializable {

	private static final long serialVersionUID = -9191364978079948376L;

	@NotNull(message = "O campo 'Nome do Usuário' é obrigatório")
	@Size(min = 1, max = 200, message = "O campo 'Nome do Usuário' deve conter entre 1 e 200 caracteres")
	private String name;

	@NotEmpty(message = "O campo 'Token' é obrigatório")
	private String token;

	@NotNull(message = "O campo 'Tipo de Usuário' é obrigatório")
	@Enumerated(EnumType.STRING)
	private UserType userType;

	@NotNull(message = "O campo 'Função' é obrigatório")
	@Enumerated(EnumType.STRING)
	private AuthenticationRole role;
}
