package com.foster.pet.dto.address;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.foster.pet.dto.city.CityHPDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressPDTO implements Serializable {

	private static final long serialVersionUID = -5993559822441053455L;

	@Size(min = 1, max = 70, message = "O campo 'Nome' deve conter entre 1 e 70 caracteres.")
	private String name;

	@Size(min = 9, max = 9, message = "O campo 'CEP' deve conter 9 caracteres.")
	private String cep;

	@Size(min = 1, max = 255, message = "O campo 'Rua' deve conter entre 1 e 255 caracteres.")
	private String road;

	@Size(min = 1, max = 255, message = "O campo 'Bairro' deve conter entre 1 e 255 caracteres.")
	private String neighborhood;

	@NotNull(message = "O campo 'Número' é obrigatório.")
	private Integer number;

	@Size(max = 255, message = "O campo 'Complemento' deve conter no máximo 255 caracteres.")
	private String complement;

	@Size(min = 13, max = 13, message = "O campo 'Nº de Telefone' deve conter 13 caracteres.")
	private String phone;

	@NotNull
	private CityHPDTO city;
}
