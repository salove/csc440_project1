package db;

import java.util.ArrayList;
import java.util.List;

import common.*;
import exception.ConnectionFailedException;
import exception.RecordNotFoundException;

public class UTCourses implements Courses {
	
	@SuppressWarnings("unused")
	private Connection connection;
	
	private static UTCourses instance=null;
	private UTCourses(Connection connection) {
		this.connection=connection;
	}
	
	public static Courses getInstance(Connection connection) {
		if (null==instance) {
			instance = new UTCourses(connection);
		}
		return instance;
	}
	
	private ArrayList<Course> list=new ArrayList<Course>();

	@Override
	public void dropTables() {
		list.clear();

	}

	@Override
	public void createTables() {
		// Nothing to do for UT

	}

	@Override
	public Course getCourse(String token)  throws ConnectionFailedException, RecordNotFoundException {
		for (Course c: list) {
			if (c.getToken().equals(token)) {
				return c;
			}
		}
		
		throw new RecordNotFoundException("Course with token "+token+" not found.");
	}

	@Override
	public void putCourse(Course course)  throws ConnectionFailedException {
		list.add(course);
	}

	@Override
	public List<Course> getByStudent(Student student)  throws ConnectionFailedException, RecordNotFoundException {
		Key searchKey=student.getKey();
		ArrayList<Course> retVal=new ArrayList<Course>();
		
		for (Course c: list) {
			for (Student s: c.getStudentList()) {
				if (s.getKey().equals(searchKey)) {
					retVal.add(c);
					break;
				}
			}
		}
		
		if (retVal.isEmpty()) {
			throw new RecordNotFoundException("No courses found for student "+searchKey+".");
		}
		
		return retVal;
	}

	@Override
	public List<Course> getByInstructor(Instructor instructor)  throws ConnectionFailedException, RecordNotFoundException {
		Key searchKey=instructor.getKey();
		ArrayList<Course> retVal=new ArrayList<Course>();
		
		for (Course c: list) {
			if (c.getInstructor().getKey().equals(searchKey)) {
				retVal.add(c);
			}
		}
		
		if (retVal.isEmpty()) {
			throw new RecordNotFoundException("No courses found for instructor "+searchKey+".");
		}
		
		return retVal;
	}

	@Override
	public List<Course> getByTA(TA ta)  throws ConnectionFailedException, RecordNotFoundException {
		Key searchKey=ta.getKey();
		ArrayList<Course> retVal=new ArrayList<Course>();
		
		for (Course c: list) {
			for (TA s: c.getTAList()) {
				if (s.getKey().equals(searchKey)) {
					retVal.add(c);
					break;
				}
			}
		}
		
		if (retVal.isEmpty()) {
			throw new RecordNotFoundException("No courses found for TA "+searchKey+".");
		}
		
		return retVal;
	}

}
