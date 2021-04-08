package com.foster.pet.dto.state;

import java.io.Serializable;

import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StateAddressPDTO implements Serializable {

	private static final long serialVersionUID = 6893487502721931547L;
	
	@Size(min = 1, max = 11, message = "O campo 'Id' deve conter entre 1 e 11 caracteres.")
	private Long id;
}
