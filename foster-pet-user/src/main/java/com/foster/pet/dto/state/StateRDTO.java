package com.foster.pet.dto.state;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.foster.pet.dto.country.CountryRPDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StateRDTO implements Serializable {

	private static final long serialVersionUID = -6969615747215487546L;

	@NotNull(message = "O campo 'Cidade' é obrigatório")
	@Size(min = 1, max = 70, message = "O campo 'Estado' deve conter entre 1 e 70 caracteres")
	private String name;

	@NotNull(message = "O campo 'País' é obrigatório")
	private CountryRPDTO country;
}
