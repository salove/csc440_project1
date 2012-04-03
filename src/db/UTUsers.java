package db;
import java.util.ArrayList;
import java.util.List;

import common.User;

import exception.ConnectionFailedException;
import exception.RecordNotFoundException;

public class UTUsers implements Users {

	private ArrayList<User> list;
	private static UTUsers instance=null;
		
	private UTUsers() {
		list=new ArrayList<User>();
	}
	
	public static Users getInstance(Connection c) {
		if (null==instance) {
			instance=new UTUsers();
		}
		return instance;
	}
	
	
	@Override
	public User getUser(String userId) throws RecordNotFoundException,
			ConnectionFailedException {
		for (User u: list) {
			if (u.getUserId().equals(userId)) {
				return u;
			}
		}
		throw new RecordNotFoundException("User name "+ userId + " not found.");
	}

	@Override
	public void putUser(User user) throws ConnectionFailedException {
	    // Add or modify user
	    if (list.contains(user)) {
	        list.remove(user);
	    }
		list.add(user);
	}

	@Override
	public void dropTables() {
		list.clear();
		
	}

	@Override
	public void createTables() {
		// nothing to do for UT
	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
