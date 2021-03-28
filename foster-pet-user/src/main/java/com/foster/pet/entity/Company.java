package com.foster.pet.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CNPJ;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table(name = "company")
@Entity(name = "company")
public class Company implements Serializable {

	private static final long serialVersionUID = -6975927235568106102L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "companyName", nullable = false)
	@Size(min = 1, max = 200, message = "O campo 'Razão Social' deve conter entre 1 e 200 caracteres.")
	private String companyName;

	@Column(name = "tradeName", nullable = false)
	@Size(min = 1, max = 200, message = "O campo 'Nome Fantasia' deve conter entre 1 e 200 caracteres.")
	private String tradeName;

	@CNPJ(message = "O campo 'CNPJ' é inválido.")
	@Column(name = "cnpj", unique = true, nullable = false)
	@Size(min = 19, max = 19, message = "O campo 'CNPJ' deve conter 19 caracteres.")
	private String cnpj;

	@ManyToMany
	@JoinTable(name = "company_employee", joinColumns = @JoinColumn(name = "company_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"))
	private List<Employee> employee;

	@ManyToMany(mappedBy = "company")
	private List<Address> addresses;

	@OneToOne(mappedBy = "company")
	private Authentication authentication;
}
