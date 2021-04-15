package com.foster.pet.dto.city;

import java.io.Serializable;

import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CityHRDTO implements Serializable {

	private static final long serialVersionUID = 6563935151606616339L;
	
	@Size(min = 1, max = 70, message = "O campo 'Cidade' deve conter entre 1 e 70 caracteres.")
	private String name;
}
