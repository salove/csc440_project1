package db;

import java.sql.SQLException;
import java.util.List;

import common.Question;
import common.Topic;

public class DBQuestions implements Questions {

	
	@SuppressWarnings("unused")
	private Connection connection;
	private DBQuestions(Connection c) {
		this.connection=c;
	}
	
	public static Questions getInstance(Connection connection) {
		return new DBQuestions(connection);
	}
	
	@Override
	public void dropTables() throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void createTables() throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void add(Question question) {
		// TODO Auto-generated method stub

	}

	@Override
	public Question get(Topic topic, String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Question> getForTopic(Topic topic) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Question> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
