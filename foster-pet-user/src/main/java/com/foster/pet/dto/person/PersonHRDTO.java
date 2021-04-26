package com.foster.pet.dto.person;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import com.foster.pet.constant.Gender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonHRDTO implements Serializable {

	private static final long serialVersionUID = 5782215103874672862L;

	@Size(min = 1, max = 200, message = "O campo 'Nome' deve conter entre 1 e 200 caracteres")
	private String name;

	@NotNull(message = "O campo 'Data de Nascimento' é obrigatório")
	private LocalDate birth;

	@CPF(message = "O campo 'CPF' é inválido")
	@Size(min = 14, max = 14, message = "O campo 'CPF' deve conter 14 caracteres")
	private String cpf;

	@Size(min = 14, max = 14, message = "O campo 'Nº de Celular' deve conter 14 caracteres")
	private String cell;

	@Enumerated(EnumType.STRING)
	private Gender gender;
}
