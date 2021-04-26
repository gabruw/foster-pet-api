package com.foster.pet.dto.address;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.foster.pet.dto.user.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressUserPDTO implements Serializable {

	private static final long serialVersionUID = -6141149076341508970L;

	@NotNull(message = "O campo 'Usuário' é obrigatório")
	private UserDTO user;

	@NotNull(message = "O campo 'Endereço' é obrigatório")
	private AddressPDTO address;
}
