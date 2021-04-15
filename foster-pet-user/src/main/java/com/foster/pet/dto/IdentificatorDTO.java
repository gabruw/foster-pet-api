package com.foster.pet.dto;

import java.io.Serializable;

import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class IdentificatorDTO implements Serializable {

	private static final long serialVersionUID = -6136766525818392534L;

	@Size(min = 1, max = 11, message = "O campo 'Id' deve conter entre 1 e 11 caracteres.")
	private Long id;
}
