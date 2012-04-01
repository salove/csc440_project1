package action;

import common.*;
import db.Factory;
import exception.ConnectionFailedException;
import exception.RecordNotFoundException;


public class AddUser {
    
    public AddUser(Session s) {
    }
    
    void addUser(String userId, String name, int role, String pwHash) throws ConnectionFailedException, RecordNotFoundException {
        User user=new User(userId, name, role, pwHash);
        Factory f=Factory.getInstance(Settings.checkUnitTest());
        f.getUsers().putUser(user);
    }

}
