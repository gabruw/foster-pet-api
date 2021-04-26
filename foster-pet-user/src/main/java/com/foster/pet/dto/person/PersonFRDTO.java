package com.foster.pet.dto.person;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import com.foster.pet.constant.Gender;
import com.foster.pet.dto.address.AddressPDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonFRDTO implements Serializable {

	private static final long serialVersionUID = -3920706731592697170L;

	@NotEmpty(message = "O campo 'Id' é obrigatório")
	private Long id;

	@NotNull(message = "O campo 'Nome' é obrigatório")
	@Size(min = 1, max = 200, message = "O campo 'Nome' deve conter entre 1 e 200 caracteres")
	private String name;

	@NotNull(message = "O campo 'Data de Nascimento' é obrigatório")
	private LocalDate birth;

	@NotNull(message = "O campo 'CPF' é obrigatório")
	@Size(min = 14, max = 14, message = "O campo 'CPF' deve conter 14 caracteres")
	@CPF(message = "O campo 'CPF' é inválido")
	private String cpf;

	@NotNull(message = "O campo 'Nº de Celular' é obrigatório")
	@Size(min = 14, max = 14, message = "O campo 'Nº de Celular' deve conter 14 caracteres")
	private String cell;

	@NotNull(message = "O campo 'Gênero' é obrigatório")
	@Enumerated(EnumType.STRING)
	private Gender gender;

	@NotNull(message = "O campo 'Endereços' é obrigatório")
	private List<AddressPDTO> addresses;
}
