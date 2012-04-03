package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import action.CourseActions;
import action.ExerciseActions;
import action.ExerciseAttemptActions;
import action.FindUserActions;
import action.QueryActions;

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
			/* 9 */ "Enroll students in a class",
			/* 10 */ "Exit"
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
    			enrollStudents();
    			break;
    		case 10:
    			doExit=true;
    			break;
    		}
    	}
    }
	
	private void enrollStudents() {
		try {
		CourseActions ca=new CourseActions(ui.getSession());
		List<Course> courseList=ca.getAllCourses();
		Course selectedCourse = selectCourse(courseList);
		FindUserActions fua=new FindUserActions(ui.getSession());
		List<User> studentList=fua.findAllUsersWithRole(User.ROLE_STUDENT);
		User selectedUser=selectUser(studentList);
		ca.enrollStudent(selectedCourse, new Student(selectedUser));
		
		} catch (Exception e ) {
			ui.statusUpdate("Failed to enroll student:"+e.getMessage());
		}
	}

	private void showEnrolledStudents() {
		CourseActions ca;
		try {
			ca=new CourseActions(ui.getSession());
			List<Course> courseList=ca.getAllCourses();
			Course course=selectCourse(courseList);
			
			for (Student s:course.getStudentList()) {
				ui.write("  "+formatUser(s));
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
			ui.clear();
			ca=new CourseActions(ui.getSession());
			List<Course> courseList=ca.getAllCourses();
			ui.statusUpdate("Course list is " + (courseList.isEmpty()?"empty":"not empty"));
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
		String[] subMenu= {
				/* 0 */ "Find students that scored the maximum on the first attempt for an Exercise.",
				/* 1 */ "Find students that scored the maximum score on the first attempt for any Exercise",
				/* 2 */ "Show scores for all students for all Exercises",
				/* 3 */ "Show the maximum and minimum score for all Exercises",
				/* 4 */ "Show the maximum and minimum score for all Questions",
				/* 5 */ "Show the average number of attempts for all Exercises",
				/* 6 */ "Show the average number of attempts for all Questions",
				/* 7 */ "Exit"
		};
		
		Selection sub=new Selection("Student score submenu:", subMenu);
		
		try {
		
			QueryActions qa=new QueryActions(ui.getSession());
			CourseActions ca=new CourseActions(ui.getSession());
			ExerciseActions ea=new ExerciseActions(ui.getSession());
			
			switch(select(sub)) {
			case 0:
			{	
				List<Course> courses=ca.getAllCourses();
				Course selectedCourse=selectCourse(courses);
				List<Exercise> exercises=ea.getForCourse(selectedCourse);
				Exercise ex=selectExercise(exercises);
				List<Student> students=qa.findStudentsThatScoredMaxOnFirstAttemptForExercise(ex); 
				ui.clear();
				ui.write("\n\nStudents scoring highest on first attempt of "+ex.getName()+"\n\n");
				for (Student s:students) {
					ui.write("  "+formatUser(s));
				}
				break;
			}
			case 1:
			{
				List<Student> students=qa.findStudentsThatScoredMaxOnFirstAttemptAnyExercises();
				ui.clear();
				ui.write("\n\nStudents scoring highest on first attempt of any exercises\n\n");
				for (Student s:students) {
					ui.write("  "+formatUser(s));
				}
			}
			case 2:
			{
				ui.write("\n\n Not Implemented - press Enter");
				ui.readLine();
			}
			case 3:
			{
				ui.write("\n\n Not Implemented - press Enter");
				ui.readLine();
			}
			case 4:
			{
				ui.write("\n\n Not Implemented - press Enter");
				ui.readLine();
			}
			case 5:
			{
				ui.write("\n\n Not Implemented - press Enter");
				ui.readLine();
			}
			case 6:
			{
				ui.write("\n\n Not Implemented - press Enter");
				ui.readLine();
			}
			case 7:
			{
				ui.write("\n\n Not Implemented - press Enter");
				ui.readLine();
			}
			}
		
		} catch (Exception e) {
			ui.statusUpdate("Failed to retrieve data: "+e.getMessage());
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
            	ui.statusUpdate("Adding TA, size is "+taList.size());
            	FindUserActions fua=new FindUserActions(ui.getSession());
            	ui.statusUpdate("FUA is " + (null==fua ? "<null>": "not null"));
            	
            	Vector<User> taUsers=fua.findAllUsersWithRole(User.ROLE_TA);
            	ui.statusUpdate("taUsers size is "+taUsers.size());
            	
            	User selectedTA=selectUser(taUsers);
            	taList.add(new TA(selectedTA,token));
            	
                       	
            }
            
            ui.statusUpdate("Done with ta");
            
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
