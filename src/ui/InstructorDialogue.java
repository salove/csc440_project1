package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import action.CourseActions;
import action.FindUserActions;

import common.C_Date;
import common.CourseSubject;
import common.Instructor;
import common.TA;
import common.User;
import common.Utils;

import exception.ConnectionFailedException;
import exception.RecordNotFoundException;
import exception.RoleException;

public class InstructorDialogue extends GeneralDialogue {
	
	private final String[] instructorMenu = {
			/* 0 */ "Add a course subject",
			/* 1 */ "Add a course",
			/* 2 */ "Add a course topic",
			/* 3 */ "Add a homework",
			/* 4 */ "Find students that did not take a homework",
			/* 5 */ "Find student scores",
			/* 6 */ "Exit"
			};
	
	private Selection instructorSelection= new Selection("Please choose one of the following: ", instructorMenu);
	
	public void showMainDialogue() {
    	for (boolean doExit=false; !doExit; ) {
    		switch(select(instructorSelection)) {
    		case 0:
    			addCourseSubject();
    			break;
    		case 1:
    			addCourse();
    			break;
    		case 2:
    			addCourseTopic();
    			break;
    		case 3:
    			addHomework();
    			break;
    		case 4:
    			findStudentsMissingHomework();
    			break;
    		case 5:
    			findStudentScores();
    			break;
    		case 6:
    			doExit=true;
    			break;
    		}
    	}
    }
	
	private void findStudentScores() {
		
		
	}

	private void findStudentsMissingHomework() {
		// TODO Auto-generated method stub
		
	}

	private void addCourseTopic() {
		// TODO Auto-generated method stub
		
	}

	InstructorDialogue(UI ui) {
		super(ui);
	}

    public void addCourse()  {
        ui.clear();
        ui.write("\nAdding a new course\n");

        try {
            CourseActions ca = new CourseActions(ui.getSession());
            List<CourseSubject> subjects = ca.getAllCourseSubjects();
            Vector<String> choices = new Vector<String>();
            for (CourseSubject cs : subjects) {
                choices.add(cs.getIdCode());
            }

            int subjectIndex=select(new Selection("Choose the course subject:", choices) );
            String idCode=choices.elementAt(subjectIndex).toString();
            
            
            ui.write("\nEnter the token for the course: ");
            String token=ui.readLine();

            ui.write("Enter the start date for the course: ");
            C_Date startDate= askDate();

            ui.write("Enter the end date for the course: ");
            C_Date endDate= askDate();
            
            ui.write("Do you want to add one or more TA's: ");
            boolean addTA=yesNo();
            
            List<TA> taList=new ArrayList<TA>();
            
            if (addTA) {
            	FindUserActions fua=new FindUserActions(ui.getSession());
            	
            	Vector<User> taUsers=fua.findAllUsersWithRole(User.ROLE_TA);
            	Vector<String> taUserids=new Vector<String>();
            	for (User u:taUsers) {
            		taUserids.add(u.getUserId()+" ("+u.getName()+")");
            	}
             	if (taUsers.isEmpty()) {
            		ui.write("Sorry, there are no registered TA's");
            		
            	} else {
            		while (!taUsers.isEmpty()) {
            			Selection temp=new Selection("Add which TA:", taUserids);
            			int selectedTa=select(temp);
            			TA ta=new TA((User) taUsers.elementAt(selectedTa), token );
            			taUsers.remove(selectedTa);
            			taUserids.remove(selectedTa);
            			ui.write("Add another TA: " );
            			boolean anotherTA=yesNo();
            			if (anotherTA==false) {
            				break;
            			}
            		}
            	}
                       	
            }
            
            Instructor instructor=new Instructor(ui.getSession().getUser());
            ca.addCourse(token, idCode, startDate, endDate, instructor, taList);
            ui.statusUpdate("Course "+token+" added ok.");

        } catch (Exception e) {
            ui.statusUpdate("Add course failed: "+e.getMessage());
        } 

    }

    public void addCourseSubject() {
        ui.clear();
        ui.write("\nAdding a new course subject\n");
        ui.write(" Enter the id code for the course: ");
        String idCode = ui.readLine();
        CourseSubject cs = new CourseSubject(idCode);
        ui.write(" Enter the name of the course: ");
        String name = ui.readLine();
        cs.setName(name);

        try {
            CourseActions ca = new CourseActions(ui.getSession());
            ca.addCourseSubject(cs);
            ui.statusUpdate("Adding new course subject");
        } catch (Exception e) {
            ui.write("Add course subject failed: "+e.getMessage());
            ui.statusUpdate("Add course subject failed due to: Database connection failure.");
        }
    }

    public void addHomework() {

    }

    public void addTopic() {

    }

    public void findHighestOnAHomework() {

    }

    public void findHighestOverall() {

    }

    public void findMissingHomework() {

    }

    
}
