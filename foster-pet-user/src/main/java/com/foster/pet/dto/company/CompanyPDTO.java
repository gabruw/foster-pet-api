package com.foster.pet.dto.company;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CNPJ;

import com.foster.pet.dto.address.AddressPDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CompanyPDTO implements Serializable {

	private static final long serialVersionUID = 5298156494112117977L;

	@Size(min = 1, max = 200, message = "O campo 'Razão Social' deve conter entre 1 e 200 caracteres.")
	private String companyName;

	@Size(min = 1, max = 200, message = "O campo 'Nome Fantasia' deve conter entre 1 e 200 caracteres.")
	private String tradeName;

	@CNPJ(message = "O campo 'CNPJ' é inválido.")
	@Size(min = 19, max = 19, message = "O campo 'CNPJ' deve conter 19 caracteres.")
	private String cnpj;

	@NotNull
	private List<AddressPDTO> addresses;
}
