package db;

import java.util.*;
import java.sql.*;

import common.C_Date;
import common.Course;
import common.Exercise;
import common.ExerciseAttempt;
import common.Student;
import exception.ConnectionFailedException;
import exception.RecordNotFoundException;
import exception.RoleException;

public class DBExerciseAttempts implements ExerciseAttempts {

	private Connection connection;
	private Users dbUsers;
	private Courses dbCourses;
	private Exercises dbExercises;

	private DBExerciseAttempts(Connection connection) {
		this.connection = connection;
		this.dbUsers=DBUsers.getInstance(connection);
		this.dbCourses=DBCourses.getInstance(connection);
		this.dbExercises=DBExercises.getInstance(connection);
	}

	public static ExerciseAttempts getInstance(Connection connection) {
		return new DBExerciseAttempts(connection);
	}

	@Override
	public void dropTables() {
		// TODO Auto-generated method stub

	}

	@Override
	public void createTables() throws SQLException {
		String query = "create table attempts ("
				+ "id int primary key,"
				+ "submission_time DATE,  "
				+ "student_id varchar(30) not null,"
				+ "exercise_id int not null,"
				+ "constraint fk_attempt_exercise foreign key (exercise_id) references exercises(id)"
				+ ");";
		Statement stmt = this.connection.createStatement();
		stmt.executeUpdate(query);
		stmt.close();

	}

	@Override
	public void addExerciseAttempt(ExerciseAttempt exerciseAttempt) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ExerciseAttempt> getExerciseAttemptsForStudent(Student s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ExerciseAttempt> getExerciseAttemptsForExercise(Exercise e) throws SQLException, ConnectionFailedException, RecordNotFoundException, RoleException {
		String query = String.format(
				"select * from attempts where exercise_id = %s", e.getId());

		Statement stmt = this.connection.createStatement();
		ResultSet rs = stmt.executeQuery(query);

		List<ExerciseAttempt> attempts = new ArrayList<ExerciseAttempt>();
		while (rs.next()) {
			Course course =  dbCourses.getCourse(rs.getString("course_token"));
			Student student = new Student( dbUsers.getUser( rs.getString("student_id")) );
			C_Date submitDate=new C_Date( rs.getDate("submission_time") );
			Exercise exercise = dbExercises.getExercise(course, rs.getInt("exercise_id"));
			ExerciseAttempt ea = new ExerciseAttempt(course,student,submitDate, exercise);
			
			attempts.add(ea);
		}
		
		stmt.close();
		rs.close();
		
		return attempts;
	}
}
