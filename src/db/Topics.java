package db;

import java.util.List;

import common.*;

public interface Topics extends Tables {
	void addTopic(Topic topic);
	List<Topic> getTopics(CourseSubject subject);
}
