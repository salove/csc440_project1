package common;
import exception.*;

public class TA extends User {

	Course someClass;
	
	TA(User user, Course someClass) throws RoleException {
		super(user, ROLE_TA);
		this.someClass=someClass;
	}
}
