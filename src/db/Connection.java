package db;
import exception.ConnectionFailedException;

public class Connection {

	public static Connection connect() throws ConnectionFailedException {
		return new Connection();
	}
}
