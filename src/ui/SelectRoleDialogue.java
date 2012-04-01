package ui;

import java.util.Vector;

import common.*;

public class SelectRoleDialogue {
    static int selectRole(UI ui) {
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
            
            int i=1;
            for (String s:choices) {
                ui.write("\t"+i+"\t"+s+"\n");
            }
            
            ui.write("\n");
            String rsp=ui.readLine();
            
            i=-1;
            try {
                i=Integer.parseInt(rsp);
            } catch (NumberFormatException e) {
                
            }
            
            if (i!=-1) {
                String selection=choices.elementAt(i).toString();
                if ("Administrator".equals(selection)) {
                    return User.ROLE_ADMINISTRATOR;
                } else if ("Instructor".equals(selection)) {
                    return User.ROLE_INSTRUCTOR;
                } else if ("Student".equals(selection)) {
                    return User.ROLE_STUDENT;
                } else if ("TA".equals(selection)) {
                    return User.ROLE_TA;
                }
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
