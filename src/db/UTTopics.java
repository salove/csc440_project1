package db;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.CourseSubject;
import common.Topic;

public class UTTopics implements Topics {

	private List<Topic> list=new ArrayList<Topic>();
	private static UTTopics instance=null;
	private UTTopics() {
		
	}
	
	static Topics getInstance(Connection c) {
		if (null==instance) {
			instance=new UTTopics();
		}
		return instance;
	}
	
	@Override
	public void addTopic(Topic topic) {
		list.add(topic);

	}

	@Override
	public List<Topic> getTopics(CourseSubject subject) {
		return list;
	}

	@Override
	public void dropTables() throws SQLException {
		list.clear();
		
	}

	@Override
	public void createTables() throws SQLException {
		// Nothing to do for UT
		
	}

}
