package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import action.CourseActions;
import action.ExerciseActions;
import action.ExerciseAttemptActions;
import action.FindUserActions;
import action.QueryActions;
import action.QuestionActions;

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
			/* 10 */ "Add a new question",
			/* 11 */ "Exit"
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
				addQuestion();
				break;
			case 11:
				doExit=true;
				break;
			}
		}
	}

	private void addHomework()  {
		try {
			CourseActions ca=new CourseActions(ui.getSession());
			ExerciseActions ea=new ExerciseActions(ui.getSession());
			List<Course> courses=ca.getCoursesByInstructor(new Instructor(ui.getSession().getUser()));
			
			Course selectedCourse=selectCourse(courses);
			List<Topic> topicList=new ArrayList<Topic>();
			for (boolean isDone=false; !isDone; ) {
				List<Topic> courseTopics=selectedCourse.getTopicList();
				for (Topic t:topicList) {
					if (courseTopics.contains(t)) {
						courseTopics.remove(t);
					}
				}
				if (courseTopics.isEmpty()) {
					isDone=true;
				} else {
					Vector<Topic> topicVector=new Vector<Topic>(courseTopics);
					Vector<String> topicStrings=new Vector<String>();
					for (Topic t:topicVector) {
						topicStrings.add(t.getId()+"-"+t.getName());
						
					}
					topicStrings.add("Done");
					Selection selection=new Selection("Choose:",topicStrings);
					int topicIdx=select(selection);
					if(topicIdx<topicVector.size()) {
						topicList.add(topicVector.get(topicIdx));
					} else {
						isDone=true;
					}
					
				}
				
			}
			
			int id=-1;
			for (boolean isValid=false; !isValid; ) {
				ui.clear();
				ui.write("\n\nEnter numeric id: ");
				String rsp=ui.readLine();
				id=Utils.str2int(rsp);
				if (id>=0) {
					isValid=true;
					List<Exercise> existingExercises=ea.getForCourse(selectedCourse);
					for (Exercise e:existingExercises) {
						if (id==e.getId()) {
							isValid=false;
							ui.write("\nID already in use, choose another\nPress enter to continue\n");
							ui.readLine();
						}
					}
				}
			}
			
			Exercise exercise=new Exercise(selectedCourse,id);
			
			ui.clear();
			ui.write("Enter start date: ");
			exercise.setStartDate(askDate());
			ui.write("Enter end date: ");
			exercise.setEndDate(askDate());
			
			for (boolean isValid=false; !isValid; ) {
				ui.clear();
				ui.write("\nEnter points for correct answer: ");
				String rsp=ui.readLine();
				int points=Utils.str2int(rsp);
				if (points>0) {
					exercise.setPointsPerCorrectAnswer(points);
					isValid=true;
				}
			}
			
			for (boolean isValid=false; !isValid; ) {
				ui.clear();
				ui.write("\nEnter points lost for incorrect answer: ");
				String rsp=ui.readLine();
				int points=Utils.str2int(rsp);
				if (points>=0) {
					exercise.setPointsPerWrongAnswer(points);
					isValid=true;
				}
			}
			
			for (boolean isValid=false; !isValid; ) {
				ui.clear();
				ui.write("\nEnter retries allowed: ");
				String rsp=ui.readLine();
				int retriesAllowed=Utils.str2int(rsp);
				if (retriesAllowed>=0) {
					exercise.setRetriesAllowed(retriesAllowed);
					isValid=true;
				}
			}
			
			String[] scoreMenu= {
					"First Attempt",
					"Last Attempt",
					"Max of all Attempts",
					"Average of all Attempts"
			};
			
			Selection scoreSelect=new Selection("Enter scoring method:", scoreMenu);
			int scoreMethod=select(scoreSelect);
			exercise.setScoreSelectionMethod(scoreMethod);
			
			ea.put(exercise);
			
			ui.write("\nExercise added\n");
			ui.write("Press enter to continue\n");
			ui.readLine();
			
		} catch(Exception e ) {
			ui.statusUpdate("Add exercise failed: "+e.getMessage() );
		}
		
		
	}

	private void addQuestion() {
		try {
			CourseActions ca=new CourseActions(ui.getSession());
			List<CourseSubject> courseSubjects=ca.getAllCourseSubjects();
			CourseSubject selectedSubject=selectCourseSubject(courseSubjects);
			
			List<Topic> topics=selectedSubject.getTopics();
			Topic selectedTopic=selectTopic(topics);
			
			ui.write("\nEnter topic id: ");
			String topicId=ui.readLine();
			
			Question q=new Question(selectedTopic,topicId);
			
			ui.write("\nEnter question text: \n");
			String qText=ui.readLine();
			q.setQuestionText(qText);
			
			ui.write("\nEnter hint (optional): \n");
			String qHint=ui.readLine();
			q.setHintText(qHint);
			
			ui.write("\nEnter explanation:\n");
			String qExplanation=ui.readLine();
			q.setExplanation(qExplanation);
			
			boolean someAnswerIsCorrect=false;
			for (boolean isDone=false; !isDone || !someAnswerIsCorrect; ) {
				ui.write("Enter an answer or leave blank if done:\n");
				if (!someAnswerIsCorrect) {
					ui.write(" (at least one answer must be correct)\n");
				}
				String text=ui.readLine();
				if (text.length()==0) {
					isDone=true;
				} else {
					ui.write("\nIs this answer correct:");
					boolean isCorrect=yesNo();
					
					ui.write("\nEnter Explanation:\n");
					String explain=ui.readLine();
					
					ui.write("\nEnter Id: ");
					String id=ui.readLine();
					
					if (isCorrect) {
						q.addCorrectAnswer(text, explain, id);
						someAnswerIsCorrect=true;
					} else {
						q.addIncorrectAnswer(text, explain, id);
					}
				}
			}
			
			for (boolean isValid=false; !isValid; ) {
				ui.clear();
				ui.write("Enter difficulty level: ");
				String rsp=ui.readLine();
				int dl=Utils.str2int(rsp);
				if (dl>0) {
					q.setDifficultyLevel(dl);
					isValid=true;
				}
			}
			
			QuestionActions qa=new QuestionActions(ui.getSession());
			qa.putQuestion(q);
			
		} catch (Exception e) {
			ui.statusUpdate("Add question failed: "+e.getMessage());
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
			ExerciseAttemptActions eaa=new ExerciseAttemptActions(ui.getSession());
			FindUserActions fua=new FindUserActions(ui.getSession());
			QuestionActions qqa=new QuestionActions(ui.getSession());

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
				ui.clear();
				List<User> studentUsers=fua.findAllUsersWithRole(User.ROLE_STUDENT);
				for (User u:studentUsers) {
					Student student=new Student(u);
					ui.write(formatUser(student)+":");
					List<ExerciseAttempt> attempts=eaa.getExerciseAttempts(student);
					for (ExerciseAttempt a:attempts) {
						ui.write("   "+formatExercise(a.getExercise())+" "+a.getSubmissionDate().toString()+" Score="+a.getScore());
					}
				}
			}
			case 3:
			{

				List<Course> allCourses=ca.getAllCourses();
				for (Course course:allCourses) {
					List<Exercise> allExercises=ea.getForCourse(course);
					for (Exercise ex:allExercises) {
						int max=qa.findMaximumScore(ex);
						int min=qa.findMinimumScore(ex);
						ui.write(" Course "+formatCourse(course)+" Exercise "+formatExercise(ex)+" Max="+max+" Min="+min);
					}
				}
				ui.write("\n\n Press Enter to continue");
				ui.readLine();
			}
			case 4:
			{
				List<Question> allQuestions=qqa.getAll();

				for (Question q:allQuestions) {
					int max=qa.findMaximumScore(q);
					int min=qa.findMinimumScore(q);
					ui.write(" Question "+q.getId()+" Max="+max+" Min="+min);
				}
				ui.write("\n\n Press Enter to continue");
				ui.readLine();
			}
			case 5:
			{
				List<Course> allCourses=ca.getAllCourses();
				for (Course course:allCourses) {
					List<Exercise> allExercises=ea.getForCourse(course);
					for (Exercise ex:allExercises) {
						int attCount=qa.findAverageAttempts(ex);

						ui.write(" Course "+formatCourse(course)+" Exercise "+formatExercise(ex)+" Avg Attempts="+attCount);
					}
				}
				ui.write("\n\n Press Enter to continue");
				ui.readLine();
			}
			case 6:
			{
				List<Question> allQuestions=qqa.getAll();

				for (Question q:allQuestions) {
					int avgAtt=qa.findAverageAttempts(q);

					ui.write(" Question "+q.getId()+" Avg Attemptsx="+avgAtt);
				}
				ui.write("\n\n Press Enter to continue");
				ui.readLine();
			}
			case 7:
			{
				return;
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

	


}
