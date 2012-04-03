package action;

import java.util.List;
import java.util.Vector;

import common.*;
import db.Factory;
import exception.ConnectionFailedException;

public class FindUserActions {
	public FindUserActions(Session s) {
		
	}
	
	public Vector<User> findAllUsersWithRole(int role) throws ConnectionFailedException {
		Vector<User>retVal=new Vector<User>();
		
		Factory f=Factory.getInstance(Settings.checkUnitTest());
        List<User>allUsers=f.getUsers().getAll();
        
        for (User u:allUsers) {
        	if (u.hasRole(role)) {
        		retVal.add(u);
        	}
        }
        
        return retVal;
	}
}
