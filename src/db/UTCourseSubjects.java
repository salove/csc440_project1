package db;

import java.util.ArrayList;
import java.util.List;

import common.CourseSubject;

import exception.ConnectionFailedException;
import exception.RecordNotFoundException;

public class UTCourseSubjects implements CourseSubjects {
	
	private ArrayList<CourseSubject> list=new ArrayList<CourseSubject>();

	
	@SuppressWarnings("unused")
	private Connection connection;
	private static UTCourseSubjects instance=null;
	private UTCourseSubjects(Connection connection) {
		this.connection=connection;
				
	}

	public static CourseSubjects getInstance(Connection connection) {
		if (null==instance) {
			instance=new UTCourseSubjects(connection);
		}
		return instance;
	}
	
	@Override
	public void dropTables() {
		list.clear();
	}

	@Override
	public void createTables() {
		// nothing to do for UT

	}

	@Override
	public CourseSubject getSubject(String idCode)
			throws RecordNotFoundException, ConnectionFailedException {
		for (CourseSubject s: list) {
			if (s.getIdCode()==idCode) {
				return s;
			}
		}
		
		throw new RecordNotFoundException("CourseSubject with idCode "+idCode+" not found.");
	}

	@Override
	public void putSubject(CourseSubject courseSubject)
			throws ConnectionFailedException {
		list.add(courseSubject);

	}

	@Override
	public List<CourseSubject> getAll() {
		return list;
	}
	
	

}
