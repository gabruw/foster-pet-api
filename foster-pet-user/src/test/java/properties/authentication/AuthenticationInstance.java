package properties.authentication;

import com.foster.pet.entity.Authentication;

public class AuthenticationInstance extends AuthenticationProperties {

	public static Authentication instace() {
		Authentication authentication = new Authentication();
		authentication.setId(ID);
		authentication.setRole(ROLE);
		authentication.setEmail(EMAIL);
		authentication.setPassword(PASSWORD);

		return authentication;
	}
}
