package db;

import java.sql.SQLException;
import java.util.List;

import common.Question;
import common.Topic;

public class UTQuestions implements Questions {

	private static UTQuestions instance=null;
	private UTQuestions() {
		
	}
	
	public static Questions getInstance(Connection c) {
		if (null==instance) {
			instance=new UTQuestions();
		}
		return instance;
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
