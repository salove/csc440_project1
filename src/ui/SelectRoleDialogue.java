package ui;

import java.util.Vector;

import common.*;

public class SelectRoleDialogue extends GeneralDialogue {
    SelectRoleDialogue(UI ui) {
		super(ui);
		// TODO Auto-generated constructor stub
	}

	int selectRole() {
        Vector<String> choices=new Vector<String>();
        
        User user=ui.getSession().getUser();
        
             
        
        if (user.countRoles()>1) {
            ui.clear();
            ui.write("\nPlease select which role you want to work with: ");
            ui.write("\n");
            
            if (user.hasRole(User.ROLE_ADMINISTRATOR)) {
                choices.add("Administrator");
            }
            
            if (user.hasRole(User.ROLE_INSTRUCTOR)) {
                choices.add("Instructor");
            }
            
            if (user.hasRole(User.ROLE_STUDENT)) {
                choices.add("Student");
            }
            
            if (user.hasRole(User.ROLE_TA)) {
                choices.add("TA");
            }
            
            Selection roleSelect=new Selection("Chose role:",choices);
            int selIdx=select(roleSelect);
            String roleString=choices.get(selIdx);
            
            if (roleString.startsWith("A")) {
            	return User.ROLE_ADMINISTRATOR;
            } else if (roleString.startsWith("I")) {
            	return User.ROLE_INSTRUCTOR;
            } else if (roleString.startsWith("S")) {
            	return User.ROLE_STUDENT;
            } else if (roleString.startsWith("T")) {
            	return User.ROLE_TA;
            }
            
            
                        
        } else if (user.countRoles()==1) {
            if (user.hasRole(User.ROLE_ADMINISTRATOR)) {
                return User.ROLE_ADMINISTRATOR; 
            }
            
            if (user.hasRole(User.ROLE_INSTRUCTOR)) {
                return User.ROLE_INSTRUCTOR;
            }
            
            if (user.hasRole(User.ROLE_STUDENT)) {
                return User.ROLE_STUDENT;
            }
            
            if (user.hasRole(User.ROLE_TA)) {
                return User.ROLE_TA;
            }
            
            
        } 
        
        return -1;
    }

}
