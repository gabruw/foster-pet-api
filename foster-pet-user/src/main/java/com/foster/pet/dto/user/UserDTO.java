package com.foster.pet.dto.user;

import java.io.Serializable;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.foster.pet.constant.UserType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable {

	private static final long serialVersionUID = 4405336524165598241L;

	@NotEmpty(message = "O campo 'Id' é obrigatório")
	private Long id;

	@NotNull(message = "O campo 'Tipo de Usuário' é obrigatório")
	@Enumerated(EnumType.STRING)
	private UserType userType;
}
