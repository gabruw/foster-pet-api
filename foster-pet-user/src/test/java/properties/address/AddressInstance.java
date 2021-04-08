package properties.address;

import java.util.ArrayList;
import java.util.List;

import com.foster.pet.entity.Address;
import com.foster.pet.entity.City;

import properties.city.CityInstance;

public class AddressInstance extends AddressProperties {

	public static Address instace() {
		City city = CityInstance.instace();

		Address address = new Address();
		address.setId(ID);
		address.setCep(CEP);
		address.setName(NAME);
		address.setRoad(ROAD);
		address.setPhone(PHONE);
		address.setNumber(NUMBER);
		address.setComplement(COMPLEMENT);
		address.setNeighborhood(NEIGHBORHOOD);

		address.setCity(city);
		return address;
	}

	public static Address instaceCityAndStateOnlyId() {
		City city = CityInstance.instaceOnlyId();

		Address address = new Address();
		address.setId(ID);
		address.setCep(CEP);
		address.setName(NAME);
		address.setRoad(ROAD);
		address.setPhone(PHONE);
		address.setNumber(NUMBER);
		address.setComplement(COMPLEMENT);
		address.setNeighborhood(NEIGHBORHOOD);

		address.setCity(city);
		return address;
	}

	public static List<Address> instaceAddresses(Integer length) {
		List<Address> addresses = new ArrayList<>();

		for (Integer i = 0; i < length; i++) {
			addresses.add(instace());
		}

		return addresses;
	}

	public static List<Address> instaceAddressesWithCityAndStateOnlyId(Integer length) {
		List<Address> addresses = new ArrayList<>();

		for (Integer i = 0; i < length; i++) {
			addresses.add(instaceCityAndStateOnlyId());
		}

		return addresses;
	}
}
