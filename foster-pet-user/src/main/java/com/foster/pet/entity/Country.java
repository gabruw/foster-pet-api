package com.foster.pet.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "country")
@Entity(name = "country")
public class Country implements Serializable {
	private static final long serialVersionUID = -535474536255654306L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "country", nullable = false)
	@Size(min = 1, max = 70, message = "O campo 'País' deve conter entre 1 e 70 caracteres.")
	private String country;
}
