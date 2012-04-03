package action;

import java.sql.SQLException;
import java.util.List;

import common.*;
import db.Factory;
import exception.ConnectionFailedException;
import exception.RecordNotFoundException;
import exception.RoleException;

public class QueryActions {
	
	private Factory factory;
	
	public QueryActions(Session s) throws ConnectionFailedException {
		factory=Factory.getInstance(Settings.checkUnitTest());
	}

	public List<Student> findStudentsThatScoredMaxOnFirstAttemptForExercise(Exercise e) throws SQLException, ConnectionFailedException, RecordNotFoundException, RoleException {
		return factory.getQueries().findStudentsThatScoredMaxOnFirstAttemptForExercise(e);
	}
	
	public List<Student> findStudentsThatScoredMaxOnFirstAttemptAnyExercises() throws SQLException, ConnectionFailedException, RecordNotFoundException, RoleException {
		return factory.getQueries().findStudentsThatScoredMaxOnFirstAttemptAnyExercises();
	}
	
	
	public int findMaximumScore(Exercise exercise) throws SQLException, ConnectionFailedException, RecordNotFoundException, RoleException {
		return factory.getQueries().findMaximumScore(exercise);
	}
	
	public int findMinimumScore(Exercise exercise) throws  ConnectionFailedException, RecordNotFoundException, RoleException, SQLException {
		return factory.getQueries().findMinimumScore(exercise);
	}
	
	public int findMaximumScore(Question question) throws SQLException, ConnectionFailedException, RecordNotFoundException, RoleException {
		return factory.getQueries().findMaximumScore(question);
	}
	
	public int findMinimumScore(Question question) throws SQLException, ConnectionFailedException, RecordNotFoundException, RoleException {
		return factory.getQueries().findMinimumScore(question);
	}
	
	public int findAverageAttempts(Exercise exercise) throws SQLException, ConnectionFailedException, RecordNotFoundException, RoleException {
		return factory.getQueries().findAverageAttempts(exercise);
	}
	
	public int findAverageAttempts(Question question) throws SQLException, ConnectionFailedException, RecordNotFoundException, RoleException {
		return factory.getQueries().findAverageAttempts(question);
	}
	
}
