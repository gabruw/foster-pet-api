package com.foster.pet.dto.address;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.foster.pet.annotation.CEP;
import com.foster.pet.dto.city.CityHPDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressHRDTO implements Serializable {

	private static final long serialVersionUID = 5477693754000776501L;

	@NotNull(message = "O campo 'Nome' é obrigatório")
	@Size(min = 1, max = 70, message = "O campo 'Nome' deve conter entre 1 e 70 caracteres")
	private String name;

	@NotNull(message = "O campo 'CEP' é obrigatório")
	@Size(min = 9, max = 9, message = "O campo 'CEP' deve conter 9 caracteres")
	@CEP
	private String cep;

	@NotNull(message = "O campo 'Rua' é obrigatório")
	@Size(min = 1, max = 255, message = "O campo 'Rua' deve conter entre 1 e 255 caracteres")
	private String road;

	@NotNull(message = "O campo 'Bairro' é obrigatório")
	@Size(min = 1, max = 255, message = "O campo 'Bairro' deve conter entre 1 e 255 caracteres")
	private String neighborhood;

	@NotEmpty(message = "O campo 'Número' é obrigatório")
	private Integer number;

	@NotNull(message = "O campo 'Complemento' é obrigatório")
	@Size(max = 255, message = "O campo 'Complemento' deve conter no máximo 255 caracteres")
	private String complement;

	@NotNull(message = "O campo 'Telefone' é obrigatório")
	@Size(min = 13, max = 13, message = "O campo 'Nº de Telefone' deve conter 13 caracteres")
	private String phone;

	@NotNull(message = "O campo 'Cidade' é obrigatório")
	private CityHPDTO city;
}
