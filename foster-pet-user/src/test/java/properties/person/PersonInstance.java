package properties.person;

import com.foster.pet.entity.Person;

public class PersonInstance extends PersonProperties {

	public static Person instace() {
		Person person = new Person();
		person.setId(ID);
		person.setCpf(CPF);
		person.setName(NAME);
		person.setCell(CELL);
		person.setBirth(BIRTH);
		person.setGender(GENDER);

		return person;
	}
}
