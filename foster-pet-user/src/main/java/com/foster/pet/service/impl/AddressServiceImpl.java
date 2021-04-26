package com.foster.pet.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.foster.pet.dto.address.AddressFPDTO;
import com.foster.pet.dto.address.AddressFRDTO;
import com.foster.pet.dto.address.AddressHRDTO;
import com.foster.pet.dto.address.AddressRDTO;
import com.foster.pet.dto.address.AddressUserFPDTO;
import com.foster.pet.dto.address.AddressUserPDTO;
import com.foster.pet.dto.user.UserDTO;
import com.foster.pet.entity.Address;
import com.foster.pet.repository.AddressRepository;
import com.foster.pet.service.AddressService;
import com.foster.pet.service.prcr.AddressProcessor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private AddressProcessor addressProcessor;

	@Autowired
	private AddressRepository addressRepository;

	@Override
	public Page<AddressHRDTO> findAll(Pageable pageable) {
		log.info("Start - AddressServiceImpl.findAll - Pageable: {}", pageable);

		Page<Address> addresses = this.addressRepository.findAll(pageable);
		Page<AddressHRDTO> fltAddresses = addresses.map(address -> this.mapper.map(address, AddressHRDTO.class));

		log.info("End - AddressServiceImpl.findAll - Page<AddressHRDTO>: {}", fltAddresses);
		return fltAddresses;
	}

	@Override
	public Page<AddressHRDTO> findAllByCep(String cep, Pageable pageable) {
		log.info("Start - AddressServiceImpl.findAllByCep - Pageable: {}", pageable);

		Page<Address> addresses = this.addressRepository.findAllByCep(cep, pageable);
		Page<AddressHRDTO> fltAddresses = addresses.map(address -> this.mapper.map(address, AddressHRDTO.class));

		log.info("End - AddressServiceImpl.findAllByCep - Page<AddressHRDTO>: {}", fltAddresses);
		return fltAddresses;
	}

	@Override
	public List<AddressHRDTO> findAllByUserId(UserDTO userDTO) {
		log.info("Start - AddressServiceImpl.findAll - UserDTO: {}", userDTO);

		List<Address> addresses = this.addressProcessor.getAddresses(userDTO);
		List<AddressHRDTO> fltAddresses = addresses.stream()
				.map(address -> this.mapper.map(address, AddressHRDTO.class)).collect(Collectors.toList());

		log.info("End - AddressServiceImpl.findAll - List<AddressHRDTO>: {}", fltAddresses);
		return fltAddresses;
	}

	@Override
	public AddressFRDTO findById(Long id) {
		log.info("Start - AddressServiceImpl.findById - Id: {}", id);

		Address address = this.addressProcessor.exists(id);
		AddressFRDTO addressFRDTO = this.mapper.map(address, AddressFRDTO.class);

		log.info("End - AddressServiceImpl.findById - AddressFRDTO {}", addressFRDTO);
		return addressFRDTO;
	}

	@Override
	public AddressFRDTO findByName(UserDTO userDTO, String name) {
		log.info("Start - AddressServiceImpl.findByName - UserDTO: {}, Name: {}", userDTO, name);

		List<Address> addresses = this.addressProcessor.getAddresses(userDTO);
		
		Address address = this.addressProcessor.exists(addresses, name);
		AddressFRDTO addressFRDTO = this.mapper.map(address, AddressFRDTO.class);

		log.info("End - AddressServiceImpl.findByName - AddressFRDTO: {}", addressFRDTO);
		return addressFRDTO;
	}

	@Override
	public AddressRDTO register(AddressUserPDTO addressUserPDTO) {
		log.info("Start - AddressServiceImpl.register - AddressUserPDTO: {}", addressUserPDTO);

		List<Address> addresses = this.addressProcessor.getAddresses(addressUserPDTO.getUser());
		this.addressProcessor.alreadyExists(addresses, addressUserPDTO.getAddress().getName());

		Address address = this.mapper.map(addressUserPDTO.getAddress(), Address.class);
		address = this.addressRepository.save(address);

		AddressRDTO addressRDTO = this.mapper.map(address, AddressRDTO.class);

		log.info("End - AddressServiceImpl.register - AddressRDTO: {}", addressRDTO);
		return addressRDTO;
	}

	@Override
	public AddressFPDTO edit(AddressUserFPDTO addressUserFPDTO) {
		log.info("Start - AddressServiceImpl.edit - AddressUserFPDTO: {}", addressUserFPDTO);

		this.addressProcessor.exists(addressUserFPDTO.getUser().getId());

		Address address = this.mapper.map(addressUserFPDTO.getAddress(), Address.class);
		address = this.addressRepository.save(address);

		AddressFPDTO addressFPDTO = this.mapper.map(address, AddressFPDTO.class);

		log.info("End - AddressServiceImpl.edit - AddressFPDTO: {}", addressFPDTO);
		return addressFPDTO;
	}

	@Override
	public AddressHRDTO remove(Long id) {
		log.info("Start - AddressServiceImpl.remove - Id: {}", id);

		Address address = this.addressProcessor.exists(id);
		this.addressRepository.deleteById(id);

		AddressHRDTO addressHRDTO = this.mapper.map(address, AddressHRDTO.class);

		log.info("End - AddressServiceImpl.remove - AddressHRDTO: {}", addressHRDTO);
		return addressHRDTO;
	}
}
