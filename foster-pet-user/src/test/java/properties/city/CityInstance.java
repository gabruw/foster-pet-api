package properties.city;

import com.foster.pet.entity.City;
import com.foster.pet.entity.State;

import properties.state.StateInstance;

public class CityInstance extends CityProperties {

	public static City instace() {
		State state = StateInstance.instace();

		City city = new City();
		city.setId(ID);
		city.setName(NAME);
		city.setState(state);

		return city;
	}

	public static City instaceOnlyId() {
		State state = StateInstance.instaceOnlyId();

		City city = new City();
		city.setId(ID);
		city.setState(state);

		return city;
	}
}
