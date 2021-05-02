package com.foster.pet.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.foster.pet.constant.AuthenticationRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "authentication")
@Entity(name = "authentication")
public class Authentication implements Serializable {

	private static final long serialVersionUID = 3128457805381586291L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "email", unique = true, nullable = false)
	@Size(min = 6, max = 80, message = "O campo 'Email' deve conter entre 6 e 80 caracteres")
	private String email;

	@ToString.Exclude
	@Column(name = "password", nullable = false)
	@Size(min = 8, message = "O campo 'Senha' deve conter 8 ou mais caracteres")
	private String password;

	@Enumerated(EnumType.STRING)
	@Column(name = "role", nullable = false)
	private AuthenticationRole role;

	@Column(name = "isLocked", nullable = false)
	private Boolean isLocked;

	@Column(name = "isEnabled", nullable = false)
	private Boolean isEnabled;

	@ToString.Exclude
	@OneToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "person_authentication", joinColumns = {
			@JoinColumn(name = "person_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "authentication_id", referencedColumnName = "id") })
	private Person person;

	@ToString.Exclude
	@OneToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "company_authentication", joinColumns = {
			@JoinColumn(name = "company_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "authentication_id", referencedColumnName = "id") })
	private Company company;
}
