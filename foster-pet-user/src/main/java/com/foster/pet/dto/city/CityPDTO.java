package com.foster.pet.dto.city;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.foster.pet.dto.state.StateHPDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CityPDTO implements Serializable {

	private static final long serialVersionUID = 1583437306224764410L;

	@Size(min = 1, max = 70, message = "O campo 'Cidade' deve conter entre 1 e 70 caracteres.")
	private String name;

	@NotNull
	private StateHPDTO state;
}
