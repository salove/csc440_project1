package db;

import common.CourseSubject;
import exception.ConnectionFailedException;
import exception.RecordNotFoundException;

public interface CourseSubjects extends Tables {
	CourseSubject getSubject(String idCode) throws RecordNotFoundException, ConnectionFailedException;
	void putSubject(CourseSubject courseSubject) throws ConnectionFailedException;
}
