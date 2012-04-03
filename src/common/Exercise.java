package common;

import java.util.ArrayList;
import java.util.List;

public class Exercise {

    static final int SCORE_LAST_ATTEMPT = 0;
    static final int SCORE_FIRST_ATTEMPT = 1;
    static final int SCORE_MAXIMUM = 2;
    static final int SCORE_AVERAGE = 3;

    private ArrayList<Question> questions = new ArrayList<Question>();
    private int retriesAllowed;
    private C_Date startDate;
    private C_Date endDate;
    private int pointsPerCorrectAnswer;
    private int pointsPerWrongAnswer;
    private long randomizationSeed;
    private int scoreSelectionMethod;
    private Course course;
    private int id;
    private Key key;

    public Exercise(Course course, int id) {
        this.course = course;
        this.id = id;
        // FIXME ugly string type conversion
        this.key = new Key(course.getKey(), id + "");
    }

    public void addQuestion(Question q) {
        this.questions.add(q);
    }

    public Course getCourse() {
        return this.course;
    }

    public C_Date getEndDate() {
        return this.endDate;
    }

    public int getId() {
        return this.id;
    }

    public Key getKey() {
        return this.key;
    }

    public int getPointsPerCorrectAnswer() {
        return this.pointsPerCorrectAnswer;
    }

    public int getPointsPerWrongAnswer() {
        return this.pointsPerWrongAnswer;
    }

    @SuppressWarnings("unchecked")
    public List<Question> getQuestions() {
        return ((List<Question>)this.questions.clone());
    }

    public long getRandomizationSeed() {
        return this.randomizationSeed;
    }

    public int getRetriesAllowed() {
        return this.retriesAllowed;
    }

    public int getScoreSelectionMethod() {
        return this.scoreSelectionMethod;
    }

    public C_Date getStartDate() {
        return this.startDate;
    }

    public void setEndDate(int month, int date, int year) {
        this.endDate = new C_Date(month, date, year);
    }

    public void setPointsPerCorrectAnswer(int pointsPerCorrectAnswer) {
        this.pointsPerCorrectAnswer = pointsPerCorrectAnswer;
    }

    public void setPointsPerWrongAnswer(int pointsPerWrongAnswer) {
        this.pointsPerWrongAnswer = pointsPerWrongAnswer;
    }

    public void setRandomizationSeed(long randomizationSeed) {
        this.randomizationSeed = randomizationSeed;
    }

    public void setRetriesAllowed(int retriesAllowed) {
        this.retriesAllowed = retriesAllowed;
    }

    public void setScoreSelectionMethod(int scoreSelectionMethod) {
        this.scoreSelectionMethod = scoreSelectionMethod;
    }

    public void setStartDate(int month, int date, int year) {
        this.startDate = new C_Date(month, date, year);
    }

    @SuppressWarnings("deprecation")
	public void setStartDate(java.sql.Date d) {
    	setStartDate(d.getMonth(),d.getDate(),d.getYear());
    }
    
    @SuppressWarnings("deprecation")
	public void setEndDate(java.sql.Date d) {
    	setEndDate(d.getMonth(),d.getDate(),d.getYear());
    }
    
    public String getName() {
    	return (""+this.course.getToken()+"-"+this.course.getName()+"-"+this.getStartDate()+"-"+this.id);
    }
}
