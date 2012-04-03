package action;

import ui.UI;
import common.*;
import db.Factory;
import db.Users;
import exception.ConnectionFailedException;
import exception.LoginFailedException;
import exception.RecordNotFoundException;

public class Login {
	
	public static Session login(UI ui, String userId, String pw, boolean unitTest) throws LoginFailedException {
		try {
			Factory factory=Factory.getInstance(unitTest);
			Users dbUsers=factory.getUsers();
			User user=dbUsers.getUser(userId);
			if (!user.getPWHash().equals(Utils.pwHash(pw))) {
				throw new LoginFailedException("Password not valid.");
			}
			return new Session(user,ui);
		} catch (RecordNotFoundException e) {
			throw new LoginFailedException("User id "+userId+" not found.");
		} catch (ConnectionFailedException e) {
			throw new LoginFailedException("Unable to connect to database for login:  "+e.getMessage());
		}
	}

}

