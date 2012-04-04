package action;

import java.util.List;

import common.*;

import db.Factory;
import exception.ConnectionFailedException;

public class QuestionActions {
	
	Factory factory;
	public QuestionActions(Session s) throws ConnectionFailedException {
		factory=Factory.getInstance(Settings.checkUnitTest());
	}
	
	public List<Question> getAll() {
		return factory.getQuestions().getAll();
	}
	
	public void putQuestion(Question q) {
		factory.getQuestions().add(q);
	}
}
