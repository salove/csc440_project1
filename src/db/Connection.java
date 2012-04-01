package db;
import java.sql.Statement;

import exception.ConnectionFailedException;

public class Connection {

	public static Connection connect() throws ConnectionFailedException {
		return new Connection();
	}

	public Statement createStatement() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}

