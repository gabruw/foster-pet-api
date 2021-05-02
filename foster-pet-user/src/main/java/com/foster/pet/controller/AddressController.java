package com.foster.pet.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foster.pet.dto.address.AddressFPDTO;
import com.foster.pet.dto.address.AddressFRDTO;
import com.foster.pet.dto.address.AddressHRDTO;
import com.foster.pet.dto.address.AddressRDTO;
import com.foster.pet.dto.address.AddressUserFPDTO;
import com.foster.pet.dto.address.AddressUserPDTO;
import com.foster.pet.dto.user.UserDTO;
import com.foster.pet.service.AddressService;
import com.foster.pet.util.Response;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@NoArgsConstructor
@RequestMapping("/address")
public class AddressController {

	@Autowired
	private AddressService addressService;

	@GetMapping
	@Cacheable("address")
	public ResponseEntity<Response<Page<AddressHRDTO>>> findAll(Pageable pageable) {
		log.info("Start - AddressController.findAll");
		Response<Page<AddressHRDTO>> response = new Response<>();

		Page<AddressHRDTO> addresses = this.addressService.findAll(pageable);
		response.setData(addresses);

		log.info("End - AddressController.findAll - Page<AddressHRDTO>: {}", addresses);
		return ResponseEntity.ok(response);
	}

	@Cacheable("address")
	@GetMapping(params = "id")
	public ResponseEntity<Response<AddressFRDTO>> findById(@RequestParam Long id) {
		log.info("Start - AddressController.findById - Id: {}", id);
		Response<AddressFRDTO> response = new Response<>();

		AddressFRDTO address = this.addressService.findById(id);
		response.setData(address);

		log.info("End - AddressController.findById - AddressFRDTO: {}", address);
		return ResponseEntity.ok(response);
	}

	@Cacheable("address")
	@GetMapping(params = "name")
	public ResponseEntity<Response<AddressFRDTO>> findByName(@RequestParam String name,
			@RequestBody @Valid UserDTO userDTO) {
		log.info("Start - AddressController.findByName - Name: {}, UserDTO: {}", name, userDTO);
		Response<AddressFRDTO> response = new Response<>();

		AddressFRDTO address = this.addressService.findByName(name, userDTO);
		response.setData(address);

		log.info("End - AddressController.findByName - AddressFRDTO: {}", address);
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<Response<AddressRDTO>> register(@RequestBody @Valid AddressUserPDTO addressUserPDTO) {
		log.info("Start - AddressController.register - AddressUserPDTO: {}", addressUserPDTO);
		Response<AddressRDTO> response = new Response<>();

		AddressRDTO address = this.addressService.register(addressUserPDTO);
		response.setData(address);

		log.info("End - AddressController.register - AddressRDTO: {}", address);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PutMapping
	public ResponseEntity<Response<AddressFPDTO>> edit(@RequestBody @Valid AddressUserFPDTO addressUserFPDTO) {
		log.info("Start - AddressController.edit - AddressUserFPDTO: {}", addressUserFPDTO);
		Response<AddressFPDTO> response = new Response<>();

		AddressFPDTO address = this.addressService.edit(addressUserFPDTO);
		response.setData(address);

		log.info("End - AddressController.edit - AddressFPDTO: {}", address);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping(params = "id")
	public ResponseEntity<Response<AddressHRDTO>> remove(@RequestParam Long id) {
		log.info("Start - AddressController.remove - Id: {}", id);
		Response<AddressHRDTO> response = new Response<>();

		AddressHRDTO address = this.addressService.remove(id);
		response.setData(address);

		log.info("End - AddressController.remove - AddressHRDTO: {}", address);
		return ResponseEntity.ok(response);
	}
}
