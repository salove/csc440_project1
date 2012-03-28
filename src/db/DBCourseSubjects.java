package db;

import common.CourseSubject;

import exception.ConnectionFailedException;
import exception.RecordNotFoundException;

public class DBCourseSubjects implements CourseSubjects {
	
	
	
	@SuppressWarnings("unused")
	private Connection connection;
	
	private DBCourseSubjects(Connection connection) {
		this.connection=connection;
	}

	
	public static CourseSubjects getInstance(Connection connection) {
		return new DBCourseSubjects(connection);
	}
	
	@Override
	public void dropTables() {
		// TODO Auto-generated method stub

	}

	@Override
	public void createTables() {
		// TODO Auto-generated method stub

	}

	@Override
	public CourseSubject getSubject(String idCode)
			throws RecordNotFoundException, ConnectionFailedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void putSubject(CourseSubject courseSubject)
			throws ConnectionFailedException {
		// TODO Auto-generated method stub

	}

}
