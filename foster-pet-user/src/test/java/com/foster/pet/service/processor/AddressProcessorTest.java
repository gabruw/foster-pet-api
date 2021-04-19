package com.foster.pet.service.processor;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.foster.pet.constant.ErrorCode;
import com.foster.pet.entity.Address;
import com.foster.pet.exception.city.CityNotFoundException;
import com.foster.pet.exception.state.StateNotFoundException;
import com.foster.pet.repository.AddressRepository;
import com.foster.pet.repository.CityRepository;
import com.foster.pet.repository.StateRepository;
import com.foster.pet.service.prcr.AddressProcessor;

import properties.address.AddressInstance;
import properties.address.AddressProperties;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Processor - Address")
public class AddressProcessorTest extends AddressProperties {

	@MockBean
	private CityRepository cityRepository;

	@MockBean
	private StateRepository stateRepository;

	@MockBean
	private AddressRepository addressRepository;

	@Autowired
	private AddressProcessor addressProcessor;

	@Mock
	private Address address;

	@Mock
	private List<Address> addresses;

	@BeforeEach
	public void init() {
		this.address = AddressInstance.instace();
		this.addresses = AddressInstance.instaceAddresses(3);
	}

	@Test
	@DisplayName("Validade address to persist")
	public void validateToPersist() {
		when(this.cityRepository.findById(this.address.getCity().getId()))
				.thenReturn(Optional.of(this.address.getCity()));
		when(this.stateRepository.findById(this.address.getCity().getState().getId()))
				.thenReturn(Optional.of(this.address.getCity().getState()));

		List<Address> addressesWithCityAndStateOnlyId = AddressInstance.instaceAddressesWithCityAndStateOnlyId(3);
		List<Address> validateToPersist = this.addressProcessor.validade(addressesWithCityAndStateOnlyId);
		
		assertArrayEquals(this.addresses.toArray(), validateToPersist.toArray());
	}

	@Test
	@DisplayName("Validade address to persist with a city not founded")
	public void validateToPersistWithNotFoundedCityId() {
		CityNotFoundException exception = assertThrows(CityNotFoundException.class, () -> {
			this.addressProcessor.validade(this.addresses);
		});

		assertEquals(ErrorCode.CITY_NOT_FOUND.getMessage(), exception.getMessage());
	}

	@Test
	@DisplayName("Validade address to persist with a state not founded")
	public void validateToPersistWithNotFoundedStateId() {
		StateNotFoundException exception = assertThrows(StateNotFoundException.class, () -> {
			when(this.cityRepository.findById(this.address.getCity().getId()))
					.thenReturn(Optional.of(this.address.getCity()));

			this.addressProcessor.validade(this.addresses);
		});

		assertEquals(ErrorCode.STATE_NOT_FOUND.getMessage(), exception.getMessage());
	}
}
