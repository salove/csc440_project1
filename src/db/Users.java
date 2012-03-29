package db;
import common.*;
import exception.*;


public interface Users extends Tables {
	
	User getUser(String userId) throws RecordNotFoundException, ConnectionFailedException;
	void putUser(User user) throws RecordNotFoundException, ConnectionFailedException;
		
}
