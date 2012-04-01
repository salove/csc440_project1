package common;
import exception.*;

public class Instructor extends User {

	public Instructor(User user) throws RoleException {
		super(user,ROLE_INSTRUCTOR);
	}
	
	
}
