package com.foster.pet.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IdentificatorDTO implements Serializable {

	private static final long serialVersionUID = -6136766525818392534L;

	@NotEmpty(message = "O campo 'Id' é obrigatório")
	private Long id;
}
