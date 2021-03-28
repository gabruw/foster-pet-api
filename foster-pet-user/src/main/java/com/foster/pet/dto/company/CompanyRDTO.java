package com.foster.pet.dto.company;

import java.io.Serializable;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CNPJ;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CompanyRDTO implements Serializable {

    private static final long serialVersionUID = 7801526329377135406L;

    @Size(min = 1, max = 200, message = "O campo 'Razão Social' deve conter entre 1 e 200 caracteres.")
    private String companyName;

    @Size(min = 1, max = 200, message = "O campo 'Nome Fantasia' deve conter entre 1 e 200 caracteres.")
    private String tradeName;

    @CNPJ(message = "O campo 'CNPJ' é inválido.")
    @Size(min = 19, max = 19, message = "O campo 'CNPJ' deve conter 19 caracteres.")
    private String cnpj;
}
