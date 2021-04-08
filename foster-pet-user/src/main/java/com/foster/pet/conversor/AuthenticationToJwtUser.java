package com.foster.pet.conversor;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.foster.pet.entity.Authentication;
import com.foster.pet.security.entity.JwtUser;

public class AuthenticationToJwtUser implements Converter<Authentication, JwtUser> {

	@Override
	public JwtUser convert(MappingContext<Authentication, JwtUser> context) {
		Authentication source = context.getSource();

		JwtUser destination = new JwtUser();
		destination.setId(source.getId());
		destination.setEmail(source.getEmail());
		destination.setPassword(source.getPassword());

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(source.getRole().toString()));

		destination.setAuthorities(authorities);
		return destination;
	}
}
