package action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.*;
import db.Factory;
import exception.ConnectionFailedException;
import exception.RecordNotFoundException;
import exception.RoleException;

public class ExerciseAttemptActions {
	public ExerciseAttemptActions(Session s) {
		
	}
	
	public class StudentExercisePair {
		public Student student;
		public Exercise exercise;
		public StudentExercisePair(Student s, Exercise e) {
			student=s;
			exercise=e;
		}
	}
	
	public List<StudentExercisePair> findStudentsMissingHomework(Exercise exercise) throws ConnectionFailedException, SQLException, RecordNotFoundException, RoleException {
		List<StudentExercisePair> retVal=new ArrayList<StudentExercisePair>();
		
		Course course=exercise.getCourse();
		for (Student s:course.getStudentList()) {
			List<ExerciseAttempt> eaList=getExerciseAttempts(s);
			boolean isFound=false;
			for (ExerciseAttempt ea:eaList) {
				if (ea.getExercise()==exercise) {
					isFound=true;
					break;
				}
			}
			if (!isFound) {
				retVal.add(new StudentExercisePair(s, exercise));
			}
		}
		
		return retVal;
	}
	
	public List<ExerciseAttempt> getExerciseAttempts(Exercise exercise) throws ConnectionFailedException, SQLException, RecordNotFoundException, RoleException {
		Factory f=Factory.getInstance(Settings.checkUnitTest());
		return (f.getExerciseAttempts().getExerciseAttemptsForExercise(exercise));
		
	}
	
	public List<ExerciseAttempt> getExerciseAttempts(Student student) throws ConnectionFailedException, SQLException, RecordNotFoundException, RoleException {
		Factory f=Factory.getInstance(Settings.checkUnitTest());
		return (f.getExerciseAttempts().getExerciseAttemptsForStudent(student));
		
	}
} 
