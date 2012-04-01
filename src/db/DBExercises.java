package db;

import java.util.*;
import java.sql.*;

import common.Course;
import common.Exercise;

import exception.ConnectionFailedException;
import exception.RecordNotFoundException;

public class DBExercises implements Exercises {

	@SuppressWarnings("unused")
	private Connection connection;

	private DBExercises(Connection connection) {
		this.connection = connection;
	}

	public static Exercises getInstance(Connection connection) {
		return new DBExercises(connection);
	}

	@Override
	public void dropTables() {
		// TODO Auto-generated method stub
		String query = "drop table exercises;";
		Statement stmt = this.connection.createStatement();
		stmt.executeUpdate(query);
		stmt.close();
	}

	@Override
	public void createTables() {
		// TODO Auto-generated method stub
		String query = "create table exercises ("
				+ "id int primary key,"
				+ "name varchar(30) not null,"
				+ "start_date date not null,"
				+ "end_date date not null,"
				+ "seed int not null,"
				+ "correct_points int not null,"
				+ "incorrect points int not null,"
				+ "score_method int not null,"
				+ "retries int not null,"
				+ "course_token int not null,"
				+ "constraint fk_exercise_course foreign key (course_token) references courses(token)"
				+ ");";
		Statement stmt = this.connection.createStatement();
		stmt.executeUpdate(query);
		stmt.close();
	}

	@Override
	public Exercise getExercise(Course course, String id)
			throws ConnectionFailedException, RecordNotFoundException {
		// TODO Auto-generated method stub
		String query = String.format(
				"select * from exercises where course_token = %s and id = %s;",
				course.id, id);
		Statement stmt = this.connection.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		// FIXME course constructor requires a topic for some reason
		Exercise e = new Exercise(new Course(rs.getString("course_token")),
				rs.getInt("id"));
		// FIXME date type in constructor
		e.setStartDate(rs.getDate("start_date"));
		e.setEndDate(rs.getDate("end_date"));
		e.setRandomizationSeed(rs.getInt("seed"));
		e.setRetriesAllowed(rs.getInt("retries"));
		e.setScoreSelectionMethod(rs.getInt("score_method"));
		e.setPointsPerCorrectAnswer(rs.getInt("correct_points"));
		e.setPointsPerWrongAnswer(rs.getInt("incorrect_points"));
		// FIXME add name field

		stmt.close();
		rs.close();
		return e;
	}

	@Override
	public List<Exercise> getExercisesByCourse(Course course)
			throws RecordNotFoundException {
		// TODO Auto-generated method stub
		String query = String
				.format("select * from exercises where course_token = %s",
						course.getToken());
		Statement stmt = this.connection.createStatement();
		ResultSet rs = stmt.executeQuery(query);

		List<Exercise> exercises = new ArrayList<Exercise>();

		while (rs.next()) {
			Exercise e = new Exercise(course, rs.getInt("id"));
			// FIXME date type in constructor
			e.setStartDate(rs.getDate("start_date"));
			e.setEndDate(rs.getDate("end_date"));
			e.setRandomizationSeed(rs.getInt("seed"));
			e.setRetriesAllowed(rs.getInt("retries"));
			e.setScoreSelectionMethod(rs.getInt("score_method"));
			e.setPointsPerCorrectAnswer(rs.getInt("correct_points"));
			e.setPointsPerWrongAnswer(rs.getInt("incorrect_points"));
			// FIXME add name field
			exercises.add(e);
		}

		stmt.close();
		rs.close();
		return exercises;
	}

	@Override
	public void putExercise(Exercise exercise) throws ConnectionFailedException {
		// TODO Auto-generated method stub

	}

}
