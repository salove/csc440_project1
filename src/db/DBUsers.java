package db;
import common.User;

import exception.ConnectionFailedException;
import exception.RecordNotFoundException;

public class DBUsers implements Users {
	
	@SuppressWarnings("unused")
	private Connection connection;
	
	private DBUsers(Connection c) {
		this.connection=c;
	}

	public static Users getInstance(Connection c) {
		return new DBUsers(c);
	}
	
	
	@Override
	public User getUser(String userId) throws RecordNotFoundException,
			ConnectionFailedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void putUser(User user) throws RecordNotFoundException,
			ConnectionFailedException {
		// TODO Auto-generated method stub

	}

	@Override
	public void dropTables() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createTables() {
		// TODO Auto-generated method stub
		
	}

	
}
