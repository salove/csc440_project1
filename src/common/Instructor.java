package common;
import exception.*;

public class Instructor extends User {

	Instructor(User user) throws RoleException {
		super(user,ROLE_INSTRUCTOR);
	}
}
