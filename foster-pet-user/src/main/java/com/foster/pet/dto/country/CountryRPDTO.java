package com.foster.pet.dto.country;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountryRPDTO implements Serializable {

	private static final long serialVersionUID = -4980773142709854080L;

	@NotNull(message = "O campo 'País' é obrigatório")
	@Size(min = 1, max = 70, message = "O campo 'País' deve conter entre 1 e 70 caracteres")
	private String name;
}
