package com.foster.pet.conversor.authentication;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.foster.pet.dto.authentication.AuthenticationFRPDTO;
import com.foster.pet.security.entity.JwtUser;

public class AuthenticationFRPDTOToJwtUser implements Converter<AuthenticationFRPDTO, JwtUser> {

	@Override
	public JwtUser convert(MappingContext<AuthenticationFRPDTO, JwtUser> context) {
		AuthenticationFRPDTO source = context.getSource();

		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(source.getRole().name()));

		return JwtUser.builder().id(source.getId()).email(source.getEmail()).password(source.getPassword())
				.authorities(authorities).build();
	}
}
