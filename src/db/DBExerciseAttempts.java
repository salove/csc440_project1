package db;

import java.util.*;
import java.sql.*;

import common.*;

public class DBExerciseAttempts implements ExerciseAttempts {

	@SuppressWarnings("unused")
	private Connection connection;

	private DBExerciseAttempts(Connection connection) {
		this.connection = connection;
	}

	public static ExerciseAttempts getInstance(Connection connection) {
		return new DBExerciseAttempts(connection);
	}

	@Override
	public void dropTables() {
		// TODO Auto-generated method stub

	}

	@Override
	public void createTables() {
		String query = "create table attempts ("
				+ "id int primary key,"
				+ "submission_time DATE,  "
				+ "student_id int not null,"
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
	public List<ExerciseAttempt> getExerciseAttemptsForExercise(Exercise e) {
		String query = String.format(
				"select * from attempts where exercise_id = %s", e.getId());

		Statement stmt = this.connection.createStatement();
		ResultSet rs = stmt.executeQuery(query);

		List<ExerciseAttempt> attempts = new ArrayList<ExerciseAttempt>();
		while (rs.next()) {
			Course c = new Course(rs.getString("course_token"));
			ExerciseAttempt ea = new ExerciseAttempt(c, new Student(new User(
					rs.getInt("student_id"), User.ROLE_STUDENT)),
					new C_Date(rs.getDate("submission_time")), new Exercise(c,
							rs.getInt("exercise_id")));
			attempts.add(ea);
		}

		stmt.close();
		rs.close();

		return attempts;
	}
}
