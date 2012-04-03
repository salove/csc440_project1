package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import action.CourseActions;
import action.ExerciseActions;
import action.ExerciseAttemptActions;
import action.FindUserActions;

import common.*;

public class InstructorDialogue extends GeneralDialogue {
	
	private final String[] instructorMenu = {
			/* 0 */ "Add a course subject",
			/* 1 */ "Add a course",
			/* 2 */ "Add a course topic",
			/* 3 */ "Add a homework",
			/* 4 */ "Find students that did not take a homework",
			/* 5 */ "Find student scores",
			/* 6 */ "Show course subjects",
			/* 7 */ "Show courses",
			/* 8 */ "Show enrolled students",
			/* 9 */ "Exit"
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
    			showCourseSubjects();
    			break;
    		case 7:
    			showCourses();
    			break;
    		case 8:
    			showEnrolledStudents();
    			break;
    		case 9:
    			doExit=true;
    			break;
    		}
    	}
    }
	
	private void showEnrolledStudents() {
		CourseActions ca;
		try {
			ca=new CourseActions(ui.getSession());
			List<Course> courseList=ca.getAllCourses();
			Course course=selectCourse(courseList);
			
			for (Student s:course.getStudentList()) {
				ui.write("  "+formatStudent(s));
			}
			ui.write("\n\nPress enter to continue\n");
			ui.readLine();
		} catch (Exception e) {
			ui.statusUpdate("Failed to retrieve enrolled students: " + e.getMessage());
		}
	}

	private void showCourses() {
		CourseActions ca;
		try {
			ca=new CourseActions(ui.getSession());
			List<Course> courseList=ca.getAllCourses();
			for (Course c:courseList) {
				ui.write("  "+formatCourse(c));
			}
			ui.write("\n\nPress enter to continue\n");
			ui.readLine();
		} catch (Exception e) {
			ui.statusUpdate("Failed to retrieve course subjects: " + e.getMessage());
		}
		
	}

	private void showCourseSubjects() {
		CourseActions ca;
		try {
			ui.clear();
			ui.write("\nCourse Subjects:\n");
			ca = new CourseActions(ui.getSession());
			List<CourseSubject> subjects=ca.getAllCourseSubjects();
			for (CourseSubject s:subjects) {
				ui.write("  "+ formatCourseSubject(s)+ "\n");
			}
			
			ui.write("\n\nPress enter to continue\n");
			ui.readLine();
		} catch (Exception e) {
			ui.statusUpdate("Failed to retrieve course subjects: " + e.getMessage());
		}
		
	}

	private void findStudentScores() {
		
		
		
		
		try {
			
			ui.write("For a particular homework: ");
			boolean forOneHW=yesNo();
			if (forOneHW) {
			
			CourseActions ca=new CourseActions(ui.getSession());
			List<Course> courseList=ca.getAllCourses();
			Vector<Course> courseVector=new Vector<Course>(courseList);
			Vector<String> courseNames=new Vector<String>();
			for (Course c:courseVector) {
				courseNames.add(c.getToken()+" - "+c.getName());
			}
			Selection temp=new Selection("For which course:", courseNames);
			int courseIdx=select(temp);
			Course selectedCourse=courseVector.get(courseIdx);
			
			ExerciseActions ea=new ExerciseActions(ui.getSession());
			List<Exercise> exerciseList=ea.getForCourse(selectedCourse);
			Vector<Exercise> exerciseVector=new Vector<Exercise>(exerciseList);
			Vector<String> exerciseNames=new Vector<String>();
			for (Exercise ex:exerciseVector) {
				exerciseNames.add(""+ex.getId()+" - "+ex.getStartDate().toString());
			}
			Selection temp2=new Selection("For which exercise: ", exerciseNames);
			int exerciseIdx=select(temp2);
			
			Exercise selectedExercise=exerciseVector.get(exerciseIdx);
			
			ExerciseAttemptActions eaa=new ExerciseAttemptActions(ui.getSession());
			List<ExerciseAttempt> attempts=eaa.getExerciseAttempts(selectedExercise);
			for(ExerciseAttempt attempt:attempts) {
				ui.write(" More work to do here");
			}
			
			} else {
				// For all HW
			}
			
			
		} catch (Exception e) {
			ui.statusUpdate("Failed to retrieve students missing homework: "+e.getMessage());
		}
	}

	private void findStudentsMissingHomework()  {
		ui.write("\n Find students missing homework:\n");
		
		
		
		try {
			CourseActions ca=new CourseActions(ui.getSession());
			List<Course> courseList=ca.getAllCourses();
			
			Course selectedCourse=selectCourse(courseList);
			
			ExerciseActions ea=new ExerciseActions(ui.getSession());
			List<Exercise> exerciseList=ea.getForCourse(selectedCourse);
			Exercise selectedExercise=selectExercise(exerciseList);
			
			
			ExerciseAttemptActions eaa=new ExerciseAttemptActions(ui.getSession());
			List<ExerciseAttemptActions.StudentExercisePair> results=eaa.findStudentsMissingHomework(selectedExercise);
			if (results.isEmpty()) {
				ui.write("There are no students missing this assignment");
			} else {
				for (ExerciseAttemptActions.StudentExercisePair pair:results) {
					ui.write(" "+pair.exercise.getName()+"  "+pair.student.getUserId()+" ("+pair.student.getName()+")");
				}
			}
			ui.statusUpdate("Find students missing homework completed ok");
		} catch (Exception e) {
			ui.statusUpdate("Failed to retrieve data: "+e.getMessage());
		} 
		
	}

	private void addCourseTopic()  {
		try {
		CourseActions ca=new CourseActions(ui.getSession());
		List<CourseSubject> subjects=ca.getAllCourseSubjects();
		CourseSubject selectedSubject=selectCourseSubject(subjects);
		ui.write("\nEnter Topic Id: ");
		String topicId=ui.readLine();
		ui.write("Enter Topic Name:");
		String topicName=ui.readLine();
		ca.addTopic(selectedSubject, topicId, topicName);
		ui.statusUpdate("Successfully added course topic");
		} catch (Exception e) {
			ui.statusUpdate("Failed to add course topic: "+e.getMessage());
		}
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

    
    public void findHighestOnAHomework() {

    }

    public void findHighestOverall() {

    }

    public void findMissingHomework() {

    }

    
}
