package com.foster.pet.dto.city;

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
public class CityHRDTO implements Serializable {

	private static final long serialVersionUID = 6563935151606616339L;

	@NotNull(message = "O campo 'Cidade' é obrigatório")
	@Size(min = 1, max = 70, message = "O campo 'Cidade' deve conter entre 1 e 70 caracteres")
	private String name;
}
