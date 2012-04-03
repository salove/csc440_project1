package db;

import java.util.List;

import common.*;

public interface Questions extends Tables {
	void add(Question question);
	Question get(Topic topic, String id);
	List<Question> getForTopic(Topic topic);
	List<Question> getAll();
}
