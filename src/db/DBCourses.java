package db;

import java.util.*;

import common.*;
import exception.ConnectionFailedException;
import exception.RecordNotFoundException;
import java.sql.*;

public class DBCourses implements Courses {

	@SuppressWarnings("unused")
	private Connection connection;

	public static Courses getInstance(Connection connection) {
		return new DBCourses(connection);
	}

	private DBCourses(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void dropTables() {
		String query = "drop table courses;";
		Statement stmt = this.connection.createStatement();
		stmt.executeQuery(query);
		stmt.close();
	}

	@Override
	public void createTables() {
		String query = "create table courses ("
				+ "token int primary key"
				+ "start_date date not null,"
				+ "end_date date not null,"
				+ "name varchar(30) not null,"
				+ "room varchar(30) not null,"
				+ "professor_id int not null,"
				+ "constraint fk_course_professor foreign key (professor_id) references professors(id)"
				+ ");";
		Statement stmt = this.connection.createStatement();
		stmt.executeUpdate(query);
		stmt.close();
	}

	@Override
	public Course getCourse(String id) throws ConnectionFailedException,
			RecordNotFoundException {
		// TODO Auto-generated method stub
		String query = String
				.format("select * from courses where id = %s;", id);
		Statement stmt = this.connection.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		// FIXME course topic in constructor
		Course c = new Course(rs.getString("token"));
		c.setStartDate(rs.getDate("start_date"));
		c.setEndDate(rs.getDate("end_date"));
		// FIXME instructor constructor
		c.setInstructor(new Instructor(new User(rs.getInt("professor_id"),
				User.ROLE_INSTRUCTOR)));
		rs.close();
		stmt.close();
		return c;
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
		String query = String.format(
				"select * from courses where instructor_id = %s;",
				instructor.getUserId());
		Statement stmt = this.connection.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		List<Course> courses = new ArrayList<Course>();

		while (rs.next()) {
			// FIXME course topic in constructor
			Course c = new Course(rs.getString("token"));
			// FIXME date interface to C_Date
			c.setStartDate(rs.getDate("start_date"));
			c.setEndDate(rs.getDate("end_date"));
			// FIXME instructor constructor
			c.setInstructor(new Instructor(new User(rs.getInt("professor_id"),
					User.ROLE_INSTRUCTOR)));
			courses.add(c);
		}
		rs.close();
		stmt.close();
		return courses;
	}

	@Override
	public List<Course> getByTA(TA ta) throws ConnectionFailedException,
			RecordNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
