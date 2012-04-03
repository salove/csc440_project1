package db;
import java.sql.SQLException;
import java.util.List;

import common.*;
import exception.ConnectionFailedException;
import exception.RecordNotFoundException;
import exception.RoleException;

public interface Courses extends Tables {
	Course getCourse(String id) throws ConnectionFailedException, RecordNotFoundException, SQLException, RoleException;
	void putCourse(Course course)  throws ConnectionFailedException,SQLException;
	List<Course> getByStudent(Student student)  throws ConnectionFailedException, RecordNotFoundException, SQLException;
	List<Course> getByInstructor(Instructor instructor)  throws ConnectionFailedException, RecordNotFoundException,SQLException, RoleException;
	List<Course> getByTA(TA ta)  throws ConnectionFailedException, RecordNotFoundException,SQLException;
	List<Course> getAll() throws ConnectionFailedException, RecordNotFoundException,SQLException;
	void enrollStudent(Course course, Student student) throws ConnectionFailedException, RecordNotFoundException,SQLException;
}
