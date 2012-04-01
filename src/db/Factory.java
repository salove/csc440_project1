package db;
import java.sql.SQLException;

import exception.ConnectionFailedException;

public class Factory {
	
	private boolean unitTest;
	private Connection connection;
	
		
	private Factory(boolean unitTest, Connection connection) {
		this.unitTest=unitTest;
		this.connection=connection;
	}

	public static Factory getInstance(boolean unitTest) throws ConnectionFailedException {
		
		Connection c=Connection.connect();
		return new Factory(unitTest,c);
		
	}
	
	public Users getUsers() {
		if (unitTest) {
			return UTUsers.getInstance(connection);
		} else {
			return DBUsers.getInstance(connection);
		}
	}
	
	public CourseSubjects getCourseSubjects() {
		if (unitTest) {
			return UTCourseSubjects.getInstance(connection);
		} else {
			return DBCourseSubjects.getInstance(connection);
		}
	}
	
	public Courses getCourses() {
		if (unitTest) {
			return UTCourses.getInstance(connection);
		} else {
			return DBCourses.getInstance(connection);
		}
	}
	
	public Exercises getExercises() {
	    if (unitTest) {
	        return UTExercises.getInstance(connection);
	    } else {
	        return DBExercises.getInstance(connection);
	    }
	}
	
	public ExerciseAttempts getExerciseAttempts() {
	    if (unitTest) {
	        return UTExerciseAttempts.getInstance(connection);
	    } else {
	        return DBExerciseAttempts.getInstance(connection);
	    }
	}
	
	public AnswerAttempts getAnswerAttempts() {
	    if (unitTest) {
	        return UTAnswerAttempts.getInstance(connection);
	    } else {
	        return DBAnswerAttempts.getInstance(connection);
	    }
	}
	
	public void createTables() throws SQLException {
		
		getUsers().createTables();
		getCourseSubjects().createTables();
		getCourses().createTables();
		getExercises().createTables();
		getExerciseAttempts().createTables();
		
	}
	
	public void dropTables() throws SQLException {
		
	    getExerciseAttempts().dropTables();
	    getExercises().dropTables();
		getCourses().dropTables();
		getCourseSubjects().dropTables();
		getUsers().dropTables();
		
	}
}
