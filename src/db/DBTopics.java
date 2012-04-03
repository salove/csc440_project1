package db;

import java.sql.SQLException;
import java.util.List;

import common.CourseSubject;
import common.Topic;

public class DBTopics implements Topics {

	@SuppressWarnings("unused")
	private Connection connection;
	private DBTopics(Connection c) {
		this.connection=c;
	}
	
	
	static Topics getInstance(Connection c) {
		return new DBTopics(c);
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
	public void addTopic(Topic topic) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Topic> getTopics(CourseSubject subject) {
		// TODO Auto-generated method stub
		return null;
	}

}
