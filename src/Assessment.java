import common.User;
import common.Utils;

import db.Factory;
import exception.ConnectionFailedException;
import exception.RecordNotFoundException;
import ui.UI;


public class Assessment {

    /**
     * @param args
     */
    public static void main(String[] args) {
        
        User a=new User("admin","Administrator",User.ROLE_ADMINISTRATOR, Utils.pwHash("pw"));
        User b=new User("i", "Instructor", User.ROLE_INSTRUCTOR, Utils.pwHash("pw"));
        
        UI ui=new UI();
        try {
            Factory.getInstance(true,ui).getUsers().putUser(a);
            Factory.getInstance(true,ui).getUsers().putUser(b);
        } catch (RecordNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ConnectionFailedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        ui.init();

    }

}
