package common;

import java.util.ArrayList;

public class Question {
	private String id;
	private String questionText;
	int difficultyLevel;
	String hintText;
	String explanationText;
	Topic topic;
	Key key;
	ArrayList<Answer> correctAnswers=new ArrayList<Answer>();
	ArrayList<Answer> incorrectAnswers=new ArrayList<Answer>();
	
	public Question(Topic topic, String id) {
		this.topic=topic;
		this.id=id;
		this.key=new Key(topic.getKey(), id);
	}
	
	public Key getKey() {
		return this.key;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getQuestionText() {
		return this.questionText;
	}
	
	public int getDifficultyLevel() {
		return this.difficultyLevel; 
	}
	
	public String getHintText() {
		return this.hintText;
	}
	
	public String getExplanation() {
		return this.explanationText;
	}
	
	public void setQuestionText(String text) {
		this.questionText=text;
	}
	
	public void setDifficultyLevel(int level) {
		this.difficultyLevel=level;
	}
	
	public void setHintText(String text) {
		this.hintText=text;
	}
	
	public void setExplanation(String text) {
		this.explanationText=text;
	}
	
	public void addCorrectAnswer(String text, String explanation, String id) {
	    correctAnswers.add(new Answer(this,id,text,explanation,true));
	}
	
	public void addIncorrectAnswer(String text, String explanation, String id) {
	    incorrectAnswers.add(new Answer(this,id,text,explanation,true));
	}
	
}
