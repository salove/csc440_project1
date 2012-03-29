package db;

import java.util.List;

import common.*;
import exception.ConnectionFailedException;
import exception.RecordNotFoundException;

public class DBCourses implements Courses {

	@SuppressWarnings("unused")
	private Connection connection;
	
	public static Courses getInstance(Connection connection) {
		return new DBCourses(connection);
	}
	
	private DBCourses(Connection connection) {
		this.connection=connection;
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
	public Course getCourse(String id) throws ConnectionFailedException,
			RecordNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void putCourse(Course course) throws ConnectionFailedException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Course> getByStudent(Student student)
			throws ConnectionFailedException, RecordNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Course> getByInstructor(Instructor instructor)
			throws ConnectionFailedException, RecordNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Course> getByTA(TA ta) throws ConnectionFailedException,
			RecordNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
