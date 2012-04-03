package common;
import exception.*;

public class TA extends User {

	String courseToken;
	
	public TA(User user, String courseToken) throws RoleException {
		super(user, ROLE_TA);
		this.courseToken=courseToken;
	}
}
