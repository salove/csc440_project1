package db;

import java.sql.SQLException;
import java.util.List;

import common.Exercise;
import common.Question;
import common.Student;

import exception.ConnectionFailedException;
import exception.RecordNotFoundException;
import exception.RoleException;

public class DBQueries implements Queries {

	@SuppressWarnings("unused")
	private Connection connection;
	private DBQueries(Connection c ) {
		this.connection=c;
	}
	public static Queries getInstance(Connection connection) {
		return new DBQueries(connection);
	}
	
	@Override
	public List<Student> findStudentsThatScoredMaxOnFirstAttemptForExercise(
			Exercise e) throws SQLException, ConnectionFailedException,
			RecordNotFoundException, RoleException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> findStudentsThatScoredMaxOnFirstAttemptAnyExercises()
			throws SQLException, ConnectionFailedException,
			RecordNotFoundException, RoleException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int findMaximumScore(Exercise exercise) throws SQLException,
			ConnectionFailedException, RecordNotFoundException, RoleException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int findMinimumScore(Exercise exercise) throws SQLException,
			ConnectionFailedException, RecordNotFoundException, RoleException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int findMaximumScore(Question question) throws SQLException,
			ConnectionFailedException, RecordNotFoundException, RoleException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int findMinimumScore(Question question) throws SQLException,
			ConnectionFailedException, RecordNotFoundException, RoleException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int findAverageAttempts(Exercise exercise) throws SQLException,
			ConnectionFailedException, RecordNotFoundException, RoleException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int findAverageAttempts(Question question) throws SQLException,
			ConnectionFailedException, RecordNotFoundException, RoleException {
		// TODO Auto-generated method stub
		return 0;
	}

}
