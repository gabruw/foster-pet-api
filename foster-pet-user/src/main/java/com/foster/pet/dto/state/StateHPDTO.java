package com.foster.pet.dto.state;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.foster.pet.dto.IdentificatorDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StateHPDTO implements Serializable {

	private static final long serialVersionUID = -8378013755698502162L;

	@NotEmpty(message = "O campo 'Id' é obrigatório")
	private Long id;

	@NotNull(message = "O campo 'País' é obrigatório")
	private IdentificatorDTO country;
}
