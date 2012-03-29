package common;
import exception.*;

public class Student extends User {

	public Student(User user) throws RoleException {
		super(user,ROLE_STUDENT);
	}
}
