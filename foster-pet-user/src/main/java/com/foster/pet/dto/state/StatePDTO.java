package com.foster.pet.dto.state;

import java.io.Serializable;

import javax.persistence.Column;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StatePDTO implements Serializable {

	private static final long serialVersionUID = -110937782251683215L;

	@Size(min = 1, max = 11, message = "O campo 'Id' deve conter entre 1 e 11 caracteres.")
	private Long id;

	@Column(name = "name", unique = true, nullable = false)
	@Size(min = 1, max = 70, message = "O campo 'Estado' deve conter entre 1 e 70 caracteres.")
	private String name;
}
