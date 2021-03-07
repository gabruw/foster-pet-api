package com.foster.pet.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Table(name = "country")
@Entity(name = "country")
public class Country implements Serializable {

	private static final long serialVersionUID = -535474536255654306L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false)
	@Size(min = 1, max = 70, message = "O campo 'País' deve conter entre 1 e 70 caracteres.")
	private String name;

	@OneToMany(mappedBy = "country")
	private List<State> state;
}
