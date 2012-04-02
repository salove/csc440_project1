package db;

import java.util.*;
import java.sql.*;

import common.*;
import exception.ConnectionFailedException;
import exception.RecordNotFoundException;

public class DBAnswers implements Answers {

	@SuppressWarnings("unused")
	private Connection connection;

	public static Answers getInstance(Connection connection) {
		return new DBAnswers(connection);
	}

	private DBAnswers(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void dropTables() {
		// TODO Auto-generated method stub
		String query = "drop table answers;";
		Statement stmt = this.connection.createStatement();
		stmt.executeUpdate(query);
		stmt.close();
	}

	@Override
	public void createTables() {
		String query = "create table answers ("
				+ "id int primary key"
				+ "text varchar(200) not null,"
				+ "explanation varchar(200) not null,"
				+ "correct int not null,"
				+ "question_id int not null,"
				+ "constraint fk_answer_question foreign key (question_id) references questions(id)"
				+ ");";
		Statement stmt = this.connection.createStatement();
		stmt.executeUpdate(query);
		stmt.close();
	}

	@Override
	public Answer getAnswer(int id) throws ConnectionFailedException,
			RecordNotFoundException {
		String query = String.format("select * from answers where id = %i", id);
		Statement stmt = this.connection.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		// FIXME how to get question?
		// DBQuestions dbq = new DBQuestions(this.connection);

		// answer is correct when correct != 0
		Answer ans = new Answer(new Question(null, null), rs.getInt("id") + "",
				rs.getString("text"), rs.getString("explanation"),
				(rs.getInt("correct") != 0));

		rs.close();
		stmt.close();

		return ans;
	}

	@Override
	public List<Answer> getAnswersByQuestion(Question question)
			throws ConnectionFailedException, RecordNotFoundException {
		String query = String.format(
				"select * from answers where question_id = %i;",
				question.getId());
		Statement stmt = this.connection.createStatement();
		ResultSet rs = stmt.executeQuery(query);

		List<Answer> answers = new ArrayList<Answer>();

		while (rs.next()) {
			// FIXME how to get question?
			// DBQuestions dbq = new DBQuestions(this.connection);

			// answer is correct when correct != 0
			Answer ans = new Answer(new Question(null, null), rs.getInt("id") + "",
					rs.getString("text"), rs.getString("explanation"),
					(rs.getInt("correct") != 0));

			answers.add(ans);
		}

		rs.close();
		stmt.close();

		return answers;
	}

}
