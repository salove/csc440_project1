package exception;
import common.*;

public class RoleException extends Exception {

	
	private static final long serialVersionUID = 1L;

	public RoleException(User user, String role) {
		super("User "+user.getName()+" is not "+Utils.getPronoun(role)+" "+role);
	}
}
