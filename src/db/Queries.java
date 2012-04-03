package db;


import java.sql.SQLException;
import java.util.List;

import common.Exercise;
import common.Question;
import common.Student;
import exception.ConnectionFailedException;
import exception.RecordNotFoundException;
import exception.RoleException;

public interface Queries {
	public List<Student> findStudentsThatScoredMaxOnFirstAttemptForExercise(Exercise e) throws SQLException, ConnectionFailedException, RecordNotFoundException, RoleException;
	public List<Student> findStudentsThatScoredMaxOnFirstAttemptAnyExercises() throws SQLException, ConnectionFailedException, RecordNotFoundException, RoleException;
	public int findMaximumScore(Exercise exercise) throws SQLException, ConnectionFailedException, RecordNotFoundException, RoleException;
	public int findMinimumScore(Exercise exercise)throws SQLException, ConnectionFailedException, RecordNotFoundException, RoleException;
	public int findMaximumScore(Question question)throws SQLException, ConnectionFailedException, RecordNotFoundException, RoleException;
	public int findMinimumScore(Question question)throws SQLException, ConnectionFailedException, RecordNotFoundException, RoleException;
	public int findAverageAttempts(Exercise exercise)throws SQLException, ConnectionFailedException, RecordNotFoundException, RoleException;
	public int findAverageAttempts(Question question)throws SQLException, ConnectionFailedException, RecordNotFoundException, RoleException;
}
