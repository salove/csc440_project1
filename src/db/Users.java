package db;
import java.util.List;

import common.*;
import exception.*;


public interface Users extends Tables {
	
	User getUser(String userId) throws RecordNotFoundException, ConnectionFailedException;
	void putUser(User user) throws RecordNotFoundException, ConnectionFailedException;
	List<User> getAll();
		
}
