package com.foster.pet.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.foster.pet.dto.address.AddressFPDTO;
import com.foster.pet.dto.address.AddressFRDTO;
import com.foster.pet.dto.address.AddressHRDTO;
import com.foster.pet.dto.address.AddressRDTO;
import com.foster.pet.dto.address.AddressUserFPDTO;
import com.foster.pet.dto.address.AddressUserPDTO;
import com.foster.pet.dto.user.UserDTO;

public interface AddressService {

	Page<AddressHRDTO> findAll(Pageable pageable);

	Page<AddressHRDTO> findAllByCep(String cep, Pageable pageable);

	List<AddressHRDTO> findAllByUserId(UserDTO userDTO);

	AddressFRDTO findById(Long id);

	AddressFRDTO findByName(String name, UserDTO userDTO);

	AddressRDTO register(AddressUserPDTO addressUserPDTO);

	AddressFPDTO edit(AddressUserFPDTO addressUserFPDTO);

	AddressHRDTO remove(Long id);
}
