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
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CNPJ;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table(name = "ngo")
@Entity(name = "ngo")
public class NGO implements Serializable {

	private static final long serialVersionUID = 5447259389535728653L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "entityName", nullable = false)
	@Size(min = 1, max = 200, message = "O campo 'Nome da Entidade' deve conter entre 1 e 200 caracteres.")
	private String entityName;

	@Column(name = "tradeName", nullable = false)
	@Size(min = 1, max = 200, message = "O campo 'Nome Fantasia' deve conter entre 1 e 200 caracteres.")
	private String tradeName;

	@CNPJ(message = "O campo 'CNPJ' é inválido.")
	@Column(name = "cnpj", unique = true, nullable = true)
	@Size(min = 19, max = 19, message = "O campo 'CNPJ' deve conter 19 caracteres.")
	private String cnpj;

	@ManyToMany(mappedBy = "ngo")
	private List<Address> addresses;

	@ManyToMany
	@JoinTable(name = "ngo_employee", joinColumns = @JoinColumn(name = "ngo_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"))
	private List<Employee> employee;
}
