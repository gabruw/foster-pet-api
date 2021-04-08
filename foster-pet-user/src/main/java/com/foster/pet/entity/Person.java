package com.foster.pet.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import com.foster.pet.constant.GenderEnum;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table(name = "person")
@Entity(name = "person")
public class Person implements Serializable {

	private static final long serialVersionUID = -4520661629094456421L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false)
	@Size(min = 1, max = 200, message = "O campo 'Nome' deve conter entre 1 e 200 caracteres.")
	private String name;

	@Column(name = "birth", nullable = false)
	@NotNull(message = "O campo 'Data de Nascimento' é obrigatório.")
	private Date birth;

	@CPF(message = "O campo 'CPF' é inválido.")
	@Column(name = "cpf", unique = true, nullable = false)
	@Size(min = 14, max = 14, message = "O campo 'CPF' deve conter 14 caracteres.")
	private String cpf;

	@Column(name = "cell", nullable = true)
	@Size(min = 14, max = 14, message = "O campo 'Nº de Celular' deve conter 14 caracteres.")
	private String cell;

	@Enumerated(EnumType.STRING)
	@Column(name = "gender", nullable = false)
	private GenderEnum gender;

	@OneToOne(mappedBy = "person")
	private Employee employee;

	@ManyToMany(mappedBy = "person")
	private List<Address> addresses;

	@OneToOne(mappedBy = "person")
	private Authentication authentication;
}
