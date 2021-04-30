package com.foster.pet.dto.company;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CNPJ;

import com.foster.pet.dto.address.AddressPDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyFPDTO implements Serializable {

	private static final long serialVersionUID = -3331492576958844721L;

	@NotNull(message = "O campo 'Id' é obrigatório")
	private Long id;

	@NotNull(message = "O campo 'Razão Social' é obrigatório")
	@Size(min = 1, max = 200, message = "O campo 'Razão Social' deve conter entre 1 e 200 caracteres")
	private String companyName;

	@NotNull(message = "O campo 'Nome' é obrigatório")
	@Size(min = 1, max = 200, message = "O campo 'Nome Fantasia' deve conter entre 1 e 200 caracteres")
	private String tradeName;

	@NotNull(message = "O campo 'Nome' é obrigatório")
	@Size(min = 19, max = 19, message = "O campo 'CNPJ' deve conter 19 caracteres")
	@CNPJ(message = "O campo 'CNPJ' é inválido")
	private String cnpj;

	@NotNull(message = "O campo 'Endereços' é obrigatório")
	private List<AddressPDTO> addresses;
}
