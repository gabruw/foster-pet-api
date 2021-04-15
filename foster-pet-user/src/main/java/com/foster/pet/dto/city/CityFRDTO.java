package com.foster.pet.dto.city;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.foster.pet.dto.state.StateFRDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CityFRDTO implements Serializable {

	private static final long serialVersionUID = -7619822387652277936L;

	@Size(min = 1, max = 11, message = "O campo 'Id' deve conter entre 1 e 11 caracteres.")
	private Long id;

	@Size(min = 1, max = 70, message = "O campo 'Estado' deve conter entre 1 e 70 caracteres.")
	private String name;

	@NotNull
	private StateFRDTO state;
}
