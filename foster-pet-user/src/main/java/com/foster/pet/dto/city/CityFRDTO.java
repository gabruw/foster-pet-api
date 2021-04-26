package com.foster.pet.dto.city;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.foster.pet.dto.state.StateFRPDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CityFRDTO implements Serializable {

	private static final long serialVersionUID = -7619822387652277936L;

	@NotEmpty(message = "O campo 'Id' é obrigatório")
	private Long id;

	@NotNull(message = "O campo 'Cidade' é obrigatório")
	@Size(min = 1, max = 70, message = "O campo 'Cidade' deve conter entre 1 e 70 caracteres")
	private String name;

	@NotNull(message = "O campo 'Estado' é obrigatório")
	private StateFRPDTO state;
}
