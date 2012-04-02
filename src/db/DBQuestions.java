package db;

import java.util.*;
import java.sql.*;

import common.*;
import exception.ConnectionFailedException;
import exception.RecordNotFoundException;

public class DBQuestions implements Questions {

	@SuppressWarnings("unused")
	private Connection connection;

	public static DBQuestions getInstance(Connection connection) {
		return new DBQuestions(connection);
	}

	private DBQuestions(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void dropTables() {
		// TODO Auto-generated method stub
		String query = "drop table questions;";
		Statement stmt = this.connection.createStatement();
		stmt.executeUpdate(query);
		stmt.close();
	}

	@Override
	public void createTables() {
		// TODO Auto-generated method stub
		String query = "create table questions ("
				+ "id int primary key"
				+ "text varchar(200) not null,"
				+ "hint varchar(200) not null,"
				+ "detailed_explanation varchar(200),"
				+ "difficulty int not null,"
				+ "exercise_id int not null,"
				+ "constraint fk_question_exercise foreign key (exercise_id) references exercises(id)"
				+ ");";
		Statement stmt = this.connection.createStatement();
		stmt.executeUpdate(query);
		stmt.close();
	}

	@Override
	public Question getQuestion(int id) {
		String query = String.format("select * from questions where id = %i;",
				id);
		Statement stmt = this.connection.createStatement();
		ResultSet rs = stmt.executeQuery(query);

		Question q = new Question(null, rs.getString("text"));
		q.setDifficultyLevel(rs.getInt("difficulty"));
		q.setExplanation(rs.getString("detailed_explanation"));
		q.setHintText(rs.getString("hint"));

		rs.close();
		stmt.close();
		return q;
	}

	@Override
	public List<Question> getQuestionsByExercise(Exercise exercise,
			boolean random) throws ConnectionFailedException,
			RecordNotFoundException {
		// randomize question order if specified
		String query = String.format(
				"select * from questions where exercise_id = %i %s;",
				exercise.getId(), (random ? "order by random" : ""));
		Statement stmt = this.connection.createStatement();
		ResultSet rs = stmt.executeQuery(query);

		List<Question> questions = new ArrayList<Question>();
		while (rs.next()) {
			Question q = new Question(null, rs.getString("text"));
			q.setDifficultyLevel(rs.getInt("difficulty"));
			q.setExplanation(rs.getString("detailed_explanation"));
			q.setHintText(rs.getString("hint"));
			
			questions.add(q);
		}
		
		rs.close();
		stmt.close();
		return questions;
	}

	@Override
	public void putQuestion(Question question) throws ConnectionFailedException {
		// TODO Auto-generated method stub

	}

}