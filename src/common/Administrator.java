package common;
import exception.*;

public class Administrator extends User {
	
	Administrator(User user) throws RoleException {
		super(user,ROLE_ADMINISTRATOR);
		
	}
}
