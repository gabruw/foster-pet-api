package properties.person;

import java.time.LocalDate;

import com.foster.pet.constant.Gender;

public class PersonProperties {

	protected static final Long ID = 1L;
	protected static final String NAME = "Test Name";
	protected static final Gender GENDER = Gender.MALE;
	protected static final String CPF = "993.725.070-67";
	protected static final String CELL = "(00) 0000-0000";
	protected static final LocalDate BIRTH = LocalDate.now();
}
