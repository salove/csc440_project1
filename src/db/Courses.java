package db;
import java.util.List;

import common.*;
import exception.ConnectionFailedException;
import exception.RecordNotFoundException;

public interface Courses extends Tables {
	Course getCourse(String id) throws ConnectionFailedException, RecordNotFoundException;
	void putCourse(Course course)  throws ConnectionFailedException;
	List<Course> getByStudent(Student student)  throws ConnectionFailedException, RecordNotFoundException;
	List<Course> getByInstructor(Instructor instructor)  throws ConnectionFailedException, RecordNotFoundException;
	List<Course> getByTA(TA ta)  throws ConnectionFailedException, RecordNotFoundException;
}
