package common;

public class Answer {
	String answerText;
	String explanationText;
	String id;
	Question question;
	boolean isCorrect;
	Key key;
	
	public Answer(Question question, String id, String answerText, String explanationText, boolean isCorrect) {
		this.id=id;
		this.question=question;
		this.answerText=answerText;
		this.explanationText=explanationText;
		this.isCorrect=isCorrect;
		key = new Key(question.getKey(), id);
	}
	
	public String getAnswerText() {
		return this.answerText;
	}
	
	public String getExplanation() {
		return this.explanationText;
	}
	
	public String getId() {
		return this.id;
	}
	
	public boolean isCorrect() {
	    return this.isCorrect;
	}
	
	public Key getKey() {
		return key; 
	}
}
