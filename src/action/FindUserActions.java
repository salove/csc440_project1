package action;

import java.util.List;
import java.util.Vector;

import common.*;
import db.Factory;
import exception.ConnectionFailedException;

public class FindUserActions {
	private Session session;
	
	public FindUserActions(Session s) {
		session=s;
	}
	
	public Vector<User> findAllUsersWithRole(int role) throws ConnectionFailedException {
		Vector<User>retVal=new Vector<User>();
		
		Factory f=Factory.getInstance(Settings.checkUnitTest());
        List<User>allUsers=f.getUsers().getAll();
        session.statusUpdate("In findAllUsers");
        
        for (User u:allUsers) {
        	session.statusUpdate("Checking "+u.getUserId());
        	if (u.hasRole(role)) {
        		retVal.add(u);
        		session.statusUpdate("Added "+u.getUserId());
        	} else {
        		session.statusUpdate("No add "+u.getUserId());
        	}
        }
        
        return retVal;
	}
}
