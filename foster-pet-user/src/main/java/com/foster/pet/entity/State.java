package com.foster.pet.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table(name = "state")
@Entity(name = "state")
public class State implements Serializable {

	private static final long serialVersionUID = 3259874520308167531L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", unique = true, nullable = false)
	@Size(min = 1, max = 70, message = "O campo 'Estado' deve conter entre 1 e 70 caracteres.")
	private String name;

	@OneToMany(mappedBy = "state")
	private List<City> city;

	@ManyToOne
	@JoinColumn(name = "country_id", referencedColumnName = "id", nullable = false)
	private Country country;
}
