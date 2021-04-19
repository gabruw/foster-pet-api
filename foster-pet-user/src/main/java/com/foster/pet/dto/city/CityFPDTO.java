package com.foster.pet.dto.city;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.foster.pet.dto.state.StateHPDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CityFPDTO implements Serializable {

	private static final long serialVersionUID = -4585729593872682671L;

	@Size(min = 1, max = 11, message = "O campo 'Id' deve conter entre 1 e 11 caracteres.")
	private Long id;

	@Size(min = 1, max = 70, message = "O campo 'Cidade' deve conter entre 1 e 70 caracteres.")
	private String name;

	@NotNull
	private StateHPDTO state;
}
