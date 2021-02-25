package com.foster.pet.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import com.foster.pet.constant.GenderEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "user")
@Entity(name = "user")
public class User implements Serializable {
	private static final long serialVersionUID = 3259874520308178951L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false)
	@Size(min = 1, max = 200, message = "O campo 'Nome' deve conter entre 1 e 200 caracteres.")
	private String name;

	@CPF
	@Column(name = "cpf", unique = true, nullable = false)
	@Size(min = 14, max = 14, message = "O campo 'CPF' deve conter 14 caracteres.")
	private String cpf;

	@Enumerated(EnumType.STRING)
	@Column(name = "gender", nullable = false)
	private GenderEnum gender;

	@Column(name = "birth", nullable = false)
	@NotNull(message = "O campo 'Data de Nascimento' é obrigatório.")
	private Date birth;
}
