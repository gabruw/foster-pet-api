package com.foster.pet.dto.state;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.foster.pet.dto.country.CountryFRPDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StateFRPDTO implements Serializable {

	private static final long serialVersionUID = -110937782251683215L;

	@Size(min = 1, max = 11, message = "O campo 'Id' deve conter entre 1 e 11 caracteres.")
	private Long id;
	
	@Size(min = 1, max = 70, message = "O campo 'Cidade' deve conter entre 1 e 70 caracteres.")
	private String name;

	@NotNull
	private CountryFRPDTO country;
}
