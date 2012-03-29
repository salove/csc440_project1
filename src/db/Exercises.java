package db;

import java.util.List;

import common.*;
import exception.ConnectionFailedException;
import exception.RecordNotFoundException;


public interface Exercises extends Tables {
	
	Exercise getExercise(Course course, String id) throws ConnectionFailedException, RecordNotFoundException;
	List<Exercise> getExercisesByCourse(Course course) throws RecordNotFoundException;
	void putExercise(Exercise exercise) throws ConnectionFailedException;

}
