package db;

import java.sql.SQLException;
import java.util.List;

import common.*;
import exception.ConnectionFailedException;
import exception.RecordNotFoundException;


public interface Exercises extends Tables {
	
	Exercise getExercise(Course course, int i) throws ConnectionFailedException, RecordNotFoundException;
	List<Exercise> getExercisesByCourse(Course course) throws RecordNotFoundException, SQLException;
	void putExercise(Exercise exercise) throws ConnectionFailedException;
	List<Exercise> getExercisesByStudent(Student s);

}
