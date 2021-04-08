package properties.state;

import com.foster.pet.entity.State;

public class StateInstance extends StateProperties {

	public static State instace() {
		State state = new State();
		state.setId(ID);
		state.setName(NAME);
		
		return state;
	}
	
	public static State instaceOnlyId() {
		State state = new State();
		state.setId(ID);
		
		return state;
	}
}
