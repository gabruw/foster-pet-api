package properties.authentication;

import com.foster.pet.dto.authentication.TokenRDTO;
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
	
	public static TokenRDTO tokenRDTOInstance() {
		TokenRDTO tokenRDTO = new TokenRDTO();
		tokenRDTO.setToken(TOKEN);
		
		return tokenRDTO;
	}
}
