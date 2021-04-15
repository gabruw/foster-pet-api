package com.foster.pet.dto.state;

import java.io.Serializable;

import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StateHRDTO implements Serializable {

	private static final long serialVersionUID = -8378013755698502162L;

	@Size(min = 1, max = 70, message = "O campo 'Estado' deve conter entre 1 e 70 caracteres.")
	private String name;
}
