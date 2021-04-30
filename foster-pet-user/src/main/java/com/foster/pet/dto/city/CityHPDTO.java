package com.foster.pet.dto.city;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.foster.pet.dto.state.StateHPDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CityHPDTO implements Serializable {

	private static final long serialVersionUID = -5731823478681620809L;

	@NotNull(message = "O campo 'Id' é obrigatório")
	private Long id;

	@NotNull(message = "O campo 'Estado' é obrigatório")
	private StateHPDTO state;
}
