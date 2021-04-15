package com.foster.pet.dto.country;

import java.io.Serializable;

import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CountryFRPDTO implements Serializable {

	private static final long serialVersionUID = -8378013755698502162L;

	@Size(min = 1, max = 11, message = "O campo 'Id' deve conter entre 1 e 11 caracteres.")
	private Long id;

	@Size(min = 1, max = 70, message = "O campo 'País' deve conter entre 1 e 70 caracteres.")
	private String name;
}
