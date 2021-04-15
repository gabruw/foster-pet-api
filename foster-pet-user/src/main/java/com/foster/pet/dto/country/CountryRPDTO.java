package com.foster.pet.dto.country;

import java.io.Serializable;

import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CountryRPDTO implements Serializable {

	private static final long serialVersionUID = -4980773142709854080L;

	@Size(min = 1, max = 70, message = "O campo 'País' deve conter entre 1 e 70 caracteres.")
	private String name;
}
